package tutu;

import org.junit.Test;
import tutu.util.MysqlUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTX {
    MysqlUtil mysqlUtil = new MysqlUtil();
    //Connection connection = mysqlUtil.getConnection();

    public TestTX() {
        try {
            mysqlUtil.setConnectionAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    public void testBatch(){
        BatchALL("insert into student values (?, ?);",new Object[]{"XIXI",564646});
        System.out.println("插入后");
        BatchALL("select * from student ;",null);

        BatchALL("update student set name = ? where id = ?;",new Object[]{"FEIFEI",564646});
        System.out.println("更新后");
        BatchALL("select * from student ;",null);

        BatchALL("delete from student where id = ? ;",new Object[]{564646});
        System.out.println("删除后");
        BatchALL("select * from student ;",null);
    }

    public void BatchALL(String sql, Object[] objects){
        int[] suc = new int[1];
        suc[0]=0;
        try {
            ResultSet resultSet = mysqlUtil.doBatch(sql,objects,suc);
            if (suc[0]>0){
                System.out.println("执行成功");
            }else {
                if (resultSet!=null){
                    while (resultSet.next()){
                        System.out.print(resultSet.getString(1));
                        System.out.println(" "+resultSet.getInt(2));
                    }
                    resultSet.close();
                }
            }
            //int i = 5/0;
            mysqlUtil.commit();

        } catch (SQLException throwables) {

            try {
                mysqlUtil.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            throwables.printStackTrace();
        }

    }


    void close()   {
        try {
            mysqlUtil.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

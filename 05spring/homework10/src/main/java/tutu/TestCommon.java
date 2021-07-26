package tutu;

import org.junit.Test;
import tutu.util.MysqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestCommon {
    private MysqlUtil mysqlUtil = new MysqlUtil();

    @Test
    public void insert(){
        boolean insert = mysqlUtil.insert("insert into student values (?,?)", new Object[]{"水牛", 202107028});
        if (insert){
            System.out.println("插入成功");
        }
        try {
            mysqlUtil.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void del(){
        int del = mysqlUtil.delete("delete from student where id = ?",new Object[]{202107026});
        if (del>0){
            System.out.println("删除成功");
        }
        try {
            mysqlUtil.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void update(){
        int update = mysqlUtil.update("update student set name = ? where id = ?", new Object[]{"李四", 202107026});
        if (update > 0){
            System.out.println("更改成功");
        }else {
            System.out.println("更改失败");
        }
            try {
                mysqlUtil.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


    }

    @Test
    public void find(){
        ResultSet resultSet = mysqlUtil.find("select * from student",null);
        while (true){
            try {
                if (resultSet.next()) {
                    String name = resultSet.getString(1);
                    int num = resultSet.getInt(2);
                    System.out.println("name " + name + ", number " + num);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    mysqlUtil.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }
}

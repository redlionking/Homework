package tutu;


import java.sql.*;

public class Insert3 {

    private static String url = "jdbc:mysql://localhost:3306/online_shop?useSSL=false&characterEncoding=utf8&" +
            "useUnicode=true&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String userName = "root";
    private static String password = "mysqlwz1tgh";

    public static void main(String[] args) {
        PreparedStatement stmt = null;
        //ResultSet rs = null;
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);


            String sql = "insert into online_shop.order values(?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                preparedStatement.setObject(1,i+"");
                preparedStatement.setObject(2,i+1+"");
                preparedStatement.setObject(3,i%100);
                preparedStatement.addBatch();
                if(i % 500 == 0){
                    //2.执行batch
                    preparedStatement.executeBatch();

                    //3.清空batch
                    preparedStatement.clearBatch();
                }

            }
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为："+(end-start)/1000);//1328



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //if (rs != null) {
            //    try {
            //        rs.close();
            //    } catch (SQLException sqlEx) { } // ignore
            //}

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }
}


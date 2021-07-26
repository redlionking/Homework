package tutu.util;

import java.sql.*;

public class MysqlUtil {
    private final String driverManager = "com.mysql.cj.jdbc.Driver";
    private final String user = "root";
    private final String pwd = "mysqlwz1tgh";
    private final String url = "jdbc:mysql://localhost:3306/test_db?autoReconnect=true&useUnicode=true&" +
            "createDatabaseIfNotExist=true&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai";

    private PreparedStatement preparedStatement = null;
    private Connection connection = null;

    public MysqlUtil(){
        try {
            Class.forName(driverManager);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //public Connection getConnection(){
    //    return connection;
    //}
    //
    //public PreparedStatement getPreparedStatement(){
    //        return preparedStatement;
    //}

    public void close() throws SQLException {
        connection.close();
        preparedStatement.close();
    }

    public boolean insert(String insert,Object[] objects){
        System.out.println(insert);
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
        try {
             preparedStatement = connection.prepareStatement(insert);
            for (int i = 0; i < objects.length; i++) {
                this.preparedStatement.setObject(i+1,objects[i]);
            }
            System.out.println(this.preparedStatement.toString());
            this.preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int delete(String delete,Object[] objects){
        try {
            preparedStatement = connection.prepareStatement(delete);
            if (objects!=null) {
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
            }
            System.out.println(preparedStatement.toString());
            int del = preparedStatement.executeUpdate();
            return del;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public ResultSet find(String find,Object[] objects){
        try {
            preparedStatement = connection.prepareStatement(find);
            if (objects!=null) {
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
            }
            System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public int update(String update, Object[] objects){
        try {
            preparedStatement = connection.prepareStatement(update);
            if (objects!=null) {
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
            }
            System.out.println(preparedStatement.toString());
            int up = preparedStatement.executeUpdate();

            return up;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


    public ResultSet doBatch(String sql, Object[] objects,int[] suc){
        try {
            preparedStatement = connection.prepareStatement(sql);
            if (objects!=null) {
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i+1,objects[i]);
                }
            }

            boolean set = preparedStatement.execute();
            int affect = preparedStatement.getUpdateCount();
            if (affect > 0){
                suc[0]=1;


                 return null;
            }else {
                if (set){
                  ResultSet resultSet = preparedStatement.getResultSet();

                    //if (resultSet!=null){
                    //    while (resultSet.next()){
                    //        System.out.print(resultSet.getString(1));
                    //        System.out.println(" "+resultSet.getInt(2));
                    //    }
                    //}
                return resultSet;
                  }

                System.out.println("执行失败");
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

        }
        return null;
    }


    public void setConnectionAutoCommit(boolean b) throws SQLException {
        connection.setAutoCommit(b);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
}

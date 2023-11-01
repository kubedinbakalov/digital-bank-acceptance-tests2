package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.*;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;

public class DBUtils {

   private static Connection connection = null;
   private static Statement statement = null;
   private static ResultSet resultSet = null;

    //create a method to establish connection with the db
    public static void establishConnection(){

       // String url = "jdbc:mysql://3.249.240.23:3306/kubedinbakalov";

       // String username = "kubedinbakalov";

       // String password = "zkqtvfvhaquikilb";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //connection = DriverManager.getConnection(url,username,password);
            connection = DriverManager.getConnection(
                    getPropertiesValue("digitalbank.db.url"),
                    getPropertiesValue("digitalbank.db.username"),
                    getPropertiesValue("digitalbank.db.password"));
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

   //create a method that can dynamically send select statements
    //and returns a list of map of all colons
    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery){

        List<Map<String,Object>> dbResultList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            //getMetaData method returns info about your information.
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columCount  = resultSetMetaData.getColumnCount();

            while(resultSet.next()){

                Map<String,Object> rowMap = new HashMap<>();

                for(int colum = 1; colum <= columCount; colum++){
                    rowMap.put(resultSetMetaData.getColumnName(colum), resultSet.getObject(colum));
                }
                dbResultList.add(rowMap);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  dbResultList;
    }

    //create a method that inserts and update into db
    //returns the num of rows updated or 0 when action is not taken

    //delete or truncate the table
    public static int  runSQLUpdateQuery(String sqlQuery){
        int rowsAffected = 0;
        try {
            statement =connection.createStatement();
             rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  rowsAffected;
    }

    //close connection

    public static void closeConnection(){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



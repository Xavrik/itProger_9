package com.example.itproger_9;

import java.sql.*;
public class DB {

    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DB_NAME = "itproger-java";
    private final String LOGIN = "root";
    private final String PASS = "root";

    private  Connection dbConn = null;
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME ;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public  void isConnected() throws SQLException, ClassNotFoundException {
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
    }
    public boolean regUser(String login, String email, String password) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `users`(`login`, `email`, `password`) VALUES (?,?,?)";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM `users` WHERE `login` = \""+ login +"\" LIMIT 1");
        if(res.next())
            return false;

        PreparedStatement prST = getDbConnection().prepareStatement(sql);
        prST.setString(1, login);
        prST.setString(2, email);
        prST.setString(3, password);
        prST.executeUpdate();
        return true;
    }
    public boolean authUser(String login, String password) throws SQLException, ClassNotFoundException {
        Statement statement = getDbConnection().createStatement();
        String sql = "SELECT * FROM `users` WHERE `login` = \"" + login + "\" AND `password` = \"" + password + "\" LIMIT 1";
        ResultSet res = statement.executeQuery(sql);
        return res.next();

    }
    public ResultSet getArticles() throws  SQLException,ClassNotFoundException{
        String sql = "SELECT `id`, `title` ,`intro` FROM `articles`";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        return  res;
    }


    public void addArticle (String title, String intro, String text) throws  SQLException, ClassNotFoundException{
        String sql = "INSERT INTO `articles`(`title`, `intro`, `text`, `views` ) VALUES (?,?,?,?)";
        PreparedStatement prST = getDbConnection().prepareStatement(sql);

        prST.setString(1, title);
        prST.setString(2, intro);
        prST.setString(3, text);
        prST.setInt(4, 15);
        prST.executeUpdate();

    }


    public ResultSet viewArticles(String id) throws  SQLException,ClassNotFoundException{
        String sql = "SELECT `title` ,`text` FROM `articles` WHERE `id` = ?";
        PreparedStatement prST = getDbConnection().prepareStatement(sql);
        prST.setString(1, String.valueOf(Integer.parseInt(id)));
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        return  res;
    }



}



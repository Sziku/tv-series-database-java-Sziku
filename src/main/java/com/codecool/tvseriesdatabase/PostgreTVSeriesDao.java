package com.codecool.tvseriesdatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreTVSeriesDao implements TVSeriesDao{
    public Connection getConnection(){
        Connection connection = null;
        String userName = "postgres";
        String password = "784512";
        String host = "localhost";
        String databaseName = "TVseries";


        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + host + ":5432/" + databaseName, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void add(Series series){
        String SQL = "insert into series(title, num_of_seasons, genre) values(?,?,?);";
        try {
            PreparedStatement pt = getConnection().prepareStatement(SQL);
                pt.setString(1, series.getTitle());
                pt.setInt(2,series.getNumberOfSeasons());
                pt.setString(3, series.getGenre());

            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Series series){
        String SQL = "update series set num_of_seasons = ?, genre = ? where title = ?;";
        try {
            PreparedStatement pt = getConnection().prepareStatement(SQL);
            pt.setString(3, series.getTitle());
            pt.setInt(1,series.getNumberOfSeasons());
            pt.setString(2, series.getGenre());

            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String titleToDelete) {
        String SQL = "delete from series where title = ?;";
        try {
            PreparedStatement pt = getConnection().prepareStatement(SQL);
            pt.setString(1, titleToDelete);
            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Series> filterByGenre(String genre) {

        String SQL = "select * from series where genre = ?";
        try {
            PreparedStatement pt = getConnection().prepareStatement(SQL);
            pt.setString(1, genre);

            ResultSet rs = pt.executeQuery();
            List<Series> result = new ArrayList<>();

            while (rs.next()){
                String title = rs.getString(1);
                int season = rs.getInt(2);
                String filmGenre = rs.getString(3);
                Series series = new Series(title,season,filmGenre);

                result.add(series);
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable() {
        String SQL = "create table series(title varchar(50) primary key, num_of_seasons integer, genre varchar(50));";
        try {
            PreparedStatement pt = getConnection().prepareStatement(SQL);;
            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable() {
        String SQL = "drop table series;";
        try {
            PreparedStatement pt = getConnection().prepareStatement(SQL);;
            pt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



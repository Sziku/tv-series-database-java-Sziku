package com.codecool.tvseriesdatabase;

import java.sql.*;
import java.util.List;

public interface TVSeriesDao {
    Connection getConnection();

    void add(Series series);

    void update(Series series);

    void delete(String titleToDelete);

    List<Series> filterByGenre(String genre);

    void createTable();

    void dropTable();
}

package main;

import database.Database;

import java.sql.SQLException;

public class TheMainCreateDb {
    public static void main(String[] args) {
        try {
            Database.open();

            Database.getInstance().getStatement().execute("drop table Bike if exists");
            Database.getInstance().getStatement().execute("create table Bike(" +
                    "  rahmennr   varchar(64)   primary key " +
                    ", markeType  varchar(256)  not null    " +
                    ", text       varchar(256)  not null    " +
                    ")");

            Database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package classes;

import config.Config;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.script.ScriptManager;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static org.dreambot.api.methods.MethodProvider.log;

/**
 * @author camalCase
 *
 * class for logging information about bot account to postgres database
 */


public class Database {
    private static final String url = "jdbc:postgresql://localhost:5432/osrs";
    private static final String username = "postgres";
    private static final String password = "sqlpassword";
    static final ScriptManager scriptManager = ScriptManager.getScriptManager();


    private static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                log("connection OK!");
                return connection;
            } else {
                log("connection is null");
            }
        } catch (Exception e) {
            System.out.println(e);
            log(e);
        }
        return null;
    }

    public static void sendLog(String task) {
        try {
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            log("sending sql data");
            String sqlStatement = String.format("INSERT INTO main (username, gp, script, task, age) VALUES ('%s', '%s', '%s', '%s', '%s' )",
                    scriptManager.getAccountNickname(), // get username
                    Inventory.count("Coins") + Bank.count("Coins"),
                   scriptManager.getCurrentScript(), // get script name
                    task,
                    Age.getAge());

            if (statement != null) {
                try {
                    statement.executeUpdate(sqlStatement);
                    log("Inserted new entry for this account.");
                } catch (PSQLException e) { // basically an if theres already an entry for this user, update instead of insert
                    sqlStatement = String.format("UPDATE main " +
                                                "SET gp = '%s', script = '%s', task = '%s', age = '%s'" +
                                                "WHERE username = '%s'",
                            Inventory.count("Coins") + Bank.count("Coins"),
                            scriptManager.getCurrentScript(),
                            task,
                            Age.getAge(),
                            scriptManager.getAccountNickname()
                            );

                    statement.executeUpdate(sqlStatement);
                }
            } else {
                log("statement = null");
            }
        } catch (SQLException e) {
            log(e);
        }
    }

    public static boolean updateCol(String column,String data) {
        try {
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            String sqlStatement = String.format("UPDATE main " +
                            "SET %s = '%s'" +
                            "WHERE username = '%s'",
                    column,
                    data,
                    scriptManager.getAccountNickname());

            if (statement.executeUpdate(sqlStatement) >= 1) { // i think executeUpdate returns an int of how many rows where edited, so this should check if i did edit the entry (seems to work)
                return true;
            }
        } catch (SQLException e) {
            log(e);
        }
        return false;
    }


    /* used to put an entry into database where username is primary key, allows more flexible database formatting than sendlog */
    public static boolean init() {
        try {
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            String sqlStatement = String.format("INSERT INTO main (username) VALUES ('%s')", scriptManager.getAccountNickname());
            if (statement.executeUpdate(sqlStatement) >= 1) {return true;}
        }catch (SQLException e) {
            log(e);
        }
        return false;
    }


}

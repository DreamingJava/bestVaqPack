/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaqpack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author OWNER
 */
public class VaqPack extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello Dreaming Java");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mytest?" + "user=root&password=xeno");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM untitled");
            ResultSetMetaData metadata = resultSet.getMetaData();
            int colCount = metadata.getColumnCount();
            System.out.println("This is a connection test\n");
            for (int i = 1; i <= colCount; i++) {
                System.out.printf("%-8s\t", metadata.getColumnName(i));
                
            }
            while (resultSet.next()) {
                for (int i = 1; i <= colCount; i++) {
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                    System.out.println();
                }

            }
        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (Exception exception) {
                exception.printStackTrace();

            }
        }

    }

}

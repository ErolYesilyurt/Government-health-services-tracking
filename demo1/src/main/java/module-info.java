module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.classveri;
    opens com.example.demo1.classveri to javafx.fxml;
}
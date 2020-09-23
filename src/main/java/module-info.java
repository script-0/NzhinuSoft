module com.nzhinusoft.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.nzhinusoft.test to javafx.fxml;
    opens com.nzhinusoft.test.controllers to javafx.fxml;
    exports com.nzhinusoft.test;
    exports com.nzhinusoft.test.controllers;
}
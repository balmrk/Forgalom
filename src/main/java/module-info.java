module grafikus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires com.fasterxml.jackson.databind;
    opens hu.gamf to javafx.fxml;
    exports hu.gamf;
}

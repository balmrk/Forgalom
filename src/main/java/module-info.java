module ufub6w {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.net.http;
    opens hu.gamf to javafx.fxml;
    exports hu.gamf;
}

package hu.gamf;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class GrafikusController {
    @FXML private Label lb1;
    @FXML private GridPane gp1;
    @FXML private TextField tfNév, tfKor, tfSúly;
    @FXML private TableView tv1;
    @FXML private TableColumn<AruClass, String> aruCol;
    @FXML private TableColumn<AruClass, String> katCol;
    @FXML private TableColumn<AruClass, String> nevCol;
    @FXML private TableColumn<AruClass, String> egysegCol;
    @FXML private TableColumn<AruClass, String> arCol;
    SessionFactory factory;
    @FXML void initialize(){
        ElemekTörlése();
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }
    void ElemekTörlése(){
        lb1.setVisible(false);  // Eltünteti, de a helyet még foglalja
        lb1.setManaged(false);  // A helyet is felszabadítja
        gp1.setVisible(false);
        gp1.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
    }
    @FXML protected void menuCreateClick() {
        ElemekTörlése();
        gp1.setVisible(true);
        gp1.setManaged(true);
    }
    void Create(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        AruClass aru=new AruClass(1,"1","1",1); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        session.save(aru);
        t.commit();
    }

    @FXML void bt1Click(){
        Create();
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Adatok beírva az adatbázisba");
    }
    @FXML protected void menuReadClick() {
        ElemekTörlése();
        tv1.setVisible(true);
        tv1.setManaged(true);
        tv1.getColumns().removeAll(tv1.getColumns());
        aruCol = new TableColumn("aru_kod");
        katCol = new TableColumn("kat_kod");
        nevCol = new TableColumn("nev");
        egysegCol = new TableColumn("egyseg");
        arCol = new TableColumn("ar");

        tv1.getColumns().addAll(aruCol, katCol, nevCol, egysegCol,arCol);
        aruCol.setCellValueFactory(new PropertyValueFactory<>("aru_kod"));
        katCol.setCellValueFactory(new PropertyValueFactory<>("kat_kod"));
        nevCol.setCellValueFactory(new PropertyValueFactory<>("nev"));
        egysegCol.setCellValueFactory(new PropertyValueFactory<>("egyseg"));
        arCol.setCellValueFactory(new PropertyValueFactory<>("ar"));
        tv1.getItems().clear();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        List<AruClass> lista = session.createQuery("FROM AruClass").list();
        for(AruClass aru:lista)
            tv1.getItems().add(aru);
        System.out.println();
        t.commit();
    }
    @FXML protected void menuUpdateClick() {}
    @FXML protected void menuDeleteClick() {}
}

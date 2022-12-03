package hu.gamf;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

interface ParhuzLbab1 {
    String lab1valt(String s);
}
interface ParhuzLbab2 {
    String lab2valt(String s);
}
public class GrafikusController {
    public static String get79Resp, getAllResp;
    @FXML private Label lb1, get79, getall, lab1, lab2;
    @FXML private GridPane gp1, gp2, gpOlv2, gp3, gp4, gp5, gp6, gp7, parhuz;
    @FXML private TextField tfAru_kod, tfNév, tfEgyseg, tfAr, tfKat_kod, tfKat_kod2, tfNév2, tfOlv2, tfEgyseg2, tfAr2, tfName, tfEmail, tfGender, tfStatus, tfName2, tfEmail2, tfGender2, tfStatus2;
    @FXML private ComboBox tfAru_kod2, tfAru_kod3, tfId, cbOlv2;
    @FXML private RadioButton rbOlv21, rbOlv22, rbOlv23;
    @FXML ToggleGroup group;
    @FXML private CheckBox chbOlv21, chbOlv22, chbOlv23;
    @FXML private TableView tv1, tv2;
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
        gpOlv2.setVisible(false);
        gpOlv2.setManaged(false);
        tv1.setVisible(false);
        tv1.setManaged(false);
        tv2.setVisible(false);
        tv2.setManaged(false);
        gp2.setVisible(false);
        gp2.setManaged(false);
        gp3.setVisible(false);
        gp3.setManaged(false);
        gp4.setVisible(false);
        gp4.setManaged(false);
        gp5.setVisible(false);
        gp5.setManaged(false);
        gp6.setVisible(false);
        gp6.setManaged(false);
        gp7.setVisible(false);
        gp7.setManaged(false);
        parhuz.setVisible(false);
        parhuz.setManaged(false);
    }
    @FXML protected void menuCreateClick() {
        ElemekTörlése();
        gp1.setVisible(true);
        gp1.setManaged(true);
    }
    void Create(){
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        AruClass aru=new AruClass(Integer.parseInt(tfKat_kod.getText()),tfNév.getText(),tfEgyseg.getText(),Integer.parseInt(tfAr.getText()));
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

    public void menuOlvas2Click(ActionEvent event) {
        ElemekTörlése();
        gpOlv2.setVisible(true);
        gpOlv2.setManaged(true);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/forgalom", "root", "");
            ResultSet rs = con.createStatement().executeQuery("select *from kategoria");
            ObservableList data = FXCollections.observableArrayList();
            while(rs.next()){
                data.add(new String(rs.getString(2)));
            }
            cbOlv2.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
        }

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


    }
    public void btSzur(ActionEvent event) {

        ToggleGroup group = new ToggleGroup();
        rbOlv21.setToggleGroup(group);
        rbOlv22.setToggleGroup(group);
        rbOlv23.setToggleGroup(group);

        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        tv1.getItems().clear();
        //szöveg mező
        String kereses="FROM AruClass";
        String szoveg="";
        String radio ="";
        String check="";
        if (!tfOlv2.getText().isEmpty()){
            szoveg = tfOlv2.getText();
            kereses+=" WHERE nev LIKE :a";
        }else {
            szoveg="";
        }

        //================================
        //==========radiobutton===========
        //================================

        boolean rf= false;
        boolean tf= false;
        try {
            if (!tfOlv2.getText().isEmpty()){
                radio+=" AND ";
                tf=true;
            }else {
                radio+=" WHERE ";
            }
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            String radioValue = selectedRadioButton.getId();
            if (radioValue.equals("rbOlv21")){
                radio+="ar <200";
                rf=true;
            } else if (radioValue.equals("rbOlv22")) {
                radio+="ar >=200 AND ar <500";
                rf=true;
            }else if (radioValue.equals("rbOlv23")){
                radio+="ar >500";
                rf=true;
            }else{
                radio="";
            }
            System.out.println(radioValue);
            System.out.println(radio);
        }catch (Exception e){
            System.out.println("Nincs kiválasztva radioButton");
        };
        if (!rf){
            radio="";
        }

        //================================
        //============checkbox============
        //================================

        Boolean cf=false;
        if (tfOlv2.getText().isEmpty()){
            check=" WHERE ";
        }else {
            check=" AND ";
        }
        if(rf){
            check=" AND ";
        }
        if (chbOlv21.isSelected()){
            if (chbOlv22.isSelected() ||chbOlv23.isSelected()){
                check+="(egyseg = 'liter'";
            }else{
                check+="egyseg = 'liter'";
            }
            cf=true;
        }
        if (chbOlv22.isSelected()){
            if (chbOlv21.isSelected()){
                if (chbOlv23.isSelected()){
                    check += " OR egyseg='kg'";

                }else {
                    check += " OR egyseg='kg')";
                }
            }else{
                if (chbOlv23.isSelected()){
                    check+="(egyseg='kg'";

                }else {
                    check+="egyseg='kg'";
                }
            }
            cf=true;

        }
        if (chbOlv23.isSelected()){
            if (chbOlv21.isSelected() || chbOlv22.isSelected()) {
                check += " OR egyseg = 'darab')";
            }else {
                check += "egyseg = 'darab'";

            }
            cf=true;

        }
        if(!cf){
            check="";
        }
        //================================
        //============combobox============
        //================================
        int valcb=0;
        List<Object> cbl = cbOlv2.getItems();

        String combo="";
        if (cbOlv2.getValue() != null) {
            for (int i = 0; i < cbl.size(); i++) {
                //System.out.println(i + " : " + cbl.get(i));
                if (cbOlv2.getValue().toString() == cbl.get(i)) {
                    valcb = i + 1;
                }
            }
            if (!tf && !cf && !rf){
                combo+=" WHERE Kat_kod='"+valcb+"'";
            }else {
                combo+=" AND Kat_kod='"+valcb+"'";
            }
        }
        System.out.println("combo: "+combo);
        kereses+=radio+check+combo;
        System.out.println("Query: " + kereses);
        //Query
        Query q=session.createQuery(kereses);
        try{
            q.setString("a",'%'+szoveg+'%');
        }catch (Exception e){
            System.out.println("Nincs szöveges keresés");
        }

        List<AruClass> lista = q.list();
        System.out.println(lista.size());
        for(AruClass aru:lista)
            tv1.getItems().add(aru);
        System.out.println();
        t.commit();
    }

    @FXML protected void menuUpdateClick() { //????????????????????????????????????????????????????????????
        ElemekTörlése();
        gp2.setVisible(true);
        gp2.setManaged(true);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/forgalom", "root", "");
            ResultSet rs = con.createStatement().executeQuery("select *from aru");
            ObservableList data = FXCollections.observableArrayList();
            while(rs.next()){
                data.add(rs.getString(1));
            }
            tfAru_kod2.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML protected void menuDeleteClick() {
        ElemekTörlése();
        gp3.setVisible(true);
        gp3.setManaged(true);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/forgalom", "root", "");
            ResultSet rs = con.createStatement().executeQuery("select *from aru");
            ObservableList data = FXCollections.observableArrayList();
            while(rs.next()){
                data.add(rs.getString(1));
            }
            tfAru_kod3.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deladatmenu(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/forgalom", "root", "");
        String sql = "DELETE FROM aru WHERE `aru`.`aru_kod` = ?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tfAru_kod3.getValue().toString());
            pst.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText(tfAru_kod3.getValue().toString() + "-s Áru kódu adat törölve lett");
    }

    public void insadatmenu(ActionEvent event) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/forgalom", "root", "");
        String sql = "INSERT INTO `aru` (`aru_kod`, `kat_kod`, `nev`, `egyseg`, `ar`) VALUES (?, ?, ?, ?, ?)";
        List<String> hibak = new ArrayList<>();
        boolean hiba=false;
        if (tfAru_kod.getText().isEmpty()){
            hibak.add("Áru kód");
            hiba=true;
        }
        if (tfKat_kod.getText().isEmpty()){
            hibak.add("Kategória kód");
            hiba=true;
        }
        if (tfNév.getText().isEmpty()){
            hibak.add("Név");
            hiba=true;
        }
        if (tfEgyseg.getText().isEmpty()){
            hibak.add("Egység");
            hiba=true;
        }
        if (tfAr.getText().isEmpty()){
            hibak.add("Ár");
            hiba=true;
        }
        if(!hiba){
            try{
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(2, tfKat_kod.getText());
                pst.setString(3, tfNév.getText());
                pst.setString(4, tfEgyseg.getText());
                pst.setString(5, tfAr.getText());
                pst.setString(1, tfAru_kod.getText());
                pst.execute();
            }catch (Exception e){
                e.printStackTrace();
            }
            ElemekTörlése();
            lb1.setVisible(true);
            lb1.setManaged(true);
            lb1.setText("A megadott adatok hozzáadva az adatbázishoz");
        }else {
            insadatHiba(hibak);
        }

    }
    public void insadatHiba(List<String> hiba){
        String hiany="";
        for (int i=0;i<hiba.size();i++){
            hiany+=hiba.get(i);
            if(i!=hiba.size()-1){
                hiany+=", ";
            }
        }
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Minden adatot meg kell adni! ("+hiany+")");
    }
    public void modadatmenu(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/forgalom", "root", "");
        String sql = "UPDATE `aru` SET `kat_kod` = ?, `nev` = ?, `egyseg` = ?, `ar` = ? WHERE `aru`.`aru_kod` = ?";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tfKat_kod2.getText());
            pst.setString(2, tfNév2.getText());
            pst.setString(3, tfEgyseg2.getText());
            pst.setString(4, tfAr2.getText());
            pst.setString(5, tfAru_kod2.getValue().toString());
            pst.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("Az adat módosítva lett az adatbázisban");
    }
    //==================================================================================================================
    //==================================================================================================================
    //============================================GOREST.IN=============================================================
    //==================================================================================================================
    //==================================================================================================================
    static String token = "5d150c6fe2aea1dcfbc9ce62d2c0e7f990c94fbd5047edbd889d0de52708e07b";
    static HttpsURLConnection connection;
    static void segéd1(){
        // Setting Header Parameters
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
    }
    static void segéd2(String params) throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        wr.write(params);
        wr.close();
        connection.connect();
    }
    static void segéd3(int code, String id) throws IOException {
        int statusCode = connection.getResponseCode();   // Getting response code
        System.out.println("statusCode: "+statusCode);
        if (statusCode == code) {     // If responseCode is code, data fetch successful
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            while ((readLine = in.readLine()) != null) {   // Append response line by line
                jsonResponseData.append(readLine);
            }
            in.close();
            if (id!=null) {
                get79Resp = jsonResponseData.toString();
                System.out.println("List of users: " + get79Resp);
            }else{
                getAllResp = jsonResponseData.toString();
                System.out.println("User 79: " + getAllResp);
            }
        } else {
            System.out.println("Hiba!!!");
        }
        connection.disconnect();
    }
    static void RestGET(String ID) throws IOException {  // Get a list of users
        System.out.println("\nGET...");
        String url = "https://gorest.co.in/public/v1/users";
        if(ID!=null)
            url=url+"/"+ID;
        URL usersUrl = new URL(url); // Url for making GET request
        connection = (HttpsURLConnection) usersUrl.openConnection();
        connection.setRequestMethod("GET");  // Set request method
        if(ID!=null)
            connection.setRequestProperty("Authorization", "Bearer " + token);
        segéd3(HttpsURLConnection.HTTP_OK,ID);
    }
    @FXML protected void rest1GET() throws IOException {
        ElemekTörlése();
        gp7.setVisible(true);
        gp7.setManaged(true);

        RestGET(null);
        RestGET("79");

        get79.setText(get79Resp);
        getall.setText(getAllResp);
    }
    @FXML protected void rest1CreateClick() {
        ElemekTörlése();
        gp4.setVisible(true);
        gp4.setManaged(true);
    }
    @FXML protected void rest1UpdateClick() {
        ElemekTörlése();
        gp5.setVisible(true);
        gp5.setManaged(true);
    }
    @FXML protected void rest1DeleteClick() {
        ElemekTörlése();
        gp6.setVisible(true);
        gp6.setManaged(true);
    }

    //==================================================================================================================
    //==================================================================================================================
    //===============================================REST 2=============================================================
    //==================================================================================================================
    //==================================================================================================================



    //==================================================================================================================
    //==================================================================================================================
    //============================================PÁRHUZAMOS PROG=======================================================
    //==================================================================================================================
    //==================================================================================================================
    public void parhuzbtClick(ActionEvent event){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            ParhuzLbab1 lab1str = (a) -> {
                String str1 = lab1.getText();
                String str2 = str1 + a;
                return str2;
            };
            lab1.setText(lab1str.lab1valt("A"));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
            ParhuzLbab2 lab2str = (a) -> {
                String str1 = lab2.getText();
                String str2 = str1 + a;
                return str2;
            };
            lab2.setText(lab2str.lab2valt("B"));
        }));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
    }

    public void parhuzmenuClick(ActionEvent event) {
        ElemekTörlése();
        lb1.setVisible(true);
        lb1.setManaged(true);
        lb1.setText("A felső label 1 mp-enként, míg az alsó 2 mp-enként változik");
        parhuz.setVisible(true);
        parhuz.setManaged(true);
    }

}

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Controller implements Initializable {
    private static ArrayList<MenuItem> listOfComps = new ArrayList<MenuItem>();
    private boolean viewCompClicked = true ;



    protected String compName;
    protected String URL;
    protected LocalDate date;
    protected String choice;

    protected String name;
    protected String id;
    protected String major;
    protected String rank;

    @FXML
    private Pane PaneAddStudExtra;

    @FXML
    private MenuItem HiItem;

    @FXML
    private VBox CompListPane;

    @FXML
    private Pane PaneAddComp;

    @FXML
    private Pane PaneAddStud;

    @FXML
    private TableColumn<Student,String> stdIDCol ;

    @FXML
    private TableView<Student> TableSoloStd;

    @FXML
    private ScrollPane paneViewStdInfoTeam;

    @FXML
    private TableView<Student> TableTeamStd;

//    @FXML
//    private Pane ViewCompsPane;

    @FXML
    private Pane ViewSpicficCompPane;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddComp;

    @FXML
    private Button btnAddStud;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnClear2;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnViewCompetition;

    @FXML
    private Label lbCompDate;

    @FXML
    private Label lbCompLink;

    @FXML
    private Hyperlink lbCompHLink;

    @FXML
    private Label lblAddComp;

    @FXML
    private Label lblAddComp1;

    @FXML
    private Label lblAddComp11;

    @FXML
    private Label lblAddComp111;

    @FXML
    private Label lblAddComp2;

    @FXML
    private Label lblState;
//    @FXML
//    private Label lblState1;

    @FXML
    private MenuButton menubtn;

    @FXML
    private ScrollPane paneViewStdInfo;

    @FXML
    private RadioButton radStudBase1;

    @FXML
    private TableColumn<Student, Integer> serialNumCol;

    @FXML
    private TableColumn<Student, String> stdMajorCol;

    @FXML
    private TableColumn<Student, String> stdNameCol;

    @FXML
    private TableColumn<Student, String> stdRankCol;
//-------------------------------------------------------------------
    @FXML
    private TableColumn<Student, String> teamNameCol;

    @FXML
    private TableColumn<Student, String> teamNumCol;

    @FXML
    private TableColumn<Student, Integer> serialNumCol1;

    @FXML
    private TableColumn<Student, String> stdIDCol1;

    @FXML
    private TableColumn<Student, String> stdMajorCol1;

    @FXML
    private TableColumn<Student, String> stdNameCol1;

    @FXML
    private TableColumn<Student, String> stdRankCol1;


    @FXML
    private TextField txtAddComp;

    @FXML
    private DatePicker txtAddDate;

    @FXML
    private TextField txtAddStud;

    @FXML
    private TextField txtAddStudID;

    @FXML
    private TextField txtAddStudMajor;

    @FXML
    private TextField txtAddStudRank;

    @FXML
    private TextField txtAddURLComp;

    @FXML
    private MenuButton comboCompABD;

    @FXML
    private TextField txtAddTeamNum;
    @FXML
    private TextField txtAddTeamName;
    @FXML
    private Label lblTeamNum;
    @FXML
    private Label lblTeamName;

    protected String competitionName;
    protected String teamNum;
    protected String teamName;

    @FXML
    void menuButtonAction(ActionEvent event) {

    }




        //    @FXML
//    protected void handleSubmitButtonRegister() throws IOException {
//        //
//        //Here I want to invoke gotoRegister
//        CompetitionApplication.getInstance().gotoRegister();
//    }
        @Override
        public void initialize(java.net.URL url, ResourceBundle rb) {
            // TODO
        }

        @FXML
        private void btnAddCompAction(ActionEvent event) {
            PaneAddStud.setVisible(false);
//            ViewCompsPane.setVisible(false);
            ViewSpicficCompPane.setVisible(false);
            lblState.setVisible(false);
            txtAddComp.setText("");
            txtAddURLComp.setText("");
            txtAddDate.setValue(null);
            radStudBase1.setSelected(false);


            PaneAddComp.setVisible(true);
            txtAddDate.setEditable(false);

        }

        @FXML
        private void handleButtonAction(ActionEvent event) {

        }

        @FXML
        private void btnClearAction(ActionEvent event) {

            txtAddComp.setText("");
            txtAddDate.setValue(null);
            txtAddURLComp.setText("");
            txtAddComp.setText("");
            lblState.setTextFill(Color.BLACK);
        }

            @FXML
            private void btnViewCompetitionAction(ActionEvent event3) { //*****


                ViewSpicficCompPane.setVisible(true);
                PaneAddComp.setVisible(false); // create method that ensure that all panes are un visible
                PaneAddStud.setVisible(false);

                paneViewStdInfoTeam.setVisible(false);
                paneViewStdInfo.setVisible(false);
                TableTeamStd.setVisible(false);
                TableSoloStd.setVisible(false);


                serialNumCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("serial"));
                stdIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                stdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                stdMajorCol.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
                stdRankCol.setCellValueFactory(new PropertyValueFactory<Student, String>("rank"));
// ----- team col :
                serialNumCol1.setCellValueFactory(new PropertyValueFactory<Student, Integer>("serial"));
                stdIDCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
                stdNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
                stdMajorCol1.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
                stdRankCol1.setCellValueFactory(new PropertyValueFactory<Student, String>("rank"));

                teamNumCol.setCellValueFactory(new PropertyValueFactory<Student, String>("teamNum"));
                teamNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("teamName"));


//                if (viewCompClicked) {
//                    System.out.println(MySystem.compArray.size());

                menubtn.getItems().clear(); // Should clear the menu
                menubtn.setText(null);
                lbCompHLink.setText(null);
                lbCompDate.setText(null);
                    for (int i = 0; i < MySystem.compArray.size(); i++) {
                        MenuItem mt = new MenuItem(MySystem.compArray.get(i).compName);
                        menubtn.getItems().add(mt);
                        listOfComps.add(mt);
//                        viewCompClicked = false; no need?

                        final int j = i ;


                        //View a spicific comp after cliciking its name
                        mt.setOnAction( event -> {
                            menubtn.setText(mt.getText());
                            lbCompHLink.setText( MySystem.compArray.get(j).compURL );
                            lbCompDate.setText( MySystem.compArray.get(j).CompDate );

                            Competition c = Competition.search(mt.getText()); // gave me obj
                            ObservableList<Student> items = FXCollections.observableArrayList();

                            for(Student s : c.stdArray){
                                items.add(s);
                            }

                            if(MySystem.compArray.get(j).compTypeStd){
                                TableSoloStd.setItems(items);

                                TableSoloStd.setRowFactory(tv -> {
                                    TableRow<Student> row = new TableRow<>();
                                    row.setOnMouseClicked( event2 -> {
                                        if (! row.isEmpty() && event2.getButton()== MouseButton.PRIMARY
                                                && event2.getClickCount() == 2) {

                                            Student clickedRow = row.getItem();
                                            printRow(clickedRow);
                                            MySystem.OpenEmail(clickedRow.id , menubtn.getText());
                                            //^ TEST
                                        }
                                    });
                                    return row ;
                                });
                                paneViewStdInfoTeam.setVisible(false);
                                TableTeamStd.setVisible(false); //solo! provide if here

                                paneViewStdInfo.setVisible(true);
                                TableSoloStd.setVisible(true); //solo! provide if here
                            }
                            else{
                                TableTeamStd.setItems(items);

                                TableTeamStd.setRowFactory(tv -> {
                                    TableRow<Student> row = new TableRow<>();
                                    row.setOnMouseClicked( event2 -> {
                                        if (! row.isEmpty() && event2.getButton()== MouseButton.PRIMARY
                                                && event2.getClickCount() == 2) {

                                            Student clickedRow = row.getItem();
                                            printRow(clickedRow);
                                            MySystem.OpenEmail(clickedRow.id , menubtn.getText());
                                            //^ TEST
                                        }
                                    });
                                    return row ;
                                });

                                paneViewStdInfo.setVisible(false);
                                TableSoloStd.setVisible(false); //solo! provide if here

                                paneViewStdInfoTeam.setVisible(true);
                                TableTeamStd.setVisible(true); //solo! provide if here


                            }
                        });


                    }


//                }

        }
        private void printRow(Student item) {
            System.out.println(item.toString());
        }

        @FXML
        void HLAction(ActionEvent event) {
            Platform.setImplicitExit(false);
            WebViewExample wv = new WebViewExample();
            update(wv , "");
//            wv.setCompetition(Competition.search(menubtn.getText())); // i need comp obj ! here
            try{//Test
                wv.main( null);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }


        }

        public void update(Observable o, Object arg) {
            Platform.runLater(new Runnable() {
                public void run() {
                    WebViewExample wv = new WebViewExample();
                    wv.setCompetition(Competition.search(menubtn.getText()));
                    wv.start(new Stage());
                }
            });
    }



        private EventHandler<ActionEvent> ListOfCompBtnsAction(ActionEvent event){
            return null;
        }

    @FXML
    void MOSAction(MouseEvent event) { /// i think i added to just one pane not both
//                event.
        System.out.println("Mos Hi");

    }


        @FXML
        private TextField txtAddDateComp;

        @FXML
        private DatePicker pick;






        //    @FXML
//    protected void handleSubmitButtonRegister() throws IOException {
//        //
//        //Here I want to invoke gotoRegister
//        CompetitionApplication.getInstance().gotoRegister();
//    }



        // replace pick --> txtAddDate
        @FXML
        private void btnRegisterAction(ActionEvent event) throws IOException, InvalidFormatException, ParseException {

            String m=txtAddDate.getValue().getMonthValue()+"";
            String d=txtAddDate.getValue().getDayOfMonth()+"";
            if(d.length()==1)
                d="0"+d;
            if(m.length()==1)
                m="0"+m;
            //System.out.println(pick.getValue().getYear()+"-"+m+"-"+d);
            String t1 =txtAddComp.getText();
            String t2 =txtAddURLComp.getText();
            //String t3 =txtAddDateComp.getText();
            String t3=txtAddDate.getValue().getYear()+"-"+m+"-"+d;





//    		System.out.println(MySystem.getCompArray().toString());
//    		System.out.println(MySystem.getCompArray().size());

            boolean flag=true;
            System.out.println(MySystem.compArray.toString());
            for (Competition c : MySystem.compArray) { // will check whiter this competition exists before or not
                System.out.println(c.compName);
                //caseupper
                System.out.println(t1 +" | "+ c.compName);
                if (t1.equals(c.compName)) { //*
                    System.out.println("The competition is already added. Please re-enter the competition name");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Addidtion faild");
                    alert.setHeaderText("The competition is already added. Please re-enter the competition name");
                    alert.setContentText("");
                    alert.showAndWait();
                    flag=false;
                }
            }
            if(flag){
                MySystem.addCompetition(t1,t2,t3,!radStudBase1.isSelected());
                lblState.setVisible(true);
                lblState.setText("You're register successfully");
                lblState.setTextFill(Color.GREEN);

            }
            txtAddComp.setText("");
            txtAddURLComp.setText("");
            txtAddDate.setValue(null);
            radStudBase1.setSelected(false);



//    		}
        }





//        @FXML
//        void HLAction(ActionEvent event) {
//            Platform.setImplicitExit(false);
//            update(new WebViewExample() , "");
//            WebViewExample.main( null);
//
//        }

//        public void update(Observable o, Object arg) {
//            Platform.runLater(new Runnable() {
//                public void run() {
//                    new WebViewExample().start(new Stage());
//                }
//            });
//        }
//______________________________________________________
@FXML
private void btnAddStudAction(ActionEvent event) throws IOException {
    comboCompABD.setText(null); // Let combo box choose first option which is empty
    txtAddStud.setText("");
    txtAddStudID.setText("");
    txtAddStudMajor.setText("");
    txtAddStudRank.setText("");
    txtAddTeamNum.setText("");
    txtAddTeamName.setText("");

    PaneAddStud.setVisible(true);
    PaneAddComp.setVisible(false);
    ViewSpicficCompPane.setVisible(false);
    PaneAddStudExtra.setVisible(false);
    txtAddStud.setText(null);

// Already read before in the consil menu I think
    MySystem.read();

    // Complete this
        /*

            we need to get the data(competition names) from Excel sheet


         */
    comboCompABD.getItems().clear(); // Should clear the menu
    for (int i = 0; i < MySystem.compArray.size(); i++) {
        MenuItem mt = new MenuItem(MySystem.compArray.get(i).compName);
        comboCompABD.getItems().add(mt);
        listOfComps.add(mt);

        mt.setOnAction( eventABD -> { //choose a spicific comp to registe ! allert
            comboCompABD.setText(mt.getText());
            Competition c = Competition.search(mt.getText()); // gIve me obj
            boolean ABDisTeam = !c.compTypeStd;
            if(ABDisTeam){
                PaneAddStudExtra.setVisible(true);
            }
            else{
                PaneAddStudExtra.setVisible(false);
            }

        });
    }
}





    @FXML
    private void btnClearAction2(ActionEvent event) {

        // comboComp.setValue("     "); // Let combo box choose first option which is empty
        txtAddStud.setText("");
        txtAddStudID.setText("");
        txtAddStudMajor.setText("");
        txtAddStudRank.setText("");

        txtAddTeamNum.setText("");
        txtAddTeamName.setText("");

    }



    @FXML
    private void btnAddAction(ActionEvent event) throws IOException, InvalidFormatException {

        competitionName =  comboCompABD.getText(); // Change line 102 to String + Make sure the method is correct
        name = txtAddStud.getText();
        id = txtAddStudID.getText();
        major = txtAddStudMajor.getText().toUpperCase();
        rank = txtAddStudRank.getText();
        if(rank.equals("")){
            rank="-";
        }

        if(!Competition.search(competitionName).compTypeStd){ //team based
            teamNum = txtAddTeamNum.getText();
            teamName = txtAddTeamName.getText();
            try{
                MySystem.addStudent(competitionName, name, id, major, rank, teamNum, teamName);
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Addidtion faild");
                alert.setHeaderText("The student is already added. Please re-enter the student data");
                alert.setContentText("");
                alert.showAndWait();
            }
        }
        else {
            try{
                MySystem.addStudent(competitionName, name, id, major, rank , "-" , "-");
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Addidtion faild");
                alert.setHeaderText("The student is already added. Please re-enter the student data");
                alert.setContentText("");
                alert.showAndWait();
            }
        }
        // Reset the value ..
        comboCompABD.setText(null); // Let combo box choose first option which is empty
        txtAddStud.setText("");
        txtAddStudID.setText("");
        txtAddStudMajor.setText("");
        txtAddStudRank.setText("");
        txtAddTeamNum.setText("");
        txtAddTeamName.setText("");
        //Successfully message
        lblState.setText("You're added successfully");
        lblState.setTextFill(Color.GREEN);
    }
    @FXML
    private void panestudentAction(MouseEvent event) {

        if (comboCompABD.getText() != "") {

            txtAddStud.setVisible(true);
            txtAddStudID.setVisible(true);
            txtAddStudMajor.setVisible(true);
            txtAddStudRank.setVisible(true);
            Competition cpt = Competition.search(comboCompABD.getText());
            boolean compTypeStdTemp = cpt.compTypeStd ;

            if (compTypeStdTemp == false) {

                txtAddTeamNum.setVisible(true);
                txtAddTeamName.setVisible(true);

            } else if (compTypeStdTemp == true) {
                txtAddTeamNum.setVisible(false);
                txtAddTeamName.setVisible(false);

            }
    }


    }
}






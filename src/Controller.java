import javafx.application.Application;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private ComboBox<?> comboComp;

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

    @FXML
    private MenuButton menubtn;

    @FXML
    private ScrollPane paneViewStdInfo;

    @FXML
    private RadioButton radStudBase1;

    @FXML
    private RadioButton radStudBase2;

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
    void menuButtonAction(ActionEvent event) {
        System.out.println("hello menu");
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

//        PaneViewComp.setVisible(false);

            PaneAddComp.setVisible(true);

        }

        @FXML
        private void handleButtonAction(ActionEvent event) {

        }

        @FXML
        private void btnRegisterAction(ActionEvent event) throws IOException {

            compName = txtAddComp.getText();
            URL = txtAddURLComp.getText();
            date = txtAddDate.getValue(); //Chack the format

            if (radStudBase1.isSelected()) {
                choice = "YES";
            } else if (radStudBase2.isSelected()) {
                choice = "NO";
            }

            // Call the function >> Don't forget to fix the parameter ..
            CompetitionApplication.getInstance().addCompetition(compName, URL, date, choice);

            lblState.setText("You're register successfully");
            lblState.setTextFill(Color.GREEN);

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
        private void btnAddStudAction(ActionEvent event) {

            PaneAddStud.setVisible(true);
//            ViewCompsPane.setVisible(true);

//        PaneViewComp.setVisible(false);

            PaneAddComp.setVisible(false);
//            ViewCompsPane.setVisible(false);
            ViewSpicficCompPane.setVisible(false);


        }

        @FXML
        private void btnClearAction2(ActionEvent event) {

            txtAddStud.setText("");
            txtAddStudID.setText("");
            txtAddStudMajor.setText("");
            txtAddStudRank.setText("");
        }

        @FXML
        private void btnAddAction(ActionEvent event) throws IOException {

            //        comboComp.getItems().add("Choice 1");

            name = txtAddStud.getText();
            id = txtAddStudID.getText();
            major = txtAddStudMajor.getText().toUpperCase();
            rank = txtAddStudRank.getText();



            //Call function ..
            CompetitionApplication.getInstance().addStudent(name, id, major, rank);

            lblState.setText("You're added successfully");
            lblState.setTextFill(Color.GREEN);
            }
            @FXML
            private void btnViewCompetitionAction(ActionEvent event2) {
                ViewSpicficCompPane.setVisible(true);
//                ViewCompsPane.setVisible(false);
                PaneAddComp.setVisible(false);
                PaneAddStud.setVisible(false);

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


                if (viewCompClicked) {
                    for (int i = 0; i < MySystem.compArray.size(); i++) {
                        MenuItem mt = new MenuItem(MySystem.compArray.get(i).compName);
                        final int j = i ;
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

                                paneViewStdInfoTeam.setVisible(false);
                                TableTeamStd.setVisible(false); //solo! provide if here

                                paneViewStdInfo.setVisible(true);
                                TableSoloStd.setVisible(true); //solo! provide if here
                            }
                            else{
                                TableTeamStd.setItems(items);

                                paneViewStdInfo.setVisible(false);
                                TableSoloStd.setVisible(false); //solo! provide if here

                                paneViewStdInfoTeam.setVisible(true);
                                TableTeamStd.setVisible(true); //solo! provide if here
                            }
                        });


                        menubtn.getItems().add(mt);
                        listOfComps.add(mt);
                        viewCompClicked = false;
                    }


                }

        }

        @FXML
        void HLAction(ActionEvent event) {
            Platform.setImplicitExit(false);
            update(new WebViewExample() , "");
            WebViewExample.main( null);

        }

        public void update(Observable o, Object arg) {
            Platform.runLater(new Runnable() {
                public void run() {
                    new WebViewExample().start(new Stage());
                }
            });
    }

        private EventHandler<ActionEvent> ListOfCompBtnsAction(ActionEvent event){
            return null;
        }




}

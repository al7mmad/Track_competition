
import java.beans.EventHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewExample extends Application implements Observable {
	Competition browseComp ;
	String browseCompURL;
	private static volatile boolean javaFxLaunched = false;


	public static void main(String[] args) {
		launch();
	}

	public static void myLaunch(Class<? extends Application> applicationClass) {
		if (!javaFxLaunched) { // First time
			Platform.setImplicitExit(false);
			new Thread(()->Application.launch(applicationClass)).start();
			javaFxLaunched = true;
		} else { // Next times
			Platform.runLater(()->{
				try {
					Application application = applicationClass.newInstance();
					Stage primaryStage = new Stage();
					application.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

	public void setCompetition(Competition comp ) {
		/*
		 * browseComp = comp; Label UrlLabel = new Label("google.com");
		 */

		browseComp = comp;
		browseCompURL =  "https://twitter.com/CyberhubSa"  ; // comp.compURL ; should be
		// worked but not fine
	}

	public void start(Stage Stage) {

		setCompetition(browseComp);
		Image left = new Image(getClass().getResourceAsStream("left.png"));
		Image right = new Image(getClass().getResourceAsStream("right.png"));
		Image refresh = new Image(getClass().getResourceAsStream("refresh.png"));
		ImageView leftV = new ImageView(left);
		leftV.setFitHeight(10);
		leftV.setFitWidth(10);
		ImageView rightV = new ImageView(right);
		rightV.setFitHeight(10);
		rightV.setFitWidth(10);
		ImageView refreshV = new ImageView(refresh);
		refreshV.setFitHeight(10);
		refreshV.setFitWidth(10);

		Stage.setTitle("Browse");
		Button backBt = new Button();
		backBt.setGraphic(leftV);
		backBt.setMinSize(15, 15);
		Button forwardBT = new Button();
		forwardBT.setGraphic(rightV);
		forwardBT.setMinSize(15, 15);
		Button refreshBt = new Button();
		refreshBt.setGraphic(refreshV);
		refreshBt.setMinSize(15, 15);
		WebView webView = new WebView();
		// Label UrlLabel = new Label("http://ultrahack.org/aiot-hackathon-stc");
		Label UrlLabel = new Label(browseCompURL);

		BorderPane borderPane = new BorderPane();
		setCompetition(browseComp);
		webView.getEngine();
		webView.getEngine().load(browseCompURL);
		webView.getEngine();
		UrlLabel.textProperty().bind(webView.getEngine().locationProperty());

		backBt.setOnAction((event) -> {
			try {
				WebHistory history;
				history = webView.getEngine().getHistory();
				ObservableList<WebHistory.Entry> entries = history.getEntries();
				history.go(-1);
				UrlLabel.setText(entries.get(history.getCurrentIndex()).getUrl());
			} catch (Exception e) {
	
			}

		});

		forwardBT.setOnAction((event) -> {
			try {
				WebHistory history;
				history = webView.getEngine().getHistory();
				ObservableList<WebHistory.Entry> entries = history.getEntries();
				history.go(1);
				UrlLabel.setText(entries.get(history.getCurrentIndex()).getUrl());
			} catch (Exception e) {
			}

		});

		refreshBt.setOnAction((event) -> {
			try {
				WebHistory history;
				history = webView.getEngine().getHistory();
				ObservableList<WebHistory.Entry> entries = history.getEntries();
				webView.getEngine().reload();
				UrlLabel.setText(entries.get(history.getCurrentIndex()).getUrl());
			} catch (Exception e) {
			}

		});
		VBox Vbox = new VBox();
		HBox buttons = new HBox(5);
		UrlLabel.setMinSize(50, 20);
		buttons.getChildren().addAll(backBt, refreshBt, forwardBT);
		Vbox.getChildren().addAll(UrlLabel, buttons);
		Vbox.setAlignment(Pos.CENTER);
		borderPane.setTop(Vbox);
		borderPane.setCenter(webView);

//		Scene scene = new Scene(borderPane, 1920, 1080);
		Scene scene = new Scene(borderPane, 750, 700);


		Stage.setScene(scene);
		Stage.show();

	}

	@Override
	public void addListener(InvalidationListener invalidationListener) {

	}

	@Override
	public void removeListener(InvalidationListener invalidationListener) {

	}
}
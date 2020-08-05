package test;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class TestMainController implements Initializable {
	
	@FXML BorderPane homepi_borderPane;
	@FXML Circle mem_picture;
	@FXML Label mem_nickName;
	@FXML Label mem_stateMsg;
	@FXML Label homepi_view;
	@FXML Label homepi_rank;
	@FXML Button home_btn;
	@FXML Button board_btn;
	@FXML Button diary_btn;
	@FXML Button picture_btn;
	@FXML Button guest_btn;
	@FXML Button setting_btn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	@FXML public void home_btnClicked(ActionEvent event) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/homepage_Home.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		homepi_borderPane.setCenter(root);
	}

}

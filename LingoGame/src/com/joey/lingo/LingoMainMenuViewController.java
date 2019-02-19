package com.joey.lingo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;

public class LingoMainMenuViewController {
	
	
	@FXML private ChoiceBox<String> fxLanguageChoice;
	@FXML private Button fxStartGame;
	
	@FXML
	private void initialize() {
		fxLanguageChoice.setItems(FXCollections.observableArrayList("English", "Dutch"));
	}
	
	@FXML
	private void onClickStartGame() {
	
		String selectedChoice = fxLanguageChoice.getSelectionModel().getSelectedItem();
		
		/*
		 * Check which & if language is selected & then choose random word from that array
		 */
		if(selectedChoice == "English") {
			LingoViewController.lingoWord = LingoWords.getRandomWord(LingoWords.englishLingoWords);
			LingoViewController.languageArray = LingoWords.englishLingoWords;
		} else if(selectedChoice == "Dutch") {
			LingoViewController.lingoWord = LingoWords.getRandomWord(LingoWords.dutchLingoWords);
			LingoViewController.languageArray = LingoWords.dutchLingoWords;
		} else {
			showSelectLanguage();
		}
		
		//call showLingoView from LingoApp class.
		LingoApp.showLingoView();
	}
	
	private void showSelectLanguage() {
			Alert setLanguage = new Alert(AlertType.INFORMATION);
			setLanguage.setTitle("No Language Selected");
			setLanguage.setHeaderText("Select word language");
			setLanguage.setContentText("Please select a word language to play with!");
			setLanguage.showAndWait();			
	}
}

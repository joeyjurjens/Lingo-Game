package com.joey.lingo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LingoViewController {
	
	//public variables are being set from LingoMainMenuViewController
	public static String[] languageArray;
	public static String lingoWord = "";
	private int attemptsLeft = 10;
	
	@FXML private Text fxLingoWord;
	@FXML private TextField fxUserGuess;
	@FXML private Button fxButtonGuess;
	@FXML private Label fxLabelAttemptsLeft;
	@FXML private Button fxRestartGame;

	@FXML
	private void onClickButtonGuess() {
		
		// for me only - users won't see.
		System.out.println(lingoWord);
		
		// if checkUserWord returns true, do the needed checks.
		if(isValidWord(fxUserGuess.getText())) {
			// call function to compare entered word.
			compareUserWordWithLingoWord();
			
		}
	}

	/*
	 * This method will loop through the user word & compare the current char index with the lingo word char index
	 * If it matches, it will replace the * with the actual char.
	 */
	private void compareUserWordWithLingoWord() {
		String userWord = fxUserGuess.getText();
		StringBuilder currentGuessedLingoWord = new StringBuilder(fxLingoWord.getText());
		
		for(int i = userWord.length() - 1; i >= 0; i--) {
			if(userWord.charAt(i) == lingoWord.charAt(i)) {
				currentGuessedLingoWord.setCharAt(i, lingoWord.charAt(i));
				fxLingoWord.setText(currentGuessedLingoWord.toString());
			}
		}
		
		CheckIfFullWordGuessed();
		updateAttempts();
		hasAttemptsLeft();
		
	}
	
	
	/*
	 * looping through the lingo word to see if there's any "*" left
	 * if not, it will change red text to green & shop a WIN popup.
	*/
	private void CheckIfFullWordGuessed() {
		
		// "*" means word isn't guessed yet.
		if(fxLingoWord.getText().contains("*")) {
			// do nothing
		} else {
			winAlert();
		}
		
		
	}
	
	
	/*
	 * This game plays only with six-letter words.
	 * If user entered one with more or less, it will tell the user.
	 * If user entered anything else than A - Z, it will also fail.
	 * Attempts won't decrease, if invalid word.
	 */
	private boolean isValidWord(String userWord) {
		
		/*
		 * [a-zA-Z] a through zor A through Z, inclusive (range) 
		 */
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");  
		Matcher matcher = pattern.matcher(userWord);
		
		if(userWord.length() != 6 || !matcher.find()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error with userWord!");
			alert.setHeaderText("Word lenght does not match the required lenght");
			alert.setContentText("Please enter a six-letter word." + userWord);
			alert.showAndWait();
			return false;
		}
		return true;
	}
	
	
	/*
	 * This method will update the attempts at the bottom of program.
	 */
	private void updateAttempts() {
		attemptsLeft--;
		String currentAttempts = fxLabelAttemptsLeft.getText();
		currentAttempts = "ATTEMPTS LEFT: " + attemptsLeft;
		fxLabelAttemptsLeft.setText(currentAttempts);

	}	
	
	
	/*
	 * Checking if there's any attempts left.
	 */
	private boolean hasAttemptsLeft() {
		if(attemptsLeft <= 0) {
			gameOverAlert();
			return false;
		}
		
		return true;
	}
	
	
	/*
	 * This method changes the text color to green if the word is guessed.
	 */
	private void changeTextColorToGreen() {
		fxLingoWord.setFill(Color.LIME.brighter());
	}

	
	/*
	 * Alert if user won.
	 */
	private void winAlert() {
		changeTextColorToGreen();
		Alert winAlert = new Alert(AlertType.CONFIRMATION);
		winAlert.setHeaderText("WINNER!");
		winAlert.setContentText("You have guessed the word before your attempts ran out!");
		winAlert.showAndWait();		
	}
	
	
	/*
	 * Alert if game over
	 */
	private void gameOverAlert() {
		Alert gameOverAlert = new Alert(AlertType.INFORMATION);
		gameOverAlert.setHeaderText("Game Over!");
		gameOverAlert.setContentText("You don't have any attempts left.");
		gameOverAlert.showAndWait();			
	}	
	
	
	/*
	 * Reset user guess text if user restart the game / made a guess
	 */
	@FXML
	private void clearUserGuessText() {
		fxUserGuess.setText("");
	}
	
	
	/*
	 * Changes the lingo word color with "******" back to red
	 */
	private void changeTextColorToRed() {
		fxLingoWord.setFill(Color.rgb(255, 0, 0));
	}		
	
	
	/*
	 * This will restart the game & set everything to normal.
	 */
	@FXML
	private void restartGame() {
		lingoWord = LingoWords.getRandomWord(languageArray);
		attemptsLeft = 10;
		String currentAttempts = "ATTEMPTS LEFT: " + attemptsLeft;
		fxLabelAttemptsLeft.setText(currentAttempts);
		fxLingoWord.setText("******");
		clearUserGuessText();
		changeTextColorToRed();
	}
}

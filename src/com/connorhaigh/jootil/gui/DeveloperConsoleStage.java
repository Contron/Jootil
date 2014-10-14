package com.connorhaigh.jootil.gui;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.connorhaigh.jootil.beans.Console;
import com.connorhaigh.jootil.utilities.Fonts;

public class DeveloperConsoleStage extends Stage
{
	/**
	 * Creates a new developer console stage.
	 * @param stage the owner of the stage
	 */
	public static void showDeveloperConsoleStage(Stage stage)
	{
		//show
		DeveloperConsoleStage developerConsoleStage = new DeveloperConsoleStage(stage);
		developerConsoleStage.show();
	}
	
	/**
	 * Creates a new developer console stage.
	 * @param stage the owner of the stage
	 */
	public DeveloperConsoleStage(Stage stage)
	{
		this.console = new Console();
		
		this.history = new ArrayList<String>();
		this.historyIndex = -1;
		
		//setup stage
		this.initOwner(stage);
		this.setTitle("Developer Console");
		this.setMinWidth(200);
		this.setMinHeight(200);
		this.getIcons().add(new Image("/images/icons/console.png"));
		
		//root vbox
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setSpacing(10);
		
		//log text area
		this.logTextArea = new TextArea();
		this.logTextArea.setPrefRowCount(15);
		this.logTextArea.setPrefColumnCount(50);
		this.logTextArea.setEditable(false);
		this.logTextArea.setFont(Fonts.MONOSPACED_FONT);
		vbox.getChildren().add(this.logTextArea);
		VBox.setVgrow(this.logTextArea, Priority.ALWAYS);
		
		//input text field
		this.inputTextField = new TextField();
		this.inputTextField.setPrefColumnCount(50);
		this.inputTextField.setFont(Fonts.MONOSPACED_FONT);
		this.inputTextField.setOnAction(event -> this.executeCommand());
		this.inputTextField.setOnKeyPressed(event ->
		{
			//get code
			KeyCode keyCode = event.getCode();
			
			if (keyCode == KeyCode.UP)
			{
				this.navigateHistory(1);
			}
			else if (keyCode == KeyCode.DOWN)
			{
				this.navigateHistory(-1);
			}
			
			//consume
			if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN)
				event.consume();
		});
		vbox.getChildren().add(this.inputTextField);
		
		//initialise
		this.initialise();
		
		//show
		this.setScene(new Scene(vbox));
		this.sizeToScene();
	}
	
	/**
	 * Initialise the console.
	 */
	public void initialise()
	{
		//header text
		this.logTextArea.clear();
		this.logTextArea.appendText("Developer console initialised\n");
		this.logTextArea.appendText("Type 'help' for available commands\n\n");
	}
	
	/**
	 * Executes the current command.
	 */
	private void executeCommand()
	{
		//get text
		String name = this.inputTextField.getText().trim();
		this.logTextArea.appendText("> " + name + "\n");
		this.inputTextField.clear();
		
		//add to history
		this.history.add(0, name);
		this.historyIndex = -1;
		
		//execute
		this.logTextArea.appendText(this.console.execute(name) + "\n");
	}
	
	/**
	 * Navigates through the command history.
	 * @param increment the increment
	 */
	private void navigateHistory(int increment)
	{
		//change
		int newIndex = (this.historyIndex + increment);
		if (newIndex <= -1 || newIndex >= this.history.size())
			return;
		
		//set
		this.historyIndex = newIndex;
		this.inputTextField.setText(this.history.get(this.historyIndex));
		this.inputTextField.end();
	}
	
	/**
	 * Returns the actual console for this visual console.
	 * @return the actual console
	 */
	public Console getConsole()
	{
		return this.console;
	}
	
	private Console console;
	
	private ArrayList<String> history;
	private int historyIndex;
	
	private TextArea logTextArea;
	private TextField inputTextField;
}

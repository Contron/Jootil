package com.connorhaigh.jootil.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.utilities.Resources;

public class InputStage extends Stage
{
	/**
	 * Creates a new input stage, and then wait for it.
	 * @param owner the owner of the stage
	 * @param title the prompt's title
	 * @param message the message on the prompt
	 * @return the message entered, or empty
	 */
	public static String showInputStage(Window owner, String title, String message)
	{
		//show
		InputStage inputStage = new InputStage(owner, title, message);
		inputStage.showAndWait();
		
		//get text
		String text = inputStage.textField.getText();
		
		return (text.isEmpty() ? null : text);
	}
	
	/**
	 * Creates a new prompt dialog stage.
	 * @param owner the owner of the stage
	 * @param title the prompt's title
	 * @param message the message on the prompt
	 */
	public InputStage(Window owner, String title, String message)
	{
		//setup stage
		this.initOwner(owner);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle(title);
		this.setResizable(false);
		this.setMinWidth(350);
		this.getIcons().add(new Image("/images/icons/input.png"));
		
		//root pane
		BorderPane borderPane = new BorderPane();
		
		//content pane
		VBox contentPane = new VBox();
		contentPane.setPadding(new Insets(10, 10, 10, 10));
		contentPane.setSpacing(10);
		borderPane.setCenter(contentPane);
		
		//header label
		Label headerLabel = new Label(title);
		headerLabel.setFont(Resources.LARGE_FONT);
		contentPane.getChildren().add(headerLabel);
		
		//description label
		Label descriptionLabel = new Label(message);
		contentPane.getChildren().add(descriptionLabel);
		
		//input text field
		this.textField = new TextField();
		this.textField.setPrefColumnCount(25);
		contentPane.getChildren().add(this.textField);
		
		//buttons box
		ButtonsBox buttonsBox = new ButtonsBox(true, true);
		buttonsBox.setOnOk(event -> this.ok());
		buttonsBox.setOnCancel(event -> this.cancel());
		borderPane.setBottom(buttonsBox);
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Confirms and closes this stage.
	 */
	private void ok()
	{
		//close
		this.close();
	}
	
	/**
	 * Cancels and closes this stage.
	 */
	private void cancel()
	{
		//close
		this.textField.clear();
		this.close();
	}
	
	private TextField textField;
}

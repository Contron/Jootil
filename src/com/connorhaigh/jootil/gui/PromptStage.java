package com.connorhaigh.jootil.gui;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.utilities.Resources;

public class PromptStage extends Stage
{
	/**
	 * Create a new prompt stage, and then wait for it.
	 * @param owner the owner of the stage
	 * @param title the title of the prompt
	 * @param message the message of the prompt
	 * @param exception the exception to show, or null
	 */
	public static void showPromptStage(Window owner, String title, String message, Exception exception)
	{
		//show
		PromptStage promptStage = new PromptStage(owner, title, message, exception);
		promptStage.showAndWait();
	}
	
	/**
	 * Create a new prompt stage, and then wait for it.
	 * @param owner the owner of the stage
	 * @param title the title of the prompt
	 * @param message the message of the prompt
	 */
	public static void showPromptStage(Window owner, String title, String message)
	{
		//show
		PromptStage.showPromptStage(owner, title, message, null);
	}
	
	/**
	 * Create a new prompt dialog stage.
	 * @param owner the owner of the stage
	 * @param title the title of the prompt
	 * @param message the message of the prompt
	 * @param exception the exception to show, or null
	 */
	public PromptStage(Window owner, String title, String message, Exception exception)
	{
		//setup stage
		this.initOwner(owner);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle(title);
		this.setResizable(false);
		this.setMinWidth(350);
		this.getIcons().add(new Image("/images/icons/information.png"));
		
		//root pane
		BorderPane borderPane = new BorderPane();
		
		//content pane
		GridPane contentPane = new GridPane();
		contentPane.setPadding(new Insets(10, 10, 10, 10));
		contentPane.setHgap(10);
		contentPane.setVgap(10);
		borderPane.setCenter(contentPane);
		
		//header label
		Label headerLabel = new Label(title);
		headerLabel.setFont(Resources.LARGE_FONT);
		contentPane.add(headerLabel, 0, 0);
		
		//description label
		Label descriptionLabel = new Label(message);
		contentPane.add(descriptionLabel, 0, 1);
		
		if (exception != null)
		{
			//collect stack trace
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			exception.printStackTrace(printWriter);
			
			//separator
			Separator separator = new Separator();
			contentPane.add(separator, 0, 2);
			
			//error grid pane
			GridPane errorGridPane = new GridPane();
			errorGridPane.setHgap(10);
			errorGridPane.setVgap(10);
			contentPane.add(errorGridPane, 0, 3);
			
			//error label
			Label errorLabel = new Label("Error:");
			errorGridPane.add(errorLabel, 0, 0);
			
			//error text field
			TextField errorTextField = new TextField(exception.getMessage());
			errorTextField.setPrefColumnCount(25);
			errorTextField.setEditable(false);
			errorGridPane.add(errorTextField, 1, 0);
			
			//type label
			Label typeLabel = new Label("Type:");
			errorGridPane.add(typeLabel, 0, 1);
			
			//type text field
			TextField typeTextField = new TextField(exception.getClass().getSimpleName());
			typeTextField.setPrefColumnCount(25);
			typeTextField.setEditable(false);
			errorGridPane.add(typeTextField, 1, 1);
			
			//stack trace label
			Label stackTraceLabel = new Label("Stack trace:");
			errorGridPane.add(stackTraceLabel, 0, 2);
			GridPane.setValignment(stackTraceLabel, VPos.TOP);
			
			//stack trace text area
			TextArea stackTraceTextArea = new TextArea(stringWriter.toString());
			stackTraceTextArea.setPrefRowCount(5);
			stackTraceTextArea.setPrefColumnCount(50);
			stackTraceTextArea.setEditable(false);
			errorGridPane.add(stackTraceTextArea, 1, 2);
		}
		
		//buttons box
		ButtonsBox buttonsBox = new ButtonsBox(true, false);
		buttonsBox.setOnOk(event -> this.ok());
		borderPane.setBottom(buttonsBox);
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Confirm and close this stage.
	 */
	private void ok()
	{
		//close
		this.close();
	}
}

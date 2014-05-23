package com.connorhaigh.jootil.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.connorhaigh.jootil.gui.components.ButtonsBox;

public class PromptStage extends Stage
{
	/**
	 * Create a new prompt stage, and then wait for it.
	 * @param owner the owner of the stage
	 * @param title the title of the prompt
	 * @param message the message of the prompt
	 */
	public static void showPromptStage(Window owner, String title, String message)
	{
		//show
		PromptStage promptStage = new PromptStage(owner, title, message);
		promptStage.showAndWait();
	}
	
	/**
	 * Create a new prompt dialog stage.
	 * @param owner the owner of the stage
	 * @param title the title of the prompt
	 * @param message the message of the prompt
	 */
	public PromptStage(Window owner, String title, String message)
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
		headerLabel.setFont(Font.font(18));
		contentPane.add(headerLabel, 0, 0);
		
		//description label
		Label descriptionLabel = new Label(message);
		contentPane.add(descriptionLabel, 0, 1);
		
		//buttons box
		ButtonsBox buttonsBox = new ButtonsBox(true, false);
		buttonsBox.setOnOk(event -> this.ok());
		borderPane.setBottom(buttonsBox);
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Confirm and close the stage.
	 */
	private void ok()
	{
		//close
		this.close();
	}
}

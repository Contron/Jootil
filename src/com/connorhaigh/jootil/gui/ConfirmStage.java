package com.connorhaigh.jootil.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.utilities.Fonts;

public class ConfirmStage extends Stage
{
	/**
	 * Creates a new confirm stage, and then wait for it.
	 * @param stage the owner of the stage
	 * @param title the prompt's title
	 * @param message the message on the prompt
	 * @returns if the dialog was confirmed
	 */
	public static boolean showConfirmStage(Stage stage, String title, String message)
	{
		//show
		ConfirmStage confirmStage = new ConfirmStage(stage, title, message);
		confirmStage.showAndWait();
		
		return confirmStage.confirmed;
	}
	
	/**
	 * Creates a new confirm dialog stage.
	 * @param stage the owner of the stage
	 * @param title the prompt's title
	 * @param message the message on the prompt
	 */
	public ConfirmStage(Stage stage, String title, String message)
	{
		this.confirmed = false;
		
		//setup stage
		this.initOwner(stage);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle(title);
		this.setResizable(false);
		this.setMinWidth(350);
		this.getIcons().add(new Image("/images/icons/question.png"));
		
		//root pane
		BorderPane borderPane = new BorderPane();
		
		//content pane
		VBox contentPane = new VBox();
		contentPane.setPadding(new Insets(10, 10, 10, 10));
		contentPane.setSpacing(10);
		borderPane.setCenter(contentPane);
		
		//header label
		Label headerLabel = new Label(title);
		headerLabel.setFont(Fonts.LARGE_FONT);
		contentPane.getChildren().add(headerLabel);
		
		//description label
		Label descriptionLabel = new Label(message);
		contentPane.getChildren().add(descriptionLabel);
		
		//buttons box
		ButtonsBox buttonsBox = new ButtonsBox(true, true);
		buttonsBox.setOnOk(event -> this.exit(true));
		buttonsBox.setOnCancel(event -> this.exit(false));
		borderPane.setBottom(buttonsBox);
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Closes this stage with the specified result.
	 * @param result the result
	 */
	private void exit(boolean result)
	{
		//close
		this.confirmed = result;
		this.close();
	}
	
	private boolean confirmed;
}

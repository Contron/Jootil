package com.connorhaigh.jootil.gui.bases;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.gui.components.HeaderBox;
import com.connorhaigh.jootil.utilities.Styles;

public abstract class BaseDialogStage extends Stage
{
	/**
	 * Creates a new base dialog stage.
	 * @param stage the owner
	 * @param title the title
	 * @param header the header text
	 * @param description the description text
	 * @param ok if an OK button should be present
	 * @param cancel if a Cancel button should be present
	 */
	public BaseDialogStage(Stage stage, String title, String header, String description, boolean ok, boolean cancel)
	{
		this.confirmed = false;
		
		//setup stage
		this.initOwner(stage);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle(title);
		this.setResizable(false);
		this.setMinWidth(350);
		this.getIcons().add(new Image("/images/icons/information.png"));

		//root pane
		BorderPane borderPane = new BorderPane();

		//header box
		HeaderBox headerBox = new HeaderBox(header, description);
		headerBox.setStyle(Styles.none());
		borderPane.setTop(headerBox);
		
		//buttons box
		ButtonsBox buttonsBox = new ButtonsBox(ok, cancel);
		borderPane.setBottom(buttonsBox);
		
		if (ok)
		{
			//ok
			buttonsBox.setOnOk(event -> this.confirm(true));
		}
		
		if (cancel)
		{
			//cancel
			buttonsBox.setOnCancel(event -> this.confirm(false));
		}
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Closes and confirms this dialog stage.
	 * @param confirmed if it is confirmed
	 */
	private void confirm(boolean confirmed)
	{
		//set and close
		this.confirmed = confirmed;
		this.close();
	}
	
	/**
	 * Returns if this base dialog stage has been confirmed.
	 * @return if this base dialog stage has been confirmed
	 */
	public boolean isConfirmed()
	{
		return this.confirmed;
	}
	
	private boolean confirmed;
}

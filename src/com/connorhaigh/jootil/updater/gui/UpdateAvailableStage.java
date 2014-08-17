package com.connorhaigh.jootil.updater.gui;

import java.awt.Desktop;
import java.net.URL;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.connorhaigh.jootil.gui.PromptStage;
import com.connorhaigh.jootil.gui.components.ButtonsBox;

public class UpdateAvailableStage extends Stage
{
	/**
	 * Create a new update available stage.
	 * @param downloadPage the remote page to download the update
	 */
	public UpdateAvailableStage(String downloadPage)
	{
		this.downloadPage = downloadPage;
		
		//setup stage
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle("Update Available");
		this.setResizable(false);
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
		Label headerLabel = new Label("Update Available");
		headerLabel.setFont(Font.font(18));
		contentPane.add(headerLabel, 0, 0);
		
		//description label
		Label descriptionLabel = new Label("An update has been detected as being available for the application.\nWould you like to download it?");
		contentPane.add(descriptionLabel, 0, 1);
		
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
	 * Confirm and close this stage.
	 */
	private void ok()
	{
		try
		{
			//open browser
			Desktop desktop = Desktop.getDesktop();
			desktop.browse(new URL(this.downloadPage).toURI());
		}
		catch (Exception exception)
		{
			//error
			PromptStage.showPromptStage(this, "Error", "Your web browser could not be opened to display the update page.");
		}
		
		//close
		this.close();
	}
	
	/**
	 * Cancel and close this stage.
	 */
	private void cancel()
	{
		//close
		this.close();
	}
	
	private String downloadPage;
}

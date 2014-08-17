package com.connorhaigh.jootil.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.utilities.Resources;

public class AboutStage extends Stage
{
	/**
	 * Create a new about stage, and then wait for it.
	 * @param owner the owner of the stage
	 * @param image the large image for the display
	 * @param name the name of the application
	 * @param developer the developer (or developers) of the application
	 * @param year the year of the release
	 * @param website the web site for the application
	 */
	public static void showAboutStage(Window owner, Image image, String name, String developer, String year, String website)
	{
		//show
		AboutStage aboutStage = new AboutStage(owner, image, name, developer, year, website);
		aboutStage.showAndWait();
	}
	
	/**
	 * Create a new about stage.
	 * @param owner the owner of the stage
	 * @param image the large image for the display
	 * @param name the name of the application
	 * @param developer the developer (or developers) of the application
	 * @param year the year of the release
	 * @param website the web site for the application
	 */
	public AboutStage(Window owner, Image image, String name, String developer, String year, String website)
	{
		//setup stage
		this.initOwner(owner);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle("About");
		this.setResizable(false);
		this.getIcons().add(image);
		
		//root pane
		BorderPane borderPane = new BorderPane();
		
		//content pane
		GridPane contentPane = new GridPane();
		contentPane.setPadding(new Insets(10, 10, 10, 10));
		contentPane.setHgap(10);
		contentPane.setVgap(10);
		borderPane.setCenter(contentPane);
		
		//image
		ImageView imageView = new ImageView(image);
		contentPane.add(imageView, 0, 0, 1, 2);
		
		//header label
		Label headerLabel = new Label(name);
		headerLabel.setFont(Resources.LARGE_FONT);
		contentPane.add(headerLabel, 1, 0);
		
		//description label
		Label descriptionLabel = new Label(String.format("(C) %s %s%n%s", developer, year, website));
		contentPane.add(descriptionLabel, 1, 1);
		
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

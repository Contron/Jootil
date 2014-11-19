package com.connorhaigh.jootil.gui;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.connorhaigh.jootil.gui.base.BaseDialogStage;

public class AboutStage extends BaseDialogStage
{
	/**
	 * Creates a new about stage, and waits for it.
	 * @param stage the owner
	 * @param image the image
	 * @param name the name of the application
	 * @param developer the developer of the application
	 * @param year the year of release
	 * @param website the developer's Web site
	 */
	public static void showAboutStage(Stage stage, Image image, String name, String developer, String year, String website)
	{
		//show
		AboutStage aboutStage = new AboutStage(stage, image, name, developer, year, website);
		aboutStage.showAndWait();
	}
	
	/**
	 * Creates a new about stage.
	 * @param stage the owner
	 * @param image the image
	 * @param name the name of the application
	 * @param developer the developer of the application
	 * @param year the year of release
	 * @param website the developer's Web site
	 */
	public AboutStage(Stage stage, Image image, String name, String developer, String year, String website)
	{
		super(stage, "About", name, String.format("(C) %s %s%n%s", developer, year, website), true, false);
	}
}

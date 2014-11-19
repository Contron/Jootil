package com.connorhaigh.jootil.gui;

import javafx.stage.Stage;

import com.connorhaigh.jootil.gui.base.BaseDialogStage;

public class PromptStage extends BaseDialogStage
{
	/**
	 * Creates a new prompt stage, and waits for it.
	 * @param stage the owner
	 * @param title the title
	 * @param message the message
	 */
	public static void showPromptStage(Stage stage, String title, String message)
	{
		//show
		PromptStage promptStage = new PromptStage(stage, title, message);
		promptStage.showAndWait();
	}
	
	/**
	 * Creates a new prompt stage.
	 * @param stage the owner
	 * @param title the title
	 * @param message the message
	 */
	public PromptStage(Stage stage, String title, String message)
	{
		super(stage, "Confirmation", title, message, true, false);
	}
}

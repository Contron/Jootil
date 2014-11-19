package com.connorhaigh.jootil.gui;

import javafx.stage.Stage;

import com.connorhaigh.jootil.gui.base.BaseDialogStage;

public class ConfirmStage extends BaseDialogStage
{
	/**
	 * Creates a new confirm stage, and waits for it.
	 * @param stage the owner
	 * @param title the title
	 * @param message the message
	 * @return the result
	 */
	public static boolean showConfirmStage(Stage stage, String title, String message)
	{
		//show
		ConfirmStage confirmStage = new ConfirmStage(stage, title, message);
		confirmStage.showAndWait();
		
		return confirmStage.isConfirmed();
	}
	
	/**
	 * Creates a new confirm stage.
	 * @param stage the owner
	 * @param title the title
	 * @param message the message
	 */
	public ConfirmStage(Stage stage, String title, String message)
	{
		super(stage, "Message", title, message, true, true);
	}
}

package com.connorhaigh.jootil.gui.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import com.connorhaigh.jootil.gui.styles.Styles;

public class ButtonsBox extends HBox
{
	/**
	 * Create a new generic buttons box.
	 * @param okButton if an 'OK' button should be added
	 * @param cancelButton if a 'Cancel' button should be added
	 */
	public ButtonsBox(boolean okButton, boolean cancelButton)
	{
		//setup box
		this.setAlignment(Pos.BASELINE_RIGHT);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setSpacing(10);
		this.setStyle(Styles.build(Styles.CONTROL_BACKGROUND, Styles.CONTROL_BORDER_COLOUR, Styles.CONTROL_TOP_BORDER));
		
		if (okButton)
		{
			//ok button
			this.okButton = new Button("OK");
			this.okButton.setDefaultButton(true);
			this.getChildren().add(this.okButton);
		}
		
		if (cancelButton)
		{
			//cancel button
			this.cancelButton = new Button("Cancel");
			this.cancelButton.setCancelButton(true);
			this.getChildren().add(this.cancelButton);
		}
	}
	
	/**
	 * Sets the action to perform when the 'OK' button is clicked.
	 * @param eventHandler the event handler
	 */
	public void setOnOk(EventHandler<ActionEvent> eventHandler)
	{
		this.okButton.setOnAction(eventHandler);
	}
	
	/**
	 * Returns this button box's 'OK' button.
	 * @return this button box's 'OK' button
	 */
	public Button getOkButton()
	{
		return this.okButton;
	}
	
	/**
	 * Sets the action to perform when the 'Cancel' button is clicked.
	 * @param eventHandler the event handler
	 */
	public void setOnCancel(EventHandler<ActionEvent> eventHandler)
	{
		this.cancelButton.setOnAction(eventHandler);
	}
	
	/**
	 * Returns this button box's 'Cancel' button.
	 * @return this button box's 'Cancel' button
	 */
	public Button getCancelButton()
	{
		return this.cancelButton;
	}
	
	private Button okButton;
	private Button cancelButton;
}

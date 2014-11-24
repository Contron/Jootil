package com.connorhaigh.jootil.gui.components;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import com.connorhaigh.jootil.utilities.Styles;

public class ButtonsBox extends HBox
{
	/**
	 * Creates a new buttons box.
	 * @param okButton if an OK button should be added
	 * @param cancelButton if a Cancel button should be added
	 */
	public ButtonsBox(boolean okButton, boolean cancelButton)
	{
		this.okDisabledProperty = new SimpleBooleanProperty(false);
		this.cancelDisabledProperty = new SimpleBooleanProperty(false);
		
		//setup box
		this.setAlignment(Pos.BASELINE_RIGHT);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setSpacing(10);
		this.setStyle(Styles.build(Styles.BACKGROUND_COLOUR, Styles.BORDER_COLOUR, Styles.TOP_BORDER));
		
		if (okButton)
		{
			//ok button
			this.okButton = new Button("OK");
			this.okButton.setDefaultButton(true);
			this.okButton.disableProperty().bind(this.okDisabledProperty);
			this.getChildren().add(this.okButton);
		}
		
		if (cancelButton)
		{
			//cancel button
			this.cancelButton = new Button("Cancel");
			this.cancelButton.setCancelButton(true);
			this.cancelButton.disableProperty().bind(this.cancelDisabledProperty);
			this.getChildren().add(this.cancelButton);
		}
	}
	
	/**
	 * Creates a new, default buttons box.
	 */
	public ButtonsBox()
	{
		this(true, true);
	}
	
	/**
	 * Sets if the OK button is disabled.
	 * @param disabled if the OK button is disabled
	 */
	public void setOkDisabled(boolean disabled)
	{
		this.okDisabledProperty.set(disabled);
	}
	
	/**
	 * Returns if the OK button is disabled.
	 * @return if the OK button is disabled
	 */
	public boolean getOkDisabled()
	{
		return this.okDisabledProperty.get();
	}
	
	/**
	 * Sets if the Cancel button is disabled.
	 * @param disabled if the Cancel button is disabled
	 */
	public void setCancelDisabled(boolean disabled)
	{
		this.cancelDisabledProperty.set(disabled);
	}
	
	
	/**
	 * Returns if the Cancel button is disabled.
	 * @return if the Cancel button is disabled
	 */
	public boolean getCancelDisabled()
	{
		return this.cancelDisabledProperty.get();
	}
	
	/**
	 * Sets the event handler when the OK button is clicked.
	 * @param eventHandler the event handler
	 */
	public void setOnOk(EventHandler<ActionEvent> eventHandler)
	{
		this.okButton.setOnAction(eventHandler);
	}
	
	/**
	 * Returns the OK button for this button box.
	 * @return the OK button
	 */
	public Button getOkButton()
	{
		return this.okButton;
	}
	
	/**
	 * Sets the event handler when the Cancel button is clicked.
 	 * @param eventHandler the event handler
	 */
	public void setOnCancel(EventHandler<ActionEvent> eventHandler)
	{
		this.cancelButton.setOnAction(eventHandler);
	}
	
	/**
	 * Returns the Cancel button for this button box.
	 * @return the Cancel button
	 */
	public Button getCancelButton()
	{
		return this.cancelButton;
	}
	
	/**
	 * Returns the OK disabled property.
	 * @return the OK disabled property
	 */
	public SimpleBooleanProperty okDisabledProperty()
	{
		return this.okDisabledProperty;
	}
	
	/**
	 * Returns the Cancel disabled property.
	 * @return the Cancel disabled property
	 */
	public SimpleBooleanProperty cancelDisabledProperty()
	{
		return this.cancelDisabledProperty;
	}
	
	private SimpleBooleanProperty okDisabledProperty;
	private SimpleBooleanProperty cancelDisabledProperty;
	
	private Button okButton;
	private Button cancelButton;
}

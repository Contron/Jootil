package com.connorhaigh.jootil.gui.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import com.connorhaigh.jootil.utilities.Styles;

public class StatusBar extends HBox
{
	/**
	 * Creates a new status bar.
	 */
	public StatusBar()
	{
		this.statusProperty = new SimpleStringProperty("Ready");
		
		//setup box
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setSpacing(10);
		this.setStyle(Styles.build(Styles.BACKGROUND_COLOUR, Styles.BORDER_COLOUR, Styles.TOP_BORDER));
		
		//status label
		Label statusLabel = new Label();
		statusLabel.setMaxWidth(Double.MAX_VALUE);
		statusLabel.textProperty().bind(this.statusProperty);
		this.getChildren().add(statusLabel);
	}
	
	/**
	 * Sets the status of this status bar.
	 * @param status the status
	 */
	public void setStatus(String status)
	{
		this.statusProperty.set(status);
	}
	
	/**
	 * Returns the status of this status bar.
	 * @return the status
	 */
	public String getStatus()
	{
		return this.statusProperty.get();
	}
	
	/**
	 * Returns the status property.
	 * @return the status property
	 */
	public SimpleStringProperty statusProperty()
	{
		return this.statusProperty;
	}
	
	private SimpleStringProperty statusProperty;
}

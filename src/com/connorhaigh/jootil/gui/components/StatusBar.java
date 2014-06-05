package com.connorhaigh.jootil.gui.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import com.connorhaigh.jootil.utilities.Styles;

public class StatusBar extends HBox
{
	/**
	 * Create a new status bar.
	 */
	public StatusBar()
	{
		this.statusProperty = new SimpleStringProperty("Ready.");
		
		//setup box
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setSpacing(10);
		this.setStyle(Styles.build(Styles.CONTROL_BACKGROUND, Styles.CONTROL_BORDER_COLOUR, Styles.CONTROL_TOP_BORDER));
		
		//status label
		Label statusLabel = new Label();
		statusLabel.setMaxWidth(Double.MAX_VALUE);
		statusLabel.textProperty().bind(this.statusProperty);
		this.getChildren().add(statusLabel);
		HBox.setHgrow(statusLabel, Priority.ALWAYS);
	}
	
	/**
	 * Sets this status bar's text.
	 * @param text the text
	 */
	public void setStatus(String text)
	{
		this.statusProperty.set(text);
	}
	
	/**
	 * Returns this status bar's text.
	 * @return this status bar's text
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

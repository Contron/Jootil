package com.connorhaigh.jootil.gui.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import com.connorhaigh.jootil.utilities.Fonts;
import com.connorhaigh.jootil.utilities.Styles;

public class HeaderBox extends VBox
{
	/**
	 * Creates a new generic header and description box.
	 * @param header the header
	 * @param description the description
	 * @param style if it should be styled
	 */
	public HeaderBox(String header, String description)
	{
		this.headerProperty = new SimpleStringProperty(header);
		this.descriptionProperty = new SimpleStringProperty(description);
		
		//setup box
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setSpacing(10);
		this.setStyle(Styles.build(Styles.CONTROL_BACKGROUND_COLOUR, Styles.CONTROL_BORDER_COLOUR, Styles.CONTROL_BOTTOM_BORDER));
		
		//header label
		Label headerLabel = new Label();
		headerLabel.setFont(Fonts.LARGE);
		headerLabel.textProperty().bind(this.headerProperty);
		this.getChildren().add(headerLabel);
		
		//description label
		Label descriptionLabel = new Label();
		descriptionLabel.textProperty().bind(this.descriptionProperty);
		this.getChildren().add(descriptionLabel);
	}
	
	/**
	 * Creates a new blank header box.
	 */
	public HeaderBox()
	{
		this(null, null);
	}
	
	/**
	 * Sets the header of this box.
	 * @param header the header
	 */
	public void setHeader(String header)
	{
		this.headerProperty.set(header);
	}
	
	/**
	 * Sets the description of this box.
	 * @param description the description
	 */
	public void setDescription(String description)
	{
		this.descriptionProperty.set(description);
	}
	
	/**
	 * Returns the header of this box.
	 * @return the header
	 */
	public String getHeader()
	{
		return this.headerProperty.get();
	}
	
	/**
	 * Returns the description of this box.
	 * @return the description
	 */
	public String getDescription()
	{
		return this.descriptionProperty.get();
	}
	
	/**
	 * Returns the header property.
	 * @return the header property
	 */
	public SimpleStringProperty headerProperty()
	{
		return this.headerProperty;
	}
	
	/**
	 * Returns the description property.
	 * @return the description property
	 */
	public SimpleStringProperty descriptionProperty()
	{
		return this.descriptionProperty;
	}
	
	private SimpleStringProperty headerProperty;
	private SimpleStringProperty descriptionProperty;
}

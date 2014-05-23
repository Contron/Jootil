package com.connorhaigh.jootil.gui.components;

import com.connorhaigh.jootil.gui.styles.Styles;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HeaderBox extends VBox
{
	/**
	 * Create a new generic header and description box.
	 * @param header the header
	 * @param description the description
	 */
	public HeaderBox(String header, String description)
	{
		this.headerProperty = new ReadOnlyStringWrapper(header);
		this.descriptionProperty = new ReadOnlyStringWrapper(description);
		
		//setup box
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setSpacing(10);
		this.setStyle(Styles.build(Styles.CONTROL_BACKGROUND, Styles.CONTROL_BORDER_COLOUR, Styles.CONTROL_BOTTOM_BORDER));
		
		//header label
		Label headerLabel = new Label(header);
		headerLabel.setFont(Font.font(18));
		headerLabel.textProperty().bind(this.headerProperty);
		this.getChildren().add(headerLabel);
		
		//description label
		Label descriptionLabel = new Label(description);
		descriptionLabel.textProperty().bind(this.descriptionProperty);
		this.getChildren().add(descriptionLabel);
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
	public ReadOnlyStringProperty headerProperty()
	{
		return this.headerProperty.getReadOnlyProperty();
	}
	
	/**
	 * Returns the description property.
	 * @return the description property
	 */
	public ReadOnlyStringProperty descriptionProperty()
	{
		return this.descriptionProperty.getReadOnlyProperty();
	}
	
	private ReadOnlyStringWrapper headerProperty;
	private ReadOnlyStringWrapper descriptionProperty;
}

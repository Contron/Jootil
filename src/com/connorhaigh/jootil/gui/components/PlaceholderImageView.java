package com.connorhaigh.jootil.gui.components;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PlaceholderImageView extends BorderPane
{
	/**
	 * Creates a new placeholder image view, which will display a progress indicator until its image is fully loaded.
	 * @param image the image to show
	 */
	public PlaceholderImageView(Image image)
	{
		this.image = image;
		this.image.progressProperty().addListener((value, oldValue, newValue) ->
		{
			//swap
			if (newValue.doubleValue() == 1)
				this.setCenter(this.imageView);
		});
		this.imageView = new ImageView(this.image);
		
		this.progressIndicator = new ProgressIndicator();
		this.progressIndicator.progressProperty().bind(this.image.progressProperty());
		BorderPane.setAlignment(this.progressIndicator, Pos.CENTER);
		
		//setup pane
		this.setCenter(this.progressIndicator);
	}
	
	/**
	 * Sets the image for this placeholder.
	 * @param image the image
	 */
	public void setImage(Image image)
	{
		this.imageView.setImage(image);
	}
	
	/**
	 * Sets the image view for this placeholder.
	 * @param imageView the image view
	 */
	public void setImageView(ImageView imageView)
	{
		this.imageView = imageView;
	}
	
	/**
	 * Returns the image view for this placeholder.
	 * @return the image view
	 */
	public ImageView getImageView()
	{
		return this.imageView;
	}
	
	private Image image;
	private ImageView imageView;
	
	private ProgressIndicator progressIndicator;
}

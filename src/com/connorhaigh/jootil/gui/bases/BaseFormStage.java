package com.connorhaigh.jootil.gui.bases;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.connorhaigh.jootil.core.FormBuilder;
import com.connorhaigh.jootil.core.interfaces.Form;
import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.gui.components.HeaderBox;

public abstract class BaseFormStage extends Stage implements Form
{
	/**
	 * Creates a new base form stage.
	 * @param stage the stage
	 * @param title the title
	 * @param description the description
	 */
	public BaseFormStage(Stage stage, String title, String description)
	{
		this.okDisabledProperty = new SimpleBooleanProperty(false);
		this.cancelDisabledProperty = new SimpleBooleanProperty(false);
		
		//setup stage
		this.initOwner(stage);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle(title);
		this.setResizable(false);
		this.setMinWidth(350);
		this.getIcons().add(new Image("/images/icons/input.png"));
		
		//border pane
		BorderPane borderPane = new BorderPane();
		
		//header box
		HeaderBox headerBox = new HeaderBox(title, description);
		borderPane.setTop(headerBox);
		
		//grid pane
		this.gridPane = new GridPane();
		this.gridPane.setPadding(new Insets(10, 10, 10, 10));
		this.gridPane.setHgap(10);
		this.gridPane.setVgap(10);
		borderPane.setCenter(this.gridPane);
		
		//buttons box
		ButtonsBox buttonsBox = new ButtonsBox(true, true);
		buttonsBox.setOnOk(event -> this.confirm(true));
		buttonsBox.setOnCancel(event -> this.confirm(false));
		buttonsBox.okDisabledProperty().bind(this.okDisabledProperty);
		buttonsBox.cancelDisabledProperty().bind(this.cancelDisabledProperty);
		borderPane.setBottom(buttonsBox);
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Assembles the controls on this form.
	 * @param formBuilder the form builder
	 */
	public abstract void assembleForm(FormBuilder formBuilder);
	
	/**
	 * Initialises this form.
	 */
	public void initialiseForm()
	{
		//initialise
		this.assembleForm(new FormBuilder(this.gridPane));
		this.populateFields();
	}
	
	/**
	 * Confirms and closes this stage.
	 * @param confirmed if it was confirmed or cancelled
	 */
	private void confirm(boolean confirmed)
	{
		if (confirmed)
		{
			//confirmed
			this.contentsConfirmed();
		}
		
		//close
		this.close();
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
	
	private GridPane gridPane;
}

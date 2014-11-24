package com.connorhaigh.jootil.gui.bases;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
		this.row = 0;
		
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
	 * Adds a row to this form.
	 * @param message the message, or null
	 * @param control the control, or null
	 */
	public void addRow(String message, Control control)
	{
		if (message != null)
		{
			//span
			int columnSpan = (control != null ? 1 : 2);
			
			//label
			Label label = new Label(message);
			this.gridPane.add(label, 0, this.row, columnSpan, 1);
		}
		
		if (control != null)
		{
			//span
			int column = (control instanceof Separator ? 0 : 1);
			int columnSpan = (column == 0 ? 2 : 1);
			
			//control
			control.setMaxWidth(Double.MAX_VALUE);
			control.setMaxHeight(150);
			
			if (control instanceof ComboBox)
			{
				//placeholder
				((ComboBox<?>) control).setPlaceholder(new Label("No available options"));
			}
			
			if (control instanceof ListView)
			{
				//placeholder
				((ListView<?>) control).setPlaceholder(new Label("No selectable options"));
			}
			
			//add
			this.gridPane.add(control, column, this.row, columnSpan, 1);
			GridPane.setHgrow(control, Priority.ALWAYS);
		}
		
		//increment
		this.row++;
	}
	
	/**
	 * Adds a row for a separator.
	 */
	public void addSeparatorRow()
	{
		this.addRow(null, new Separator());
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
	
	private int row;
	
	private SimpleBooleanProperty okDisabledProperty;
	private SimpleBooleanProperty cancelDisabledProperty;
	
	private GridPane gridPane;
}

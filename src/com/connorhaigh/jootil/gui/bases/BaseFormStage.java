package com.connorhaigh.jootil.gui.bases;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.connorhaigh.jootil.beans.Form;
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
		
		//setup stage
		this.initOwner(stage);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle(title);
		this.setResizable(false);
		this.setMinWidth(350);
		this.getIcons().add(new Image("/images/icons/information.png"));
		
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
		borderPane.setBottom(buttonsBox);
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Adds a row for a label and a control.
	 * @param message the message
	 * @param control the control
	 */
	public void addRowForControl(String message, Control control)
	{	
		this.addRow(message, control);
	}
	
	/**
	 * Adds a row for a separator.
	 */
	public void addRowForSeparator()
	{
		this.addRow(null, new Separator());
	}
	
	/**
	 * Adds a row to this form.
	 * @param message the message, or null
	 * @param control the control, or null
	 */
	private void addRow(String message, Control control)
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
			GridPane.setHgrow(control, Priority.ALWAYS);
			this.gridPane.add(control, column, this.row, columnSpan, 1);
		}
		
		//increment
		this.row++;
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
	
	private int row;
	
	private GridPane gridPane;
}

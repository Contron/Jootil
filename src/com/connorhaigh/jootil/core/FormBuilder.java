package com.connorhaigh.jootil.core;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class FormBuilder
{
	/**
	 * Creates a new form builder.
	 * @param gridPane the grid pane
	 */
	public FormBuilder(GridPane gridPane)
	{
		this.currentRow = 0;
		this.gridPane = gridPane;
	}
	
	/**
	 * Adds a row to this form.
	 * @param message the message
	 * @param control the row
	 */
	public void addControlRow(String message, Control control)
	{
		this.addRow(message, control, 0, 1);
	}
	
	/**
	 * Adds a row to this form.
	 * @param control the control
	 */
	public void addControlRow(Control control)
	{
		this.addControlRow(null, control);
	}
	
	/**
	 * Adds a full row to this form.
	 * @param control the control
	 */
	public void addFullControlRow(Control control)
	{
		this.addRow(null, control, 0, 2);
	}
	
	/**
	 * Adds a row for a separator.
	 */
	public void addSeparatorRow()
	{
		this.addFullControlRow(new Separator());
	}
	
	/**
	 * Adds a row to this form.
	 * @param message the message, or null
	 * @param control the control, or null
	 * @param column the column
	 * @param columnSpan the column span
	 */
	private void addRow(String message, Control control, int column, int columnSpan)
	{
		if (message != null)
		{
			//label
			Label label = new Label(message);
			this.gridPane.add(label, 0, this.currentRow);
		}
		
		if (control != null)
		{
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
			this.gridPane.add(control, column, this.currentRow, columnSpan, 1);
			GridPane.setHgrow(control, Priority.ALWAYS);
		}
		
		//increment
		this.currentRow++;
	}
	
	private int currentRow;
	private GridPane gridPane;
}

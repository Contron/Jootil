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
			this.gridPane.add(label, 0, this.currentRow, columnSpan, 1);
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
			this.gridPane.add(control, column, this.currentRow, columnSpan, 1);
			GridPane.setHgrow(control, Priority.ALWAYS);
		}
		
		//increment
		this.currentRow++;
	}
	
	/**
	 * Adds a row for a separator.
	 */
	public void addSeparatorRow()
	{
		this.addRow(null, new Separator());
	}
	
	/**
	 * Adds a row for a message.
	 * @param message the message
	 */
	public void addLabelRow(String message)
	{
		this.addRow(null, new Label(message));
	}
	
	private int currentRow;
	private GridPane gridPane;
}

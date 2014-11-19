package com.connorhaigh.jootil.utilities;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class Shortcuts 
{
	/**
	 * Creates the appropriate handlers to allow a text field to receive drag-and-drop events for files.
	 * The contents of the text field will then be matched to the file.
	 * @param textField the text field
	 */
	public static void createDragDroppableTextField(TextField textField)
	{
		//create
		Shortcuts.createDragDroppableControl(textField, event -> textField.setText(event.getDragboard().getFiles().get(0).getAbsolutePath()));
	}
	
	/**
	 * Creates the appropriate handlers to allow a list view to have dragged file locations added to it.
	 * The appropriate file will be added to the list view's items.
	 * @param listView
	 */
	public static void createDragDroppableListView(ListView<File> listView)
	{
		//create
		Shortcuts.createDragDroppableControl(listView, event -> listView.getItems().addAll(event.getDragboard().getFiles()));
	}
	
	/**
	 * Creates the appropriate handlers for the control to receive drag-drop events.
	 * @param control the control
	 * @param eventHandler the event handler for a successful drag
	 */
	public static void createDragDroppableControl(Control control, EventHandler<DragEvent> eventHandler)
	{
		//drag over
		control.setOnDragOver(event ->
		{
			//get dragboard
			Dragboard dragboard = event.getDragboard();
			
			//check
			if (dragboard.hasFiles())
				event.acceptTransferModes(TransferMode.ANY);
			
			//consume
			event.consume();
		});
		
		//drag dropped
		control.setOnDragDropped(event ->
		{
			//get dragboard
			Dragboard dragboard = event.getDragboard();
			
			//check
			if (!dragboard.hasFiles())
				return;
			
			//consume
			event.setDropCompleted(true);
			event.consume();
			
			//fire
			eventHandler.handle(event);
		});
	}
}

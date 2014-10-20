package com.connorhaigh.jootil.utilities;

import java.io.File;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
		//drag over
		textField.setOnDragOver(event ->
		{
			//check files
			Dragboard dragboard = event.getDragboard();
			
			if (dragboard.hasFiles())
				event.acceptTransferModes(TransferMode.LINK);
			
			//consume
			event.consume();
		});
		
		//drag dropped
		textField.setOnDragDropped(event ->
		{
			//check files
			Dragboard dragboard = event.getDragboard();
			if (!dragboard.hasFiles())
				return;
			
			//get first file
			File file = dragboard.getFiles().get(0);
			if (!file.exists())
				return;
			
			//update
			textField.setText(file.getAbsolutePath());
			
			//consume
			event.setDropCompleted(true);
			event.consume();
		});
	}
	
	/**
	 * Creates the appropriate handlers to allow a list view to have dragged file locations added to it.
	 * The appropriate file will be added to the list view's items.
	 * @param listView
	 */
	public static void createDragDroppableListView(ListView<File> listView)
	{
		//drag over
		listView.setOnDragOver(event ->
		{
			//check files
			Dragboard dragboard = event.getDragboard();
			if (dragboard.hasFiles())
				event.acceptTransferModes(TransferMode.LINK);
			
			//consume
			event.consume();
		});
		
		//drag dropped
		listView.setOnDragDropped(event ->
		{
			//check files
			Dragboard dragboard = event.getDragboard();
			if (!dragboard.hasFiles())
				return;
			
			//add files
			listView.getItems().addAll(dragboard.getFiles());
			
			//consume
			event.setDropCompleted(true);
			event.consume();
		});
	}
}

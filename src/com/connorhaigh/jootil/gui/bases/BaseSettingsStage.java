package com.connorhaigh.jootil.gui.bases;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.connorhaigh.jootil.beans.Form;
import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.gui.components.HeaderBox;

public abstract class BaseSettingsStage extends Stage implements Form
{
	/**
	 * Creates a new base settings stage.
	 * @param stage the owner
	 */
	public BaseSettingsStage(Stage stage)
	{
		//setup stage
		this.initOwner(stage);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle("Settings");
		this.setResizable(false);
		this.setMinWidth(350);
		this.getIcons().add(new Image("/images/icons/console.png"));
		
		//root pane
		BorderPane borderPane = new BorderPane();
		
		//header box
		HeaderBox headerBox = new HeaderBox("Settings", "Configure application-specific settings.");
		borderPane.setTop(headerBox);
		
		//tab pane
		this.tabPane = new TabPane();
		this.tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		borderPane.setCenter(this.tabPane);
		
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
	 * Creates a new tab that can have controls added to it.
	 * @param name the name of the tab
	 * @return the tab's grid pane
	 */
	public GridPane createTab(String name)
	{
		//tab
		Tab tab = new Tab(name);
		this.tabPane.getTabs().add(tab);
		
		//grid pane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		tab.setContent(gridPane);
		
		return gridPane;
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
	
	private TabPane tabPane;
}

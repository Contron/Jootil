package com.connorhaigh.jootil.gui.base;

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
import javafx.stage.Window;

import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.gui.components.HeaderBox;

public abstract class BaseSettingsStage extends Stage
{
	/**
	 * Creates a new base settings stage.
	 * @param owner the owner of the stage
	 * @param image the image of the stage
	 */
	public BaseSettingsStage(Window owner)
	{
		//setup stage
		this.initOwner(owner);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle("Settings");
		this.setResizable(false);
		this.setMinWidth(350);
		this.getIcons().add(new Image("/images/icons/console.png"));
		
		//root pane
		this.borderPane = new BorderPane();
		
		//header box
		this.headerBox = new HeaderBox("Settings", "Configure application-specific settings.");
		this.borderPane.setTop(this.headerBox);
		
		//tab pane
		this.tabPane = new TabPane();
		this.tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		this.borderPane.setCenter(this.tabPane);
		
		//buttons box
		this.buttonsBox = new ButtonsBox(true, true);
		this.buttonsBox.setOnOk(event -> this.ok());
		this.buttonsBox.setOnCancel(event -> this.cancel());
		this.borderPane.setBottom(this.buttonsBox);
		
		//show
		this.setScene(new Scene(this.borderPane));
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
	 */
	public void ok()
	{
		//close
		this.close();
	}
	
	/**
	 * Cancels and closes this stage.
	 */
	public void cancel()
	{
		//close
		this.close();
	}
	
	/**
	 * Returns the border pane for this base.
	 * @return the border pane
	 */
	public BorderPane getBorderPane()
	{
		return this.borderPane;
	}
	
	/**
	 * Returns the tab pane for this base.
	 * @return the tab pane
	 */
	public TabPane getTabPane()
	{
		return this.tabPane;
	}
	
	/**
	 * Returns the buttons box for this base.
	 * @return the buttons box
	 */
	public ButtonsBox getButtonsBox()
	{
		return this.buttonsBox;
	}
	
	private HeaderBox headerBox;
	private BorderPane borderPane;
	private TabPane tabPane;
	private ButtonsBox buttonsBox;
}

package com.connorhaigh.jootil.gui.bases;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.connorhaigh.jootil.gui.ConfirmStage;
import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.gui.components.HeaderBox;

public abstract class BaseManagerStage<E> extends Stage
{
	/**
	 * Creates a new base manager stage.
	 * @param stage the owner
	 * @param title the title
	 * @param description the description
	 * @param selectable if items are selectable
	 * @param addable if items are addable
	 * @param editable if items are editable
	 * @param sourceProperty the source of items
	 */
	public BaseManagerStage(Stage stage, String title, String description, boolean selectable, boolean addable, boolean editable, SimpleListProperty<E> sourceProperty)
	{
		this.sourceProperty = sourceProperty;
		
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
		
		//content pane
		BorderPane contentPane = new BorderPane();
		borderPane.setCenter(contentPane);
		
		//table view
		this.tableView = new TableView<E>();
		this.tableView.setPrefWidth(800);
		this.tableView.setPrefHeight(250);
		this.tableView.setPlaceholder(new Label("No data to display"));
		this.tableView.setOnKeyPressed(event ->
		{
			if (this.tableView.getSelectionModel().getSelectedItem() == null)
			{
				return;
			}
			
			switch (event.getCode())
			{
				case ENTER:
				{
					//select
					this.select();
					
					break;
				}
				case DELETE:
				{
					//remove
					this.remove();
					
					break;
				}
				default:
				{
					break;
				}
			}
		});
		this.tableView.setOnMouseClicked(event ->
		{
			if (this.tableView.getSelectionModel().getSelectedItem() == null)
			{
				return;
			}
			
			if (event.getClickCount() < 2)
			{
				return;
			}
			
			//select
			this.select();
		});
		this.tableView.itemsProperty().bind(this.sourceProperty);
		contentPane.setCenter(this.tableView);
		
		//actions box
		VBox actionsBox = new VBox();
		actionsBox.setPadding(new Insets(10, 10, 10, 10));
		actionsBox.setSpacing(10);
		contentPane.setRight(actionsBox);
		
		if (selectable)
		{
			//select button
			this.selectButton = new Button();
			this.selectButton.setGraphic(new ImageView(new Image("/images/buttons/go.png")));
			this.selectButton.setTooltip(new Tooltip("Select"));
			this.selectButton.setOnAction(event -> this.select());
			actionsBox.getChildren().add(this.selectButton);
		}
		
		if (addable)
		{
			//add button
			this.addButton = new Button();
			this.addButton.setGraphic(new ImageView(new Image("/images/buttons/add.png")));
			this.addButton.setTooltip(new Tooltip("Add"));
			this.addButton.setOnAction(event -> this.performAdd());
			actionsBox.getChildren().add(this.addButton);
		}
		
		if (editable)
		{
			//edit button
			this.editButton = new Button();
			this.editButton.setGraphic(new ImageView(new Image("/images/buttons/edit.png")));
			this.editButton.setTooltip(new Tooltip("Edit"));
			this.editButton.setOnAction(event -> this.performEdit(this.tableView.getSelectionModel().getSelectedItem()));
			this.editButton.disableProperty().bind(this.tableView.getSelectionModel().selectedItemProperty().isNull());
			actionsBox.getChildren().add(this.editButton);
		}
		
		//remove button
		this.removeButton = new Button();
		this.removeButton.setGraphic(new ImageView(new Image("/images/buttons/remove.png")));
		this.removeButton.setTooltip(new Tooltip("Remove"));
		this.removeButton.setOnAction(event -> this.remove());
		this.removeButton.disableProperty().bind(this.tableView.getSelectionModel().selectedItemProperty().isNull());
		actionsBox.getChildren().add(this.removeButton);
		
		//separator
		Separator separator = new Separator();
		actionsBox.getChildren().add(separator);
		
		//clear button
		this.clearButton = new Button();
		this.clearButton.setGraphic(new ImageView(new Image("/images/buttons/clear.png")));
		this.clearButton.setTooltip(new Tooltip("Clear"));
		this.clearButton.setOnAction(event -> this.clear());
		this.clearButton.disableProperty().bind(Bindings.size(this.sourceProperty).lessThanOrEqualTo(0));
		actionsBox.getChildren().add(this.clearButton);
		
		//buttons box
		ButtonsBox buttonsBox = new ButtonsBox(true, false);
		buttonsBox.setOnOk(event -> this.close());
		borderPane.setBottom(buttonsBox);
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Performs a select operation.
	 * @param object the object
	 */
	public void performSelect(E object)
	{
		
	}
	
	/**
	 * Performs an add operation.
	 */
	public void performAdd()
	{
		
	}
	
	/**
	 * Performs an edit operation.
	 * @param object the object
	 */
	public void performEdit(E object)
	{
		
	}
	
	/**
	 * Adds a column for the specified property.
	 * @param name the column name
	 * @param property the property name
	 * @param width the width
	 */
	public <T> void addColumn(String name, int width, Callback<CellDataFeatures<E,T>, ObservableValue<T>> callback)
	{
		//table column
		TableColumn<E, T> tableColumn = new TableColumn<E, T>(name);
		tableColumn.setPrefWidth(width);
		tableColumn.setCellValueFactory(callback);
		this.tableView.getColumns().add(tableColumn);
	}
	
	/**
	 * Adds a column for the specified property.
	 * @param name the column name
	 * @param property the property name
	 * @param width the width
	 */
	public <T> void addColumn(String name, String property, int width)
	{
		this.addColumn(name, width, new PropertyValueFactory<E, T>(property));
	}
	
	
	/**
	 * Selects the current item.
	 */
	private void select()
	{
		//perform
		this.performSelect(this.tableView.getSelectionModel().getSelectedItem());
	}
	
	/**
	 * Removes the currently selected item.
	 */
	private void remove()
	{
		//remove
		this.sourceProperty.remove(this.tableView.getSelectionModel().getSelectedItem());
	}
	
	/**
	 * Clears the list.
	 */
	private void clear()
	{
		//confirm
		boolean confirm = ConfirmStage.showConfirmStage(this, "Clear", "Are you sure you want to clear all entries?");
		
		if (!confirm)
			return;
		
		//clear
		this.sourceProperty.clear();
	}
	
	/**
	 * Returns the table view for this stage.
	 * @return the table view
	 */
	public TableView<E> getTableView()
	{
		return this.tableView;
	}
	
	/**
	 * Returns the add button for this stage.
	 * @return the add button
	 */
	public Button getAddButton()
	{
		return this.addButton;
	}
	
	/**
	 * Returns the edit button for this stage.
	 * @return the edit button
	 */
	public Button getEditButton()
	{
		return this.editButton;
	}
	
	/**
	 * Returns the remove button for this stage.
	 * @return the remove button
	 */
	public Button getRemoveButton()
	{
		return this.removeButton;
	}
	
	/**
	 * Returns the clear button for this stage.
	 * @return the clear button
	 */
	public Button getClearButton()
	{
		return this.clearButton;
	}
	
	/**
	 * Sets the source for this stage.
	 * @param source the source
	 */
	public void setSource(ObservableList<E> source)
	{
		this.sourceProperty.set(source);
	}
	
	/**
	 * Returns the source for this stage.
	 * @return the source
	 */
	public ObservableList<E> getSource()
	{
		return this.sourceProperty.get();
	}
	
	/**
	 * Returns the source property.
	 * @return the source property
	 */
	public SimpleListProperty<E> sourceProperty()
	{
		return this.sourceProperty;
	}
	
	private SimpleListProperty<E> sourceProperty;
	
	private TableView<E> tableView;
	
	private Button selectButton;
	private Button addButton;
	private Button editButton;
	private Button removeButton;
	private Button clearButton;
}

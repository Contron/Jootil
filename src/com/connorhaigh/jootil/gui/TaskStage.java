package com.connorhaigh.jootil.gui;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import com.connorhaigh.jootil.gui.components.ButtonsBox;
import com.connorhaigh.jootil.utilities.Tasks;

public class TaskStage extends Stage
{
	/**
	 * Create a new task stage to monitor a task's progress, and then wait for it.
	 * @param owner the owner of the stage
	 * @param task the task to monitor
	 * @param title the title of the task
	 * @param if the task is cancellable
	 * @param modal if the task should be modal
	 */
	public static void showTaskStage(Window owner, Task<?> task, String title, boolean cancellable, boolean modal)
	{
		//start
		Tasks.start(task);
		
		//show
		TaskStage taskStage = new TaskStage(owner, task, title, cancellable, modal);
		taskStage.showAndWait();
	}
	
	/**
	 * Create a new progress stage to monitor a task's progress.
	 * @param owner the owner of the stage
	 * @param task the task to monitor
	 * @param title the title of the task
	 * @param if the task is cancellable
	 * @param modal if the task should be modal
	 */
	public TaskStage(Window owner, Task<?> task, String title, boolean cancellable, boolean modal)
	{
		this.task = task;
		this.task.addEventHandler(WorkerStateEvent.WORKER_STATE_CANCELLED, event -> this.close());
		this.task.addEventHandler(WorkerStateEvent.WORKER_STATE_FAILED, event -> this.close());
		this.task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> this.close());
		this.cancellable = cancellable;
		
		//setup stage
		this.initOwner(owner);
		this.initModality(modal ? Modality.APPLICATION_MODAL : Modality.NONE);
		this.setResizable(false);
		this.setMinWidth(350);
		this.setTitle(title);
		this.getIcons().add(new Image("/images/icons/task.png"));
		this.setOnCloseRequest(event -> this.tryClose(event));
		
		//root pane
		BorderPane borderPane = new BorderPane();
		
		//content pane
		VBox contentPane = new VBox();
		contentPane.setPadding(new Insets(10, 10, 10, 10));
		contentPane.setSpacing(10);
		borderPane.setCenter(contentPane);
		
		//header label
		Label headerLabel = new Label(title);
		headerLabel.setFont(Font.font(18));
		contentPane.getChildren().add(headerLabel);
		
		//description label
		Label descriptionLabel = new Label();
		descriptionLabel.textProperty().bind(this.task.messageProperty());
		contentPane.getChildren().add(descriptionLabel);
		
		//progress bar
		ProgressBar progressBar = new ProgressBar();
		progressBar.setMaxWidth(Double.MAX_VALUE);
		progressBar.setPrefWidth(250);
		progressBar.progressProperty().bind(this.task.progressProperty());
		contentPane.getChildren().add(progressBar);
		
		//buttons box
		ButtonsBox buttonsBox = new ButtonsBox(false, true);
		buttonsBox.setOnCancel(event -> this.cancel());
		buttonsBox.getCancelButton().setDisable(!this.cancellable);
		borderPane.setBottom(buttonsBox);
		
		//show
		this.setScene(new Scene(borderPane));
		this.sizeToScene();
	}
	
	/**
	 * Attempt to close this window.
	 * @param event the event
	 */
	private void tryClose(WindowEvent event)
	{
		if (this.cancellable)
		{
			this.cancel();
		}
		else
		{
			if (this.task.isRunning()) 
				event.consume();
		}
	}
	
	/**
	 * Cancel the current task.
	 */
	private void cancel()
	{
		//cancel
		this.task.cancel();
	}
	
	private Task<?> task;
	private boolean cancellable;
}

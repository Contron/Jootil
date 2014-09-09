package com.connorhaigh.jootil.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import com.connorhaigh.jootil.utilities.Fonts;
import com.connorhaigh.jootil.utilities.Styles;

public class NotificationStage extends Stage
{
	/**
	 * Creates a new notification stage, and displays it.
	 * @param owner the owner of the stage
	 * @param title the brief title of the notification
	 * @param description the detailed description of the notification
	 */
	public static void showNotificationStage(Window owner, String title, String description)
	{
		//increment
		NotificationStage.active++;
		
		//show
		NotificationStage notificationStage = new NotificationStage(owner, title, description);
		notificationStage.show();
		
		//initialise
		notificationStage.moveToPosition();
		notificationStage.animate(-1, false);
	}
	
	/**
	 * Creates a new notification stage.
	 * @param owner the owner of the stage
	 * @param title the brief title of the notification
	 * @param description the detailed description of the notification
	 */
	private NotificationStage(Window owner, String title, String description)
	{
		this.index = NotificationStage.active;
		this.owner = owner;
		
		this.thread = null;
		this.screenBounds = Screen.getPrimary().getVisualBounds();
		
		//setup stage
		this.initOwner(this.owner);
		this.initStyle(StageStyle.UNDECORATED);
		this.setTitle("Notification");
		this.setResizable(false);
		this.getIcons().add(new Image("/images/icons/information.png"));
		this.setOnCloseRequest(event -> this.confirm());
		
		//root box
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setCursor(Cursor.HAND);
		vbox.setStyle(Styles.build(Styles.CONTROL_BACKGROUND_COLOUR, Styles.CONTROL_BORDER_COLOUR, Styles.CONTROL_ALL_BORDER));
		vbox.setOnMouseClicked(event -> this.animate(1, true));
		
		//header label
		Label headerLabel = new Label((this.owner instanceof Stage) ? ((Stage) this.owner).getTitle().toUpperCase() : "NOTIFICATION");
		headerLabel.setTextFill(Color.GRAY);
		vbox.getChildren().add(headerLabel);
		
		//separator
		Separator separator = new Separator();
		separator.setPrefHeight(10);
		vbox.getChildren().add(separator);
		
		//title label
		Label titleLabel = new Label(title);
		titleLabel.setFont(Fonts.LARGE_FONT);
		vbox.getChildren().add(titleLabel);
		
		//description label
		Label descriptionLabel = new Label(description);
		descriptionLabel.setWrapText(true);
		vbox.getChildren().add(descriptionLabel);
		
		//show
		this.setScene(new Scene(vbox));
		this.sizeToScene();
	}
	
	/**
	 * Moves this stage to the bottom right of the screen.
	 */
	private void moveToPosition()
	{
		//move
		this.setX((this.screenBounds.getWidth() - this.getWidth()) - 10);
		this.setY(this.screenBounds.getHeight());
		
		//show
		this.requestFocus();
		this.toFront();
	}
	
	/**
	 * Animates this notification stage in the specified direction.
	 * @param direction the direction
	 * @param exit if the stage should exit once completed
	 */
	private void animate(int direction, boolean exit)
	{
		//skip if running
		if (this.thread != null && this.thread.isAlive())
			return;
		
		this.thread = new Thread(() ->
		{
			//maximum
			int maximum = (int) (this.getHeight() + 10);
			
			for (int current = 0; current < maximum; current++)
			{
				//progress
				final double progress = ((double) current / (double) maximum);
				
				Platform.runLater(() ->
				{
					//move
					this.setY(this.getY() + (direction * this.index));
					this.setOpacity(direction < 0 ? progress : (1 - progress));
				});
				
				try
				{
					Thread.sleep(16);
				}
				catch (Exception exception)
				{
					
				}
			}
			
			//exit
			if (exit)
				Platform.runLater(() -> this.confirm());
		});
		this.thread.setName("Notification Thread");
		this.thread.start();
	}
	
	/**
	 * Confirms and accept the notification.
	 */
	private void confirm()
	{
		//close
		this.close();
		this.owner.requestFocus();
		
		//decrement
		NotificationStage.active--;
	}
	
	private static int active = 0;
	
	private int index;
	private Window owner;
	
	private Thread thread;
	private Rectangle2D screenBounds;
}

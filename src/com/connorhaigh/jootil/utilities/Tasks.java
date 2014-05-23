package com.connorhaigh.jootil.utilities;

import javafx.concurrent.Task;

public class Tasks 
{
	/**
	 * Wraps a task into a Thread, and starts it.
	 * @param task the task to start
	 */
	public static void start(Task<?> task)
	{
		//start
		Thread thread = new Thread(task);
		thread.setName("Task Thread");
		thread.setDaemon(true);
		thread.start();
	}
}

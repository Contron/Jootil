package com.connorhaigh.jootil.updater;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.application.Platform;

import com.connorhaigh.jootil.updater.gui.UpdateAvailableStage;

public class UpdaterThread extends Thread
{
	/**
	 * Create a new updater thread.
	 * @param clazz the application's main class
	 * @param jarFile the location of the JAR file to check for differences
	 * @param updatePage the page the user can navigate to for downloading the JAR file
	 */
	public UpdaterThread(Class<?> clazz, String jarFile, String updatePage)
	{
		this.clazz = clazz;
		
		this.jarFile = jarFile;
		this.updatePage = updatePage;
	}
	
	/**
	 * Run the updater thread.
	 */
	@Override
	public void run() 
	{
		//initialise
		HttpURLConnection.setFollowRedirects(true);
		
		try
		{
			//sleep for a few moments
			Thread.sleep(3000);
		}
		catch (Exception exception)
		{
			
		}
		
		try
		{
			//get local jar file
			String localJarLocation = this.clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
			File localJarFile = new File(localJarLocation);
			
			//get remove jar file
			URL remoteJarFile = new URL(this.jarFile);
			
			//create connection
			HttpURLConnection httpUrlConnection = (HttpURLConnection) remoteJarFile.openConnection();
			httpUrlConnection.setUseCaches(true);
			httpUrlConnection.setConnectTimeout(5000);
			
			//check response code
			if (httpUrlConnection.getResponseCode() != 200)
				return;
			
			//get file sizes
			long localFileSize = localJarFile.length();
			long remoteFileSize = httpUrlConnection.getContentLengthLong();
			
			//get dates
			long localLastModified = localJarFile.lastModified();
			long remoteLastModified = httpUrlConnection.getLastModified();
			
			//skip if zero length
			if (localFileSize <= 0 || remoteFileSize <= 0)
				return;
			
			//check sizes and dates
			if (localFileSize != remoteFileSize)
				if (remoteLastModified > localLastModified)
					this.displayNotificationWindow();
		}
		catch (Exception exception)
		{
			
		}
	}
	
	/**
	 * Display the notification window to inform of an update.
	 */
	public void displayNotificationWindow()
	{
		Platform.runLater(() ->
		{
			//show
			UpdateAvailableStage updateAvailableStage = new UpdateAvailableStage(this.updatePage);
			updateAvailableStage.showAndWait();
		});
	}
	
	private Class<?> clazz;
	
	private String jarFile;
	private String updatePage;
}
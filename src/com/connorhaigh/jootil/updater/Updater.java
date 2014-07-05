package com.connorhaigh.jootil.updater;

public class Updater 
{
	/**
	 * Passively check for updates from a remote site.
	 * @param clazz the application's main class
	 * @param jarFile the location of the JAR file to check for differences
	 * @param updatePage the page the user can navigate to for downloading the JAR file
	 */
	public static void checkForUpdates(Class<?> clazz, String jarFile, String updatePage)
	{
		//create thread
		UpdaterThread updaterThread = new UpdaterThread(clazz, jarFile, updatePage);
		updaterThread.start();
	}
}

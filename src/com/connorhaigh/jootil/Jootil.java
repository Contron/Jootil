package com.connorhaigh.jootil;

import java.io.File;

import com.connorhaigh.jootil.beans.Platform;
import com.connorhaigh.jootil.utilities.OperatingSystem;

public class Jootil 
{
	/**
	 * Returns the default application directory for the specified class.
	 * This should ideally be the application's main class.
	 * @param clazz the class
	 * @return the preferences directory
	 */
	public static File getApplicationDirectory(Class<?> clazz)
	{
		//get directories and platform
		String userHome = System.getProperty("user.home");
		String directoryName = clazz.getSimpleName();
		Platform platform = OperatingSystem.getPlatform();
		
		switch (platform)
		{
			case WINDOWS:
			{
				return new File(System.getenv("AppData"), directoryName);
			}
			case MACINTOSH:
			{
				return new File(userHome, String.format("Library%sApplication Support%s%s", File.separator, File.separator, directoryName));
			}
			case LINUX:
			{
				return new File(userHome, String.format(".%s", directoryName.toLowerCase()));
			}
			default:
			{
				return new File(userHome, directoryName);
			}
		}
	}
	
	/**
	 * Returns a folder within the application's default directory.
	 * @param clazz the class
	 * @param name the sub directory name
	 * @return the directory
	 */
	public static File getApplicationSubDirectory(Class<?> clazz, String name)
	{
		return new File(Jootil.getApplicationDirectory(clazz), name);
	}
}

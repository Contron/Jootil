package com.connorhaigh.jootil;

import java.io.File;

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
		//get name and home
		String osName = System.getProperty("os.name").toLowerCase();
		String userHome = System.getProperty("user.home");
		
		//check
		if (osName.contains("windows"))
			return new File(System.getenv("APPDATA"), clazz.getSimpleName());
		else if (osName.contains("mac"))
			return new File(userHome, "Library" + File.separator + "Application Support" + File.separator + clazz.getSimpleName());
		else if (osName.contains("unix") || osName.contains("linux"))
			return new File(userHome, "." + clazz.getSimpleName().toLowerCase());
		else
			return new File(userHome, clazz.getSimpleName());
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

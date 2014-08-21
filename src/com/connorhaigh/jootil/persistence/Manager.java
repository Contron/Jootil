package com.connorhaigh.jootil.persistence;

import java.io.File;
import java.io.IOException;

public abstract class Manager 
{
	/**
	 * Creates a new manager.
	 * @param directory the directory of the file
	 * @param file the name of the file
	 */
	public Manager(File directory, String file)
	{
		this.directory = directory;
		this.file = new File(this.directory, file + ".xml");
	}
	
	/**
	 * Loads this manager's properties from a file.
	 * @throws IOException if the file could not be loaded
	 */
	public abstract void load() throws IOException;
	
	/**
	 * Saves this manager's properties to a file.
	 * @throws IOException if the file could not be saved
	 */
	public abstract void save() throws IOException;
	
	/**
	 * Returns the directory of this manager.
	 * @return the directory
	 */
	public File getDirectory()
	{
		return this.directory;
	}
	
	/**
	 * Returns the file of this manager.
	 * @return the file
	 */
	public File getFile()
	{
		return this.file;
	}
	
	private File directory;
	private File file;
}

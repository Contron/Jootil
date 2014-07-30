package com.connorhaigh.jootil.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

public class SettingsManager 
{
	/**
	 * Create a new settings manager.
	 * @param directory the root directory
	 * @param name the settings file
	 * @param settingsGroup the group of initial settings
	 */
	public SettingsManager(File directory, String name, SettingsGroup settingsGroup)
	{
		this.directory = directory;
		this.file = new File(this.directory, name + ".xml");
		
		this.settingsGroup = settingsGroup;
	}
	
	/**
	 * Loads the settings from file.
	 * @throws FileNotFoundException if the file could not be found
	 * @throws ClassCastException if the saved data could not be cast to a hash map
	 */
	@SuppressWarnings("unchecked")
	public void load() throws FileNotFoundException, ClassCastException
	{
		//check directories
		if (!this.directory.exists() || !this.file.exists())
			return;
		
		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(this.file)))
		{
			//read
			this.settingsGroup.setSettings((HashMap<String, Object>) xmlDecoder.readObject());
		}
	}
	
	/**
	 * Saves the settings to file.
	 * @throws FileNotFoundException if the file could not be found
	 */
	public void save() throws FileNotFoundException
	{
		//create directories
		if (!this.directory.exists())
			this.directory.mkdirs();
		
		try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(this.file)))
		{
			//write
			xmlEncoder.writeObject(this.settingsGroup.getSettings());
		}
	}
	
	/**
	 * Sets the settings group for this manager.
	 * @param settingsGroup the settings group
	 */
	public void setSettingsGroup(SettingsGroup settingsGroup)
	{
		this.settingsGroup = settingsGroup;
	}
	
	/**
	 * Returns the settings group for this manager.
	 * @return the settings group
	 */
	public SettingsGroup getSettingsGroup()
	{
		return this.settingsGroup;
	}
	
	private File directory;
	private File file;
	
	private SettingsGroup settingsGroup;
}

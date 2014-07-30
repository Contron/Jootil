package com.connorhaigh.jootil.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

public class SettingsManager extends Manager
{
	/**
	 * Create a new manager.
	 * @param directory the directory of the file
	 * @param file the name of the file
	 * @param settingsGroup the initial group of settings
	 */
	public SettingsManager(File directory, String name, SettingsGroup settingsGroup)
	{
		super(directory, name);
		
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
		if (!this.getDirectory().exists() || !this.getDirectory().exists())
			return;
		
		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(this.getFile())))
		{
			this.settingsGroup.setSettings((HashMap<String, Object>) xmlDecoder.readObject());
		}
	}
	
	/**
	 * Saves the settings to file.
	 * @throws FileNotFoundException if the file could not be found
	 */
	public void save() throws FileNotFoundException
	{
		if (!this.getDirectory().exists())
			this.getDirectory().mkdirs();
		
		try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(this.getFile())))
		{
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
	
	private SettingsGroup settingsGroup;
}

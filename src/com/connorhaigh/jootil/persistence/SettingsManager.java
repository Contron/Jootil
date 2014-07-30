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
	 * Create a new settings manager.
	 * @param directory the directory of the file
	 * @param file the name of the file
	 * @param template the initial settings to use
	 */
	public SettingsManager(File directory, String name, HashMap<String, Object> template)
	{
		super(directory, name);
		
		this.settings = template;
	}
	
	/**
	 * Load this manager's properties from a file.
	 * @throws FileNotFoundException if the file could not be found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void load() throws FileNotFoundException
	{
		if (!this.getDirectory().exists() || !this.getFile().exists())
			return;

		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(this.getFile())))
		{
			this.settings = (HashMap<String, Object>) xmlDecoder.readObject();
		}
	}
	
	/**
	 * Save this manager's properties to a file.
	 * @throws FileNotFoundException if the file could not be found
	 */
	@Override
	public void save() throws FileNotFoundException
	{
		if (!this.getDirectory().exists())
			this.getDirectory().mkdirs();
		
		try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(this.getFile())))
		{
			xmlEncoder.writeObject(this.settings);
		}
	}
	
	/**
	 * Sets the map of settings in this manager.
	 * @param settings the map of settings
	 */
	public void setSettings(HashMap<String, Object> settings)
	{
		this.settings = settings;
	}
	
	/**
	 * Returns the map of settings in this manager.
	 * @return the map of settings
	 */
	public HashMap<String, Object> getSettings()
	{
		return this.settings;
	}
	
	private HashMap<String, Object> settings;
}

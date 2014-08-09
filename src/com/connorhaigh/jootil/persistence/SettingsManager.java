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
	 * Load this manager's settings from a file.
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
			this.settings.putAll((HashMap<String, Object>) xmlDecoder.readObject());
		}
	}
	
	/**
	 * Save this manager's settings to a file.
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
	 * Sets a setting in the map.
	 * @param setting the setting
	 * @param value the value
	 */
	public void set(String setting, Object value)
	{
		this.settings.put(setting, value);
	}
	
	/**
	 * Returns a setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public Object get(String setting)
	{
		return this.settings.get(setting);
	}
	
	/**
	 * Returns a boolean setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public boolean getBoolean(String setting)
	{
		return (boolean) this.get(setting);
	}
	
	/**
	 * Returns a byte setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public byte getByte(String setting)
	{
		return (byte) this.get(setting);
	}
	
	/**
	 * Returns a character setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public char getCharacter(String setting)
	{
		return (char) this.get(setting);
	}
	
	/**
	 * Returns an integer setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public int getInteger(String setting)
	{
		return (int) this.get(setting);
	}
	
	/**
	 * Returns a long setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public long getLong(String setting)
	{
		return (long) this.get(setting);
	}
	
	/**
	 * Returns a double setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public double getDouble(String setting)
	{
		return (double) this.get(setting);
	}
	
	/**
	 * Returns a float setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public float getFloat(String setting)
	{
		return (float) this.get(setting);
	}
	
	/**
	 * Returns a string setting in the map.
	 * @param setting the setting
	 * @return the value
	 */
	public String getString(String setting)
	{
		return (String) this.get(setting);
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

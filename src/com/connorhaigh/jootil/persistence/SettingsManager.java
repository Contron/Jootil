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
	 * @param settingGroup the group of initial settings
	 */
	public SettingsManager(File directory, String name, SettingGroup settingGroup)
	{
		this.directory = directory;
		this.file = new File(this.directory, name + ".xml");
		
		this.settingGroup = settingGroup;
	}
	
	/**
	 * Loads the settings from file.
	 * @throws FileNotFoundException if the file could not be found
	 */
	@SuppressWarnings("unchecked")
	public void load() throws FileNotFoundException
	{
		//check directories
		if (!this.directory.exists() || !this.file.exists())
			return;
		
		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(this.file)))
		{
			//read
			this.settingGroup.setInternalSettings((HashMap<String, Object>) xmlDecoder.readObject());
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
			xmlEncoder.writeObject(this.settingGroup.getInternalSettings());
		}
	}
	
	/**
	 * Sets the setting group for this manager.
	 * @param settingGroup the setting group
	 */
	public void setSettingGroup(SettingGroup settingGroup)
	{
		this.settingGroup = settingGroup;
	}
	
	/**
	 * Returns the setting group for this manager.
	 * @return the setting group
	 */
	public SettingGroup getSettingGroup()
	{
		return this.settingGroup;
	}
	
	private File directory;
	private File file;
	
	private SettingGroup settingGroup;
}

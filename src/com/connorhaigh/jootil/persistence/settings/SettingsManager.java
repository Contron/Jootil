package com.connorhaigh.jootil.persistence.settings;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.connorhaigh.jootil.Jootil;
import com.connorhaigh.jootil.persistence.Persistence;

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
		this.file = new File(this.directory, name + Persistence.EXTENSION);
		
		this.settingGroup = settingGroup;
	}
	
	/**
	 * Loads the settings from file.
	 */
	public void load()
	{
		//check directories
		if (!this.directory.exists() || !this.file.exists())
			return;
		
		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(this.file)))
		{
			//setup
			xmlDecoder.setOwner(this);
			xmlDecoder.setExceptionListener(exception -> Jootil.LOGGER.info("XML settings load error: " + exception.getMessage()));
			
			//read
			this.settingGroup = (SettingGroup) xmlDecoder.readObject();
		}
		catch (Exception exception)
		{
			Jootil.LOGGER.info("Could not load settings for " + this);
		}
	}
	
	/**
	 * Saves the settings to file.
	 */
	public void save()
	{
		//create directories
		if (!this.directory.exists())
			this.directory.mkdirs();
		
		try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(this.file)))
		{
			//setup
			xmlEncoder.setOwner(this);
			xmlEncoder.setExceptionListener(exception -> Jootil.LOGGER.info("XML settings save error: " + exception.getMessage()));
			
			//write
			xmlEncoder.writeObject(this.settingGroup);
		}
		catch (Exception exception)
		{
			Jootil.LOGGER.severe("Could not load objects for " + this);
		}
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

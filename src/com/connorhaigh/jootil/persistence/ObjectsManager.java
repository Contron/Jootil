package com.connorhaigh.jootil.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.connorhaigh.jootil.Jootil;

public class ObjectsManager<Element>
{
	/**
	 * Create a new object manager.
	 * @param directory the root directory
	 * @param name the object file
	 */
	public ObjectsManager(File directory, String name)
	{
		this.directory = directory;
		this.file = new File(this.directory, name + ".xml");
		
		this.objectGroup = new ObjectGroup<Element>();
	}
	
	/**
	 * Loads the objects from file.
	 */
	@SuppressWarnings("unchecked")
	public void load()
	{
		//check directories
		if (!this.directory.exists() || !this.file.exists())
			return;
		
		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(this.file)))
		{
			//setup
			xmlDecoder.setOwner(this);
			xmlDecoder.setExceptionListener(exception -> Jootil.LOGGER.info("XML objects loading error: " + exception.getMessage()));
			
			//read
			this.objectGroup = new ObjectGroup<Element>();
			this.objectGroup.setInternalObjects((ArrayList<Element>) xmlDecoder.readObject());
		}
		catch (Exception exception)
		{
			Jootil.LOGGER.info("Could not load objects for " + this);
		}
	}
	
	/**
	 * Saves the objects to file.
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
			xmlEncoder.setExceptionListener(exception -> Jootil.LOGGER.info("XML objects saving error: " + exception.getMessage()));
			
			//write
			xmlEncoder.writeObject(this.objectGroup.getInternalObjects());
		}
		catch (Exception exception)
		{
			Jootil.LOGGER.severe("Could not save objects for " + this);
		}
	}
	
	/**
	 * Sets the object group for this manager.
	 * @param objectGroup the object group
	 */
	public void setObjectGroup(ObjectGroup<Element> objectGroup)
	{
		this.objectGroup = objectGroup;
	}
	
	/**
	 * Returns the object group for this manager.
	 * @return the object group
	 */
	public ObjectGroup<Element> getObjectGroup()
	{
		return this.objectGroup;
	}
	
	private File directory;
	private File file;
	
	private ObjectGroup<Element> objectGroup;
}

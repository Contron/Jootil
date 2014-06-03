package com.connorhaigh.jootil.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
			//read
			xmlDecoder.setExceptionListener(exception -> Jootil.LOGGER.info("XML objects loading error: " + exception.getMessage()));
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
			//write
			xmlEncoder.setExceptionListener(exception -> Jootil.LOGGER.info("XML objects saving error: " + exception.getMessage()));
			xmlEncoder.writeObject(this.objectGroup.getInternalObjects());
		}
		catch (Exception exception)
		{
			Jootil.LOGGER.severe("Could not save objects for " + this);
		}
	}
	
	/**
	 * Loads a local object from file.
	 * @param file the file to load
	 * @return the object
	 * @throws FileNotFoundException if the file could not be found
	 */
	@SuppressWarnings("unchecked")
	public Element loadLocal(File file) throws FileNotFoundException
	{
		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(file)))
		{
			//read
			xmlDecoder.setExceptionListener(exception -> Jootil.LOGGER.info("XML local object loading error: " + exception.getMessage()));
			Element element = (Element) xmlDecoder.readObject();
			
			return element;
		}
	}
	
	/**
	 * Saves a local object to file.
	 * @param file the file to save
	 * @param element the object to save
	 * @throws FileNotFoundException if the file could not be found
	 */
	public void saveLocal(File file, Element element) throws FileNotFoundException
	{
		try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(file)))
		{
			//write
			xmlEncoder.setExceptionListener(exception -> Jootil.LOGGER.info("XML local object saving error: " + exception.getMessage()));
			xmlEncoder.writeObject(element);
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

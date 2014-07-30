package com.connorhaigh.jootil.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ObjectsManager<Element> extends Manager
{
	/**
	 * Create a new object manager.
	 * @param directory the root directory
	 * @param name the object file
	 */
	public ObjectsManager(File directory, String name)
	{
		super(directory, name);
		
		this.objectsGroup = new ObjectsGroup<Element>();
	}
	
	/**
	 * Loads the objects from file.
	 * @throws FileNotFoundException if the file could not be found
	 * @throws ClassCastException if the saved data could not be cast to a list
	 */
	@SuppressWarnings("unchecked")
	public void load() throws FileNotFoundException, ClassCastException
	{
		if (!this.getDirectory().exists() || !this.getDirectory().exists())
			return;
		
		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(this.getFile())))
		{
			this.objectsGroup.setObjects((ArrayList<Element>) xmlDecoder.readObject());
		}
	}
	
	/**
	 * Saves the objects to file.
	 * @throws FileNotFoundException if the file could not be found
	 */
	public void save() throws FileNotFoundException
	{
		if (!this.getDirectory().exists())
			this.getDirectory().mkdirs();
		
		try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(this.getFile())))
		{
			xmlEncoder.writeObject(this.objectsGroup.getObjects());
		}
	}
	
	/**
	 * Sets the objects group for this manager.
	 * @param objectsGroup the objects group
	 */
	public void setObjectsGroup(ObjectsGroup<Element> objectGroup)
	{
		this.objectsGroup = objectGroup;
	}
	
	/**
	 * Returns the objects group for this manager.
	 * @return the objects group
	 */
	public ObjectsGroup<Element> getObjectsGroup()
	{
		return this.objectsGroup;
	}
	
	private ObjectsGroup<Element> objectsGroup;
}

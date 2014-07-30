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
	 * Create a new settings manager.
	 * @param directory the directory of the file
	 * @param file the name of the file
	 */
	public ObjectsManager(File directory, String name)
	{
		super(directory, name);
		
		this.objects = new ArrayList<Element>();
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
			this.objects = (ArrayList<Element>) xmlDecoder.readObject();
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
			xmlEncoder.writeObject(this.objects);
		}
	}
	
	/**
	 * Sets the list of objects in this manager.
	 * @param objects the list of objects
	 */
	public void setObjects(ArrayList<Element> objects)
	{
		this.objects = objects;
	}
	
	/**
	 * Returns the list of objects in this manager.
	 * @return the list of objects
	 */
	public ArrayList<Element> getObjects()
	{
		return this.objects;
	}
	
	private ArrayList<Element> objects;
}

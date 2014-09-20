package com.connorhaigh.jootil.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ObjectManager<E> extends Manager
{
	/**
	 * Creates a new object manager.
	 * @param directory the directory of the file
	 * @param file the name of the file
	 * @param template the original template to use
	 */
	public ObjectManager(File directory, String name, E template)
	{
		super(directory, name);
		
		this.object = template;
	}
	
	/**
	 * Loads this manager's object from a file.
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
			this.object = (E) xmlDecoder.readObject();
		}
	}
	
	/**
	 * Saves this manager's object to a file.
	 * @throws FileNotFoundException if the file could not be found
	 */
	@Override
	public void save() throws FileNotFoundException
	{
		if (!this.getDirectory().exists())
			this.getDirectory().mkdirs();
		
		try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(this.getFile())))
		{
			xmlEncoder.writeObject(this.object);
		}
	}
	
	/**
	 * Sets the object in this manager.
	 * @param object the object
	 */
	public void set(E object)
	{
		this.object = object;
	}
	
	/**
	 * Returns the object in this manager.
	 * @return the object
	 */
	public E get()
	{
		return this.object;
	}
	
	private E object;
}

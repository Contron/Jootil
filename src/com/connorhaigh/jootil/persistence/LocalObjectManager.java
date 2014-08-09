package com.connorhaigh.jootil.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class LocalObjectManager<Element>
{
	/**
	 * Loads a local object from file.
	 * @param file the file to load
	 * @return the object
	 * @throws FileNotFoundException if the file could not be found
	 * @throws ClassCastException if the object is not an instance of this manager's class
	 */
	@SuppressWarnings("unchecked")
	public Element load(File file) throws FileNotFoundException, ClassCastException
	{
		try (XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(file)))
		{
			return (Element) xmlDecoder.readObject();
		}
	}
	
	/**
	 * Saves a local object to file.
	 * @param file the file to save
	 * @param element the object to save
	 * @throws FileNotFoundException if the file could not be found
	 */
	public void save(File file, Element element) throws FileNotFoundException
	{
		try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(file)))
		{
			xmlEncoder.writeObject(element);
		}
	}
}

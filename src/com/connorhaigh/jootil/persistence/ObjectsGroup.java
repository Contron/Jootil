package com.connorhaigh.jootil.persistence;

import java.util.ArrayList;

public class ObjectsGroup<Element>
{
	/**
	 * Create a new objects group.
	 */
	public ObjectsGroup()
	{
		this.objects = new ArrayList<Element>();
	}
	
	/**
	 * Adds an object to this group.
	 * @param element the object
	 */
	public void add(Element element)
	{
		this.objects.add(element);
	}
	
	/**
	 * Removes an object from this group.
	 * @param element the object
	 */
	public void remove(Element element)
	{
		this.objects.remove(element);
	}
	
	/**
	 * Replaces an object in this group with another object.
	 * @param oldElement the old object
	 * @param newElement the new object
	 */
	public void replace(Element oldElement, Element newElement)
	{
		this.objects.set(this.objects.indexOf(oldElement), newElement);
	}
	
	/**
	 * Returns the object at the specified position.
	 * @param index the index
	 * @return the object
	 */
	public Element get(int index)
	{
		return this.objects.get(index);
	}
	
	/**
	 * Returns the index of the specified object.
	 * @param element the object
	 * @return the index
	 */
	public int indexOf(Element element)
	{
		return this.objects.indexOf(element);
	}
	
	/**
	 * Returns if this group contains a specific element.
	 * @param element the element to check for
	 * @return if this group contains
	 */
	public boolean contains(Element element)
	{
		return this.objects.contains(element);
	}
	
	/**
	 * Clears all objects from this group.
	 */
	public void clear()
	{
		this.objects.clear();
	}
	
	/**
	 * Returns the size of this group.
	 * @return the size
	 */
	public int getSize()
	{
		return this.objects.size();
	}
	
	/**
	 * Sets the list of objects in this group.
	 * @param objects the list of objects
	 */
	public void setObjects(ArrayList<Element> objects)
	{
		this.objects = objects;
	}
	
	/**
	 * Returns the list of objects in this group.
	 * @return the list of objects
	 */
	public ArrayList<Element> getObjects()
	{
		return this.objects;
	}
	
	private ArrayList<Element> objects;
}

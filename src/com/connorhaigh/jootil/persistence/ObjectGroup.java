package com.connorhaigh.jootil.persistence;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObjectGroup<Element> implements Serializable
{
	/**
	 * Create a new object group.
	 */
	public ObjectGroup()
	{
		this.objects = FXCollections.observableArrayList();
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
	public void setObjects(ObservableList<Element> objects)
	{
		this.objects = objects;
	}
	
	/**
	 * Returns the list of objects in this group.
	 * @return the list of objects
	 */
	public ObservableList<Element> getObjects()
	{
		return this.objects;
	}
	
	/**
	 * Sets the non-observable objects list of this group.
	 * This is used when loading the group.
	 * @param objects the objects list
	 */
	protected void setInternalObjects(ArrayList<Element> objects)
	{
		this.objects = FXCollections.observableArrayList(this.objects);
	}
	
	/**
	 * Returns the non-observable settings map of this group.
	 * This is used when saving the group.
	 * @return the objects list
	 */
	protected ArrayList<Element> getInternalObjects()
	{
		return new ArrayList<Element>(this.objects);
	}
	
	public static final long serialVersionUID = 1;
	
	private ObservableList<Element> objects;
}

package com.connorhaigh.jootil.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class SettingsGroup
{
	/**
	 * Create a new settings group.
	 */
	public SettingsGroup()
	{
		this.settings = FXCollections.observableHashMap();
	}
	
	/**
	 * Updates a boolean property in this group.
	 * @param name the name of the setting
	 * @param value the new value
	 */
	public void updateBooleanProperty(String name, boolean value)
	{
		this.getBooleanProperty(name).set(value);
	}
	
	/**
	 * Sets a boolean setting in this group, wrapping it in a property.
	 * @param name the name of the setting
	 * @param value the initial value
	 */
	public void setBoolean(String name, boolean value)
	{
		this.settings.put(name, new SimpleBooleanProperty(value));
	}
	
	/**
	 * Returns a boolean property in this group.
	 * @param name the name of the setting
	 * @return the property
	 */
	public SimpleBooleanProperty getBooleanProperty(String name)
	{
		return (SimpleBooleanProperty) this.settings.get(name);
	}
	
	/**
	 * Returns the boolean value of a property in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public boolean getBoolean(String name)
	{
		return this.getBooleanProperty(name).get();
	}
	
	/**
	 * Updates a double property in this group.
	 * @param name the name of the setting
	 * @param value the new value
	 */
	public void updateDoubleProperty(String name, double value)
	{
		this.getDoubleProperty(name).set(value);
	}
	
	/**
	 * Sets a double setting in this group, wrapping it in a property.
	 * @param name the name of the setting
	 * @param value the initial value
	 */
	public void setDouble(String name, double value)
	{
		this.settings.put(name, new SimpleDoubleProperty(value));
	}
	
	/**
	 * Returns a double property in this group.
	 * @param name the name of the setting
	 * @return the property
	 */
	public SimpleDoubleProperty getDoubleProperty(String name)
	{
		return (SimpleDoubleProperty) this.settings.get(name);
	}
	
	/**
	 * Returns the double value of a property in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public double getDouble(String name)
	{
		return this.getDoubleProperty(name).get();
	}
	
	/**
	 * Updates a float property in this group.
	 * @param name the name of the setting
	 * @param value the new value
	 */
	public void updateFloatProperty(String name, float value)
	{
		this.getFloatProperty(name).set(value);
	}
	
	/**
	 * Sets a float setting in this group, wrapping it in a property.
	 * @param name the name of the setting
	 * @param value the initial value
	 */
	public void setFloat(String name, float value)
	{
		this.settings.put(name, new SimpleFloatProperty(value));
	}
	
	/**
	 * Returns a float property in this group.
	 * @param name the name of the setting
	 * @return the property
	 */
	public SimpleFloatProperty getFloatProperty(String name)
	{
		return (SimpleFloatProperty) this.settings.get(name);
	}
	
	/**
	 * Returns the float value of a property in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public float getFloat(String name)
	{
		return this.getFloatProperty(name).get();
	}
	
	/**
	 * Updates an integer property in this group.
	 * @param name the name of the setting
	 * @param value the new value
	 */
	public void updateIntegerProperty(String name, int value)
	{
		this.getIntegerProperty(name).set(value);
	}
	
	/**
	 * Sets an integer setting in this group, wrapping it in a property.
	 * @param name the name of the setting
	 * @param value the initial value
	 */
	public void setInteger(String name, int value)
	{
		this.settings.put(name, new SimpleIntegerProperty(value));
	}
	
	/**
	 * Returns an integer property in this group.
	 * @param name the name of the setting
	 * @return the property
	 */
	public SimpleIntegerProperty getIntegerProperty(String name)
	{
		return (SimpleIntegerProperty) this.settings.get(name);
	}
	
	/**
	 * Returns the integer value of a property in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public int getInteger(String name)
	{
		return this.getIntegerProperty(name).get();
	}
	
	/**
	 * Updates a long property in this group.
	 * @param name the name of the setting
	 * @param value the new value
	 */
	public void updateLongProperty(String name, long value)
	{
		this.getLongProperty(name).set(value);
	}
	
	/**
	 * Sets a long setting in this group, wrapping it in a property.
	 * @param name the name of the setting
	 * @param value the initial value
	 */
	public void setLong(String name, long value)
	{
		this.settings.put(name, new SimpleLongProperty(value));
	}
	
	/**
	 * Returns a long property in this group.
	 * @param name the name of the setting
	 * @return the property
	 */
	public SimpleLongProperty getLongProperty(String name)
	{
		return (SimpleLongProperty) this.settings.get(name);
	}
	
	/**
	 * Returns the long value of a property in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public long getLong(String name)
	{
		return this.getLongProperty(name).get();
	}
	
	/**
	 * Updates a string property in this group.
	 * @param name the name of the setting
	 * @param value the new value
	 */
	public void updateStringProperty(String name, String value)
	{
		this.getStringProperty(name).set(value);
	}
	
	/**
	 * Sets a string setting in this group, wrapping it in a property.
	 * @param name the name of the setting
	 * @param value the initial value
	 */
	public void setString(String name, String value)
	{
		this.settings.put(name, new SimpleStringProperty(value));
	}
	
	/**
	 * Returns a string property in this group.
	 * @param name the name of the setting
	 * @return the property
	 */
	public SimpleStringProperty getStringProperty(String name)
	{
		return (SimpleStringProperty) this.settings.get(name);
	}
	
	/**
	 * Returns the string value of a property in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public String getString(String name)
	{
		return this.getStringProperty(name).get();
	}
	
	/**
	 * Sets an object setting in this group, wrapping it in a property.
	 * @param name the name of the setting
	 * @param value the initial value
	 */
	public <Element> void setObject(String name, Element value)
	{
		this.settings.put(name, new SimpleObjectProperty<Element>(value));
	}
	
	/**
	 * Returns an object property in this group.
	 * @param name the name of the setting
	 * @return the property
	 */
	@SuppressWarnings("unchecked")
	public <Element> SimpleObjectProperty<Element> getObjectProperty(String name)
	{
		return (SimpleObjectProperty<Element>) this.settings.get(name);
	}
	
	/**
	 * Returns the object value of a property in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public <Element> Element getObject(String name)
	{
		return this.<Element>getObjectProperty(name).get();
	}
	
	/**
	 * Clears this settings group of all its settings.
	 */
	public void clear()
	{
		this.settings.clear();
	}
	
	/**
	 * Returns the size of this group.
	 * @return the size
	 */
	public int getSize()
	{
		return this.settings.size();
	}
	
	/**
	 * Returns the set of keys in this group.
	 * @return the set of keys
	 */
	public Set<String> getKeys()
	{
		return this.settings.keySet();
	}
	
	/**
	 * Returns the collection of values in this group.
	 * @return the collection of values
	 */
	public Collection<Property<?>> getValues()
	{
		return this.settings.values();
	}
	
	/**
	 * Sets the settings map of this group.
	 * @param settings the settings map
	 */
	public void setSettings(ObservableMap<String, Property<?>> settings)
	{
		this.settings = settings;
	}
	
	/**
	 * Returns the settings map of this group.
	 * @return the settings map
	 */
	public ObservableMap<String, Property<?>> getSettings()
	{
		return this.settings;
	}
	
	/**
	 * Sets the internal settings of this group.
	 * @param the settings map
	 */
	protected void setInternalSettings(HashMap<String, Property<?>> settings)
	{
		this.settings = FXCollections.observableMap(settings);
	}
	
	/**
	 * Returns the internal settings of this group.
	 * @return the settings map
	 */
	protected HashMap<String, Object> getInternalSettings()
	{
		return new HashMap<String, Object>(this.settings);
	}
	
	private ObservableMap<String, Property<?>> settings;
}

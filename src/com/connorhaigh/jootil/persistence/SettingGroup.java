package com.connorhaigh.jootil.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class SettingGroup
{
	/**
	 * Create a new settings group.
	 */
	public SettingGroup()
	{
		this.settings = FXCollections.observableHashMap();
	}
	
	/**
	 * Sets a setting in this group.
	 * @param name the name of the setting
	 * @param value the value
	 */
	public void set(String name, Object value)
	{
		this.settings.put(name, value);
	}
	
	/**
	 * Returns a setting in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public Object get(String name)
	{
		return this.settings.get(name);
	}
	
	/**
	 * Returns a byte setting in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public byte getByte(String name)
	{
		return (byte) this.get(name);
	}
	
	/**
	 * Returns an integer setting in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public int getInteger(String name)
	{
		return (int) this.get(name);
	}
	
	/**
	 * Returns a double setting in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public double getDouble(String name)
	{
		return (double) this.get(name);
	}
	
	/**
	 * Returns a boolean setting in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public boolean getBoolean(String name)
	{
		return (boolean) this.get(name);
	}
	
	/**
	 * Returns a string setting in this group.
	 * @param name the name of the setting
	 * @return the value
	 */
	public String getString(String name)
	{
		return (String) this.get(name);
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
	public Collection<Object> getValues()
	{
		return this.settings.values();
	}
	
	/**
	 * Sets the settings map of this group.
	 * @param settings the settings map
	 */
	public void setSettings(ObservableMap<String, Object> settings)
	{
		this.settings = settings;
	}
	
	/**
	 * Returns the settings map of this group.
	 * @return the settings map
	 */
	public ObservableMap<String, Object> getSettings()
	{
		return this.settings;
	}
	
	/**
	 * Sets the internal settings of this group.
	 * @param the settings map
	 */
	protected void setInternalSettings(HashMap<String, Object> settings)
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
	
	private ObservableMap<String, Object> settings;
}

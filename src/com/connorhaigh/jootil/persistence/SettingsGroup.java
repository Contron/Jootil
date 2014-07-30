package com.connorhaigh.jootil.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class SettingsGroup
{
	/**
	 * Create a new settings group.
	 */
	public SettingsGroup()
	{
		this.settings = new HashMap<String, Object>();
	}
	
	/**
	 * Sets a character setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setCharacter(String key, char value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns a character setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public char getCharacter(String key)
	{
		return (char) this.settings.get(key);
	}
	
	/**
	 * Sets a byte setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setByte(String key, byte value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns a byte setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public byte getByte(String key)
	{
		return (byte) this.settings.get(key);
	}
	
	/**
	 * Sets a boolean setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setBoolean(String key, boolean value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns a boolean setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public boolean getBoolean(String key)
	{
		return (boolean) this.settings.get(key);
	}
	
	/**
	 * Sets an integer setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setInteger(String key, int value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns an integer setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public int getInteger(String key)
	{
		return (int) this.settings.get(key);
	}
	
	/**
	 * Sets a long setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setLong(String key, long value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns a long setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public long getLong(String key)
	{
		return (long) this.settings.get(key);
	}
	
	/**
	 * Sets a double setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setDouble(String key, double value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns a double setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public double getDouble(String key)
	{
		return (double) this.settings.get(key);
	}
	
	/**
	 * Sets a float setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setFloat(String key, float value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns a float setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public float getFloat(String key)
	{
		return (float) this.settings.get(key);
	}
	
	/**
	 * Sets a string setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setString(String key, String value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns a string setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public String getString(String key)
	{
		return (String) this.settings.get(key);
	}
	
	/**
	 * Sets an object setting in this settings group.
	 * @param key the key of the setting
	 * @param value the value of the setting
	 */
	public void setObject(String key, Object value)
	{
		this.settings.put(key, value);
	}
	
	/**
	 * Returns an object setting from this settings group.
	 * @param key the key of the setting
	 * @return the value of the setting, or null if it does not exist
	 */
	public Object getObject(String key)
	{
		return (Object) this.settings.get(key);
	}
	
	/**
	 * Removes a setting from this settings group.
	 * @param key the key of the setting
	 */
	public void remove(String key)
	{
		this.settings.remove(key);
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
	public Collection<Object> getValues()
	{
		return this.settings.values();
	}
	
	/**
	 * Sets the settings map of this group.
	 * @param settings the settings map
	 */
	public void setSettings(HashMap<String, Object> settings)
	{
		this.settings = settings;
	}
	
	/**
	 * Returns the settings map of this group.
	 * @return the settings map
	 */
	public HashMap<String, Object> getSettings()
	{
		return this.settings;
	}
	
	private HashMap<String, Object> settings;
}

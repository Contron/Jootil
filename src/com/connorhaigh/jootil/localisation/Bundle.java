package com.connorhaigh.jootil.localisation;

import java.util.Locale;
import java.util.Properties;

public class Bundle 
{
	/**
	 * Create a bundle of language localisation keys.
	 * @param locale the owning locale
	 * @param properties the properties
	 */
	public Bundle(Locale locale, Properties properties)
	{
		this.locale = locale;
		this.language = this.locale.getDisplayLanguage();
		this.properties = properties;
	}
	
	public boolean equals(Object object)
	{
		if (object instanceof Bundle)
		{
			Bundle bundle = (Bundle) object;
			
			return (bundle.locale.equals(bundle.locale) && bundle.properties.equals(bundle.properties));
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Retrieves the localisation matching the specified key.
	 * @param key the key
	 * @return the localised string, or "Missing Localisation" if it could not be found
	 */
	public String retrieveLocalisation(String key)
	{
		return this.properties.getProperty(key, key);
	}
	
	/**
	 * Returns the locale of this bundle.
	 * @return the locale
	 */
	public Locale getLocale()
	{
		return this.locale;
	}
	
	/**
	 * Returns the language of this bundle.
	 * @return the language
	 */
	public String getLanguage()
	{
		return this.language;
	}
	
	private Locale locale;
	private String language;
	private Properties properties;
}

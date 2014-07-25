package com.connorhaigh.jootil.localisation;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

public class LocalisationManager 
{
	/**
	 * Create a new localisation manager.
	 * The class should ideally be the application's main class.
	 * @param clazz the class
	 * @param directory the sub-directory to search in
	 */
	public LocalisationManager(Class<?> clazz, String directory)
	{
		this.clazz = clazz;
		this.directory = directory;
		
		this.locale = Locale.getDefault();
		this.defaultBundle = null;
		this.bundles = new ArrayList<Bundle>();
	}
	
	/**
	 * Load all localisation files, and then assign a default bundle;
	 */
	public void loadAndAssign()
	{
		this.loadBundles();
		this.assignDefaultBundle();
	}
	
	/**
	 * Find and load all localisation files.
	 */
	public void loadBundles()
	{
		for (Locale locale : Locale.getAvailableLocales())
		{
			//get language
			String language = locale.getDisplayLanguage(Locale.ENGLISH);
			String lowercaseLanguage = language.toLowerCase();
			
			//get bundle name and location
			String bundleName = String.format("%s.properties", lowercaseLanguage);
			String bundleLocation = String.format("%s/%s", this.directory, bundleName);
			
			//get URL
			URL bundleURL = this.clazz.getResource(bundleLocation);
			if (bundleURL == null)
				continue;
			
			//properties
			Properties properties = new Properties();
			
			try (InputStream inputStream = bundleURL.openStream())
			{
				//load
				properties.load(inputStream);
			}
			catch (Exception exception)
			{
				continue;
			}
			
			//create and add bundle
			Bundle bundle = new Bundle(locale, properties);
			this.bundles.add(bundle);
		}
	}
	
	/**
	 * Find and assign the bundle for the matching locale.
	 */
	public void assignDefaultBundle()
	{
		//stream
		Bundle bundle = this.bundles.stream()
			.filter(b -> b.getLocale().equals(this.locale))
			.findFirst()
			.orElse(null);
		
		this.defaultBundle = bundle;
	}
	
	/**
	 * Retrieve a localisation for the current locale from the current bundle.
	 * @param key the key
	 * @return the localised string, "No Default Bundle" if there is no bundle, or "Missing Localisation" if it could not be found
	 */
	public String retrieveLocalisation(String key)
	{
		//check bundle
		if (this.defaultBundle == null)
			return String.format("No Default Bundle (%s)", this.locale.getDisplayLanguage(Locale.ENGLISH));
		
		return this.defaultBundle.retrieveLocalisation(key);
	}
	
	/**
	 * Returns the locale of this manager.
	 * @return the locale
	 */
	public Locale getLocale()
	{
		return this.locale;
	}
	
	/**
	 * Returns the default bundle (matching the current localisation) for this manager.
	 * @return the default bundle, or null
	 */
	public Bundle getDefaultBundle()
	{
		return this.defaultBundle;
	}
	
	private Class<?> clazz;
	private String directory;
	
	private Locale locale;
	private Bundle defaultBundle;
	private ArrayList<Bundle> bundles;
}

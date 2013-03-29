package me.deathline75.main.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class MCWorldProperties extends Properties{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2032338856438440700L;
	
	private File file;
	
	public MCWorldProperties(File file){
		this(file, true);
	}
	
	public MCWorldProperties( File file, boolean load){
		this.setFile(file);
		if(load){
			this.load();
		}
	}

	 public void set(String key, boolean value)
	  {
	    setProperty(key, new Boolean(value).toString());
	  }

	  public boolean getBoolean(String key, boolean base) {
	    String value = getProperty(key);
	    try {
	      return (value == null) || (value.trim().equalsIgnoreCase("")) ? base : new Boolean(value).booleanValue(); } catch (Exception e) {
	    }
	    return base;
	  }

	  public void set(String key, int value)
	  {
	    setProperty(key, new Integer(value).toString());
	  }

	  public int getInteger(String key, int base) {
	    String value = getProperty(key);
	    try {
	      return isEmpty(value) ? base : new Integer(value).intValue(); } catch (NumberFormatException e) {
	    }
	    return base;
	  }

	  public void set(String key, char value)
	  {
	    setProperty(key, new Character(value).toString());
	  }

	  public char getCharacter(String key, char base) {
	    String value = getProperty(key);
	    try {
	      return isEmpty(value) ? base : value.charAt(0); } catch (NumberFormatException e) {
	    }
	    return base;
	  }

	  public void set(String key, double value)
	  {
	    setProperty(key, new Double(value).toString());
	  }

	  public double getDouble(String key, double base) {
	    String value = getProperty(key);
	    try {
	      return isEmpty(value) ? base : new Double(value).doubleValue(); } catch (NumberFormatException e) {
	    }
	    return base;
	  }

	  public void set(String key, float value)
	  {
	    setProperty(key, new Float(value).toString());
	  }

	  public float getFloat(String key, float base) {
	    String value = getProperty(key);
	    try {
	      return isEmpty(value) ? base : new Float(value).floatValue(); } catch (NumberFormatException e) {
	    }
	    return base;
	  }

	  public void set(String key, String value)
	  {
	    setProperty(key, value);
	  }

	  public String getString(String key, String base) {
	    String value = getProperty(key);
	    return isEmpty(value) ? base : value;
	  }
	  
	  public boolean save() {
		    return save("");
		  }
	
	  public boolean save(String header) {
		    return save(this.file, header);
		  }

		  public boolean save(File file, String header) {
		    if ((file == null) || (file.isDirectory()))
		      return false;
		    try
		    {
		      if (!file.exists()) {
		        file.createNewFile();
		      }
		      FileOutputStream fos = new FileOutputStream(file);
		      super.store(fos, header);
		      fos.close();
		      return true; } catch (Exception e) {
		    }
		    return false;
		  }

		  public boolean load()
		  {
		    return load(this.file);
		  }

		  public boolean load(File file) {
		    if ((file == null) || (file.isDirectory()))
		      return false;
		    if(!file.getParentFile().exists()){
		    	if(!file.getParentFile().mkdirs()){
		    		file.getParentFile().mkdirs();
		    	}
		    }
		    try
		    {
		      if (!file.exists()) {
		        file.createNewFile();
		        return true;
		      }
		      super.load(new FileInputStream(file));
		      return true; } catch (Exception e) {
		    }
		    return false;
		  }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	  private boolean isEmpty(String value) {
		    return (value == null) || (value.trim().equalsIgnoreCase(""));
		  }

}

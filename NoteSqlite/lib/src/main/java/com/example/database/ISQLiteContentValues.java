package com.example.database;

import java.util.Set;

public interface ISQLiteContentValues {

    /**
     * Adds a value to the set.
     *
     * @param key the name of the value to put
     * @param value the data for the value to put
     */
    public void put(String key, String value);
	
	/**
     * Adds a value to the set.
    *
    * @param key the name of the value to put
    * @param value the data for the value to put
    */
   public void put(String key, Byte value);

   /**
    * Adds a value to the set.
    *
    * @param key the name of the value to put
    * @param value the data for the value to put
    */
   public void put(String key, Short value);

   /**
    * Adds a value to the set.
    *
    * @param key the name of the value to put
    * @param value the data for the value to put
    */
   public void put(String key, Integer value);

   /**
    * Adds a value to the set.
    *
    * @param key the name of the value to put
    * @param value the data for the value to put
    */
   public void put(String key, Long value);

   /**
    * Adds a value to the set.
    *
    * @param key the name of the value to put
    * @param value the data for the value to put
    */
   public void put(String key, Float value);

   /**
    * Adds a value to the set.
    *
    * @param key the name of the value to put
    * @param value the data for the value to put
    */
   public void put(String key, Double value);

   /**
    * Adds a value to the set.
    *
    * @param key the name of the value to put
    * @param value the data for the value to put
    */
   public void put(String key, Boolean value);

   public int size();

   public Set<String> keySet();

   public Object get(String colName);

}

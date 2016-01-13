// Copyright (c) 2015, Intel Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// 3. Neither the name of the copyright holder nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.intel.inde.moe.samples.museummap.common.database;

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

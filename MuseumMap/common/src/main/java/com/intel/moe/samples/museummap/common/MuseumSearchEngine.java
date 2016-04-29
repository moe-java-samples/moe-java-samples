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

package com.intel.moe.samples.museummap.common;

import com.intel.moe.samples.museummap.common.core.Utils;
import com.intel.moe.samples.museummap.common.model.Museum;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MuseumSearchEngine {
    private static final String GOOGLE_MAPS_WEB_API_KEY = "WEB_GOOGLE_MAPS_API_KEY";

    private static final String requestTemplate = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=__latitude__,__longitude__&radius=50000&types=museum&key=API_KEY_HERE";

    private static String lastError = "";

    public static ArrayList<Museum> find(double latitude, double longitude) {
        String request = requestTemplate.replace("__latitude__", String.valueOf(latitude))
                .replace("__longitude__", String.valueOf(longitude)).replace("API_KEY_HERE", GOOGLE_MAPS_WEB_API_KEY);

        URL url;
        ArrayList<Museum> museums;
        try {
            url = new URL(request);
            System.out.println(request);

            InputStream inputStream = url.openStream();
            museums = (ArrayList<Museum>) Utils.get(inputStream);
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            lastError = e.getLocalizedMessage();
            museums = null;
        }
        return museums;
    }

    public static String getLastError() {
        return lastError;
    }
}

package com.intel.inde.moe.samples.webbrowser.common;

public class Bookmarks {
    public static final String[] bookmarks = new String[] { "www.intel.com",
            "www.google.com", "www.apple.com", "www.android.com",};

    public static String prepareUrlAddress(String string) {
        if (string != null) {
            if (!string.startsWith("http://") && !string.startsWith("https://")) {
                string = "http://" + string;
            }
        }
        return string;
    }
}

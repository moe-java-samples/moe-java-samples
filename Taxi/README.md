Taxi Sample
========================

Last year was a great year for taxi apps. Well-known taxi companies all around the world continued to rise. Standard form of hailing a taxi is becoming increasingly uncommon for millions of people. Growing popularity of these services is a startling example of the apps have to change the way we live. Would you like to create next Uber or Lyft? Let's start from our new template!

How to build iOS part
---------------

- Go to the official GoogleMaps cocoapods page: https://cocoapods.org/pods/GoogleMaps
- Click on "See Podspec" link below Library on the bottom right corner.
- At the end of the JSON, find the key "source" and use the url provided to download the SDK.
- Extract content of *.tar.gz archive.
- Find GoogleMaps.framework inside. Put it to the Taxi/ios/lib directory.
- Switch to Android Studio. Expand GoogleMaps.framework in the Project view panel and find GoogleMaps.h file.
- Press right mouse button on it and select "MOE Actions > Generate Bindings" in the context menu.
- Type "com.intel" in the dialog and press "OK". Please wait few seconds.
- That's all!
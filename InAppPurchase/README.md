InAppPurchase sample
====================

This sample demonstrates how to make a purchase in your applications. All work with platform specific API is performed in a special framework which you can use in your applications.
This framework is used in the common part of the sample. We call only our common API from android and ios parts.

In order to run this sample you should set next variables:
1. In com/intel/inapppurchase/android/MainActivity.java:25 you should replace <your license key here> to your android base64EncodedPublicKey 
2. In com/intel/inapppurchase/common/ProductsStore you should set up a list with your products ID.
3. In iOS part you should change application bundle id from com.intel.inapppurchasedemo to your real bundle id.

After that your application is ready to use. You can build a framework and use it in your projects as a 3-rd party library.
InAppPurchase sample
====================

This sample demonstrates how to make a purchase in your applications. All work with platform specific API is performed in a special framework which you can use in your applications.
This framework is used in the common part of the sample. We call only our common API from android and ios parts.

In order to run this sample you should set next variables:
- replace <your license key here> with your android base64EncodedPublicKey in com/intel/inapppurchase/android/MainActivity.java:54
- create a list with your products ID in com/intel/inapppurchase/common/ProductsStore
- change application bundle id from com.intel.inapppurchasedemo to your real bundle id for iOS part

After that your application is ready to use. You can build a framework and use it in your projects as a 3-rd party library.
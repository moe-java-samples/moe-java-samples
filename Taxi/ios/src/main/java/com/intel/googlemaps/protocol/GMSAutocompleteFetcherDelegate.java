package com.intel.googlemaps.protocol;


import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCProtocolName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.foundation.NSArray;
import ios.foundation.NSError;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCProtocolName("GMSAutocompleteFetcherDelegate")
public interface GMSAutocompleteFetcherDelegate {
	@Generated
	@Selector("didAutocompleteWithPredictions:")
	void didAutocompleteWithPredictions(NSArray<?> predictions);

	@Generated
	@Selector("didFailAutocompleteWithError:")
	void didFailAutocompleteWithError(NSError error);
}
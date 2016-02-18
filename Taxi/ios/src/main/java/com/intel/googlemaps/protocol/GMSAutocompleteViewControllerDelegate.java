package com.intel.googlemaps.protocol;


import com.intel.googlemaps.GMSAutocompletePrediction;
import com.intel.googlemaps.GMSAutocompleteViewController;
import com.intel.googlemaps.GMSPlace;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.ObjCProtocolName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.foundation.NSError;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCProtocolName("GMSAutocompleteViewControllerDelegate")
public interface GMSAutocompleteViewControllerDelegate {
	@Generated
	@IsOptional
	@Selector("didRequestAutocompletePredictions:")
	default void didRequestAutocompletePredictions(
			GMSAutocompleteViewController viewController) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("didUpdateAutocompletePredictions:")
	default void didUpdateAutocompletePredictions(
			GMSAutocompleteViewController viewController) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@Selector("viewController:didAutocompleteWithPlace:")
	void viewControllerDidAutocompleteWithPlace(
			GMSAutocompleteViewController viewController, GMSPlace place);

	@Generated
	@Selector("viewController:didFailAutocompleteWithError:")
	void viewControllerDidFailAutocompleteWithError(
			GMSAutocompleteViewController viewController, NSError error);

	@Generated
	@IsOptional
	@Selector("viewController:didSelectPrediction:")
	default boolean viewControllerDidSelectPrediction(
			GMSAutocompleteViewController viewController,
			GMSAutocompletePrediction prediction) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@Selector("wasCancelled:")
	void wasCancelled(GMSAutocompleteViewController viewController);
}
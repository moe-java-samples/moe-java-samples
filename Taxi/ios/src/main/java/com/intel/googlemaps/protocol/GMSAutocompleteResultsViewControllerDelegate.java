package com.intel.googlemaps.protocol;


import com.intel.googlemaps.GMSAutocompletePrediction;
import com.intel.googlemaps.GMSAutocompleteResultsViewController;
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
@ObjCProtocolName("GMSAutocompleteResultsViewControllerDelegate")
public interface GMSAutocompleteResultsViewControllerDelegate {
	@Generated
	@IsOptional
	@Selector("didRequestAutocompletePredictionsForResultsController:")
	default void didRequestAutocompletePredictionsForResultsController(
			GMSAutocompleteResultsViewController resultsController) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("didUpdateAutocompletePredictionsForResultsController:")
	default void didUpdateAutocompletePredictionsForResultsController(
			GMSAutocompleteResultsViewController resultsController) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@Selector("resultsController:didAutocompleteWithPlace:")
	void resultsControllerDidAutocompleteWithPlace(
			GMSAutocompleteResultsViewController resultsController,
			GMSPlace place);

	@Generated
	@Selector("resultsController:didFailAutocompleteWithError:")
	void resultsControllerDidFailAutocompleteWithError(
			GMSAutocompleteResultsViewController resultsController,
			NSError error);

	@Generated
	@IsOptional
	@Selector("resultsController:didSelectPrediction:")
	default boolean resultsControllerDidSelectPrediction(
			GMSAutocompleteResultsViewController resultsController,
			GMSAutocompletePrediction prediction) {
		throw new java.lang.UnsupportedOperationException();
	}
}
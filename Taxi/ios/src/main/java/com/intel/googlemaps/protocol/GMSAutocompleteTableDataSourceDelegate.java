package com.intel.googlemaps.protocol;


import com.intel.googlemaps.GMSAutocompletePrediction;
import com.intel.googlemaps.GMSAutocompleteTableDataSource;
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
@ObjCProtocolName("GMSAutocompleteTableDataSourceDelegate")
public interface GMSAutocompleteTableDataSourceDelegate {
	@Generated
	@IsOptional
	@Selector("didRequestAutocompletePredictionsForTableDataSource:")
	default void didRequestAutocompletePredictionsForTableDataSource(
			GMSAutocompleteTableDataSource tableDataSource) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("didUpdateAutocompletePredictionsForTableDataSource:")
	default void didUpdateAutocompletePredictionsForTableDataSource(
			GMSAutocompleteTableDataSource tableDataSource) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@Selector("tableDataSource:didAutocompleteWithPlace:")
	void tableDataSourceDidAutocompleteWithPlace(
			GMSAutocompleteTableDataSource tableDataSource, GMSPlace place);

	@Generated
	@Selector("tableDataSource:didFailAutocompleteWithError:")
	void tableDataSourceDidFailAutocompleteWithError(
			GMSAutocompleteTableDataSource tableDataSource, NSError error);

	@Generated
	@IsOptional
	@Selector("tableDataSource:didSelectPrediction:")
	default boolean tableDataSourceDidSelectPrediction(
			GMSAutocompleteTableDataSource tableDataSource,
			GMSAutocompletePrediction prediction) {
		throw new java.lang.UnsupportedOperationException();
	}
}
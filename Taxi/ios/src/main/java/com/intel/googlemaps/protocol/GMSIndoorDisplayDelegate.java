package com.intel.googlemaps.protocol;


import com.intel.googlemaps.GMSIndoorBuilding;
import com.intel.googlemaps.GMSIndoorLevel;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.ObjCProtocolName;
import com.intel.inde.moe.natj.objc.ann.Selector;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCProtocolName("GMSIndoorDisplayDelegate")
public interface GMSIndoorDisplayDelegate {
	@Generated
	@IsOptional
	@Selector("didChangeActiveBuilding:")
	default void didChangeActiveBuilding(GMSIndoorBuilding building) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("didChangeActiveLevel:")
	default void didChangeActiveLevel(GMSIndoorLevel level) {
		throw new java.lang.UnsupportedOperationException();
	}
}
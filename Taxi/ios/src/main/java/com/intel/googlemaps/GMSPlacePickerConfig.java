package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.NSObject;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPlacePickerConfig extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPlacePickerConfig(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPlacePickerConfig alloc();

	@Generated
	@Selector("init")
	public native GMSPlacePickerConfig init();

	@Generated
	@Selector("initWithViewport:")
	public native GMSPlacePickerConfig initWithViewport(
			GMSCoordinateBounds viewport);

	@Generated
	@Selector("viewport")
	public native GMSCoordinateBounds viewport();
}
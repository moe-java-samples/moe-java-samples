package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.NSObject;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.NSArray;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPanorama extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPanorama(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPanorama alloc();

	@Generated
	@Selector("coordinate")
	@ByValue
	public native CLLocationCoordinate2D coordinate();

	@Generated
	@Selector("init")
	public native GMSPanorama init();

	@Generated
	@Selector("links")
	public native NSArray<?> links();

	@Generated
	@Selector("panoramaID")
	public native String panoramaID();
}
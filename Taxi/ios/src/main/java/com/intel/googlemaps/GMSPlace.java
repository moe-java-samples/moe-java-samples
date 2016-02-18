package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.NSObject;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.NSArray;
import ios.foundation.NSAttributedString;
import ios.foundation.NSURL;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPlace extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPlace(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPlace alloc();

	@Generated
	@Selector("attributions")
	public native NSAttributedString attributions();

	@Generated
	@Selector("coordinate")
	@ByValue
	public native CLLocationCoordinate2D coordinate();

	@Generated
	@Selector("formattedAddress")
	public native String formattedAddress();

	@Generated
	@Selector("init")
	public native GMSPlace init();

	@Generated
	@Selector("name")
	public native String name();

	@Generated
	@Selector("openNowStatus")
	@NInt
	public native long openNowStatus();

	@Generated
	@Selector("phoneNumber")
	public native String phoneNumber();

	@Generated
	@Selector("placeID")
	public native String placeID();

	@Generated
	@Selector("priceLevel")
	@NInt
	public native long priceLevel();

	@Generated
	@Selector("rating")
	public native float rating();

	@Generated
	@Selector("types")
	public native NSArray<?> types();

	@Generated
	@Selector("viewport")
	public native GMSCoordinateBounds viewport();

	@Generated
	@Selector("website")
	public native NSURL website();
}
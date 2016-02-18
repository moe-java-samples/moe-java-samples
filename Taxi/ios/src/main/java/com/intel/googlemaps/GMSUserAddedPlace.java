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
public class GMSUserAddedPlace extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSUserAddedPlace(Pointer peer) {
		super(peer);
	}

	@Generated
	@Selector("address")
	public native String address();

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSUserAddedPlace alloc();

	@Generated
	@Selector("coordinate")
	@ByValue
	public native CLLocationCoordinate2D coordinate();

	@Generated
	@Selector("init")
	public native GMSUserAddedPlace init();

	@Generated
	@Selector("name")
	public native String name();

	@Generated
	@Selector("phoneNumber")
	public native String phoneNumber();

	@Generated
	@Selector("setAddress:")
	public native void setAddress(String value);

	@Generated
	@Selector("setCoordinate:")
	public native void setCoordinate(@ByValue CLLocationCoordinate2D value);

	@Generated
	@Selector("setName:")
	public native void setName(String value);

	@Generated
	@Selector("setPhoneNumber:")
	public native void setPhoneNumber(String value);

	@Generated
	@Selector("setTypes:")
	public native void setTypes(NSArray<?> value);

	@Generated
	@Selector("setWebsite:")
	public native void setWebsite(String value);

	@Generated
	@Selector("types")
	public native NSArray<?> types();

	@Generated
	@Selector("website")
	public native String website();
}
package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.NSArray;
import ios.foundation.protocol.NSCopying;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSAddress extends NSObject implements NSCopying {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSAddress(Pointer peer) {
		super(peer);
	}

	@Generated
	@Deprecated
	@Selector("addressLine1")
	public native String addressLine1();

	@Generated
	@Deprecated
	@Selector("addressLine2")
	public native String addressLine2();

	@Generated
	@Selector("administrativeArea")
	public native String administrativeArea();

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSAddress alloc();

	@Generated
	@Selector("coordinate")
	@ByValue
	public native CLLocationCoordinate2D coordinate();

	@Generated
	@Owned
	@Selector("copyWithZone:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object copyWithZone(VoidPtr zone);

	@Generated
	@Selector("country")
	public native String country();

	@Generated
	@Selector("init")
	public native GMSAddress init();

	@Generated
	@Selector("lines")
	public native NSArray<?> lines();

	@Generated
	@Selector("locality")
	public native String locality();

	@Generated
	@Selector("postalCode")
	public native String postalCode();

	@Generated
	@Selector("subLocality")
	public native String subLocality();

	@Generated
	@Selector("thoroughfare")
	public native String thoroughfare();
}
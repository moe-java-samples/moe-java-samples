package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NUInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.corelocation.struct.CLLocationCoordinate2D;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSMutablePath extends GMSPath {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSMutablePath(Pointer peer) {
		super(peer);
	}

	@Generated
	@Selector("addCoordinate:")
	public native void addCoordinate(@ByValue CLLocationCoordinate2D coord);

	@Generated
	@Selector("addLatitude:longitude:")
	public native void addLatitudeLongitude(double latitude, double longitude);

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSMutablePath alloc();

	@Generated
	@Selector("init")
	public native GMSMutablePath init();

	@Generated
	@Selector("initWithPath:")
	public native GMSMutablePath initWithPath(GMSPath path);

	@Generated
	@Selector("insertCoordinate:atIndex:")
	public native void insertCoordinateAtIndex(
			@ByValue CLLocationCoordinate2D coord, @NUInt long index);

	@Generated
	@Selector("path")
	public static native GMSMutablePath path();

	@Generated
	@Selector("pathFromEncodedPath:")
	public static native GMSMutablePath pathFromEncodedPath(String encodedPath);

	@Generated
	@Selector("removeAllCoordinates")
	public native void removeAllCoordinates();

	@Generated
	@Selector("removeCoordinateAtIndex:")
	public native void removeCoordinateAtIndex(@NUInt long index);

	@Generated
	@Selector("removeLastCoordinate")
	public native void removeLastCoordinate();

	@Generated
	@Selector("replaceCoordinateAtIndex:withCoordinate:")
	public native void replaceCoordinateAtIndexWithCoordinate(
			@NUInt long index, @ByValue CLLocationCoordinate2D coord);
}
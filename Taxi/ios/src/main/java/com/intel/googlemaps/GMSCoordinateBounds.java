package com.intel.googlemaps;


import com.intel.googlemaps.struct.GMSVisibleRegion;
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

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSCoordinateBounds extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSCoordinateBounds(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSCoordinateBounds alloc();

	@Generated
	@Selector("containsCoordinate:")
	public native boolean containsCoordinate(
			@ByValue CLLocationCoordinate2D coordinate);

	@Generated
	@Selector("includingBounds:")
	public native GMSCoordinateBounds includingBounds(GMSCoordinateBounds other);

	@Generated
	@Selector("includingCoordinate:")
	public native GMSCoordinateBounds includingCoordinate(
			@ByValue CLLocationCoordinate2D coordinate);

	@Generated
	@Selector("includingPath:")
	public native GMSCoordinateBounds includingPath(GMSPath path);

	@Generated
	@Selector("init")
	public native GMSCoordinateBounds init();

	@Generated
	@Selector("initWithCoordinate:coordinate:")
	public native GMSCoordinateBounds initWithCoordinateCoordinate(
			@ByValue CLLocationCoordinate2D coord1,
			@ByValue CLLocationCoordinate2D coord2);

	@Generated
	@Selector("initWithPath:")
	public native GMSCoordinateBounds initWithPath(GMSPath path);

	@Generated
	@Selector("initWithRegion:")
	public native GMSCoordinateBounds initWithRegion(
			@ByValue GMSVisibleRegion region);

	@Generated
	@Selector("intersectsBounds:")
	public native boolean intersectsBounds(GMSCoordinateBounds other);

	@Generated
	@Selector("isValid")
	public native boolean isValid();

	@Generated
	@Selector("northEast")
	@ByValue
	public native CLLocationCoordinate2D northEast();

	@Generated
	@Selector("southWest")
	@ByValue
	public native CLLocationCoordinate2D southWest();
}
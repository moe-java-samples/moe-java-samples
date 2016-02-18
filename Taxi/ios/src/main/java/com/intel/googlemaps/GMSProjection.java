package com.intel.googlemaps;


import com.intel.googlemaps.struct.GMSVisibleRegion;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NFloat;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.NSObject;
import ios.coregraphics.struct.CGPoint;
import ios.corelocation.struct.CLLocationCoordinate2D;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSProjection extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSProjection(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSProjection alloc();

	@Generated
	@Selector("containsCoordinate:")
	public native boolean containsCoordinate(
			@ByValue CLLocationCoordinate2D coordinate);

	@Generated
	@Selector("coordinateForPoint:")
	@ByValue
	public native CLLocationCoordinate2D coordinateForPoint(
			@ByValue CGPoint point);

	@Generated
	@Selector("init")
	public native GMSProjection init();

	@Generated
	@Selector("pointForCoordinate:")
	@ByValue
	public native CGPoint pointForCoordinate(
			@ByValue CLLocationCoordinate2D coordinate);

	@Generated
	@Selector("pointsForMeters:atCoordinate:")
	@NFloat
	public native double pointsForMetersAtCoordinate(double meters,
			@ByValue CLLocationCoordinate2D coordinate);

	@Generated
	@Selector("visibleRegion")
	@ByValue
	public native GMSVisibleRegion visibleRegion();
}
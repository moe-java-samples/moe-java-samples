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
import ios.corelocation.struct.CLLocationCoordinate2D;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSMutableCameraPosition extends GMSCameraPosition {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSMutableCameraPosition(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSMutableCameraPosition alloc();

	@Generated
	@Selector("bearing")
	public native double bearing();

	@Generated
	@Selector("init")
	public native GMSMutableCameraPosition init();

	@Generated
	@Selector("initWithTarget:zoom:bearing:viewingAngle:")
	public native GMSMutableCameraPosition initWithTargetZoomBearingViewingAngle(
			@ByValue CLLocationCoordinate2D target, float zoom, double bearing,
			double viewingAngle);

	@Generated
	@Selector("setBearing:")
	public native void setBearing(double value);

	@Generated
	@Selector("setTarget:")
	public native void setTarget(@ByValue CLLocationCoordinate2D value);

	@Generated
	@Selector("setViewingAngle:")
	public native void setViewingAngle(double value);

	@Generated
	@Selector("setZoom:")
	public native void setZoom(float value);

	@Generated
	@Selector("target")
	@ByValue
	public native CLLocationCoordinate2D target();

	@Generated
	@Selector("viewingAngle")
	public native double viewingAngle();

	@Generated
	@Selector("zoom")
	public native float zoom();
}
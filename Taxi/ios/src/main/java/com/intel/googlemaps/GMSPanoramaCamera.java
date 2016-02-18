package com.intel.googlemaps;


import com.intel.googlemaps.struct.GMSOrientation;
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

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPanoramaCamera extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPanoramaCamera(Pointer peer) {
		super(peer);
	}

	@Generated
	@Selector("FOV")
	public native double FOV();

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPanoramaCamera alloc();

	@Generated
	@Selector("cameraWithHeading:pitch:zoom:")
	public static native GMSPanoramaCamera cameraWithHeadingPitchZoom(
			double heading, double pitch, float zoom);

	@Generated
	@Selector("cameraWithHeading:pitch:zoom:FOV:")
	public static native GMSPanoramaCamera cameraWithHeadingPitchZoomFOV(
			double heading, double pitch, float zoom, double FOV);

	@Generated
	@Selector("cameraWithOrientation:zoom:")
	public static native GMSPanoramaCamera cameraWithOrientationZoom(
			@ByValue GMSOrientation orientation, float zoom);

	@Generated
	@Selector("cameraWithOrientation:zoom:FOV:")
	public static native GMSPanoramaCamera cameraWithOrientationZoomFOV(
			@ByValue GMSOrientation orientation, float zoom, double FOV);

	@Generated
	@Selector("init")
	public native GMSPanoramaCamera init();

	@Generated
	@Selector("initWithOrientation:zoom:FOV:")
	public native GMSPanoramaCamera initWithOrientationZoomFOV(
			@ByValue GMSOrientation orientation, float zoom, double FOV);

	@Generated
	@Selector("orientation")
	@ByValue
	public native GMSOrientation orientation();

	@Generated
	@Selector("zoom")
	public native float zoom();
}
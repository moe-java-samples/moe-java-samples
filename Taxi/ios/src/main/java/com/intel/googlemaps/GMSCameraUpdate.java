package com.intel.googlemaps;


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
import ios.uikit.struct.UIEdgeInsets;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSCameraUpdate extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSCameraUpdate(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSCameraUpdate alloc();

	@Generated
	@Selector("fitBounds:")
	public static native GMSCameraUpdate fitBounds(GMSCoordinateBounds bounds);

	@Generated
	@Selector("fitBounds:withEdgeInsets:")
	public static native GMSCameraUpdate fitBoundsWithEdgeInsets(
			GMSCoordinateBounds bounds, @ByValue UIEdgeInsets edgeInsets);

	@Generated
	@Selector("fitBounds:withPadding:")
	public static native GMSCameraUpdate fitBoundsWithPadding(
			GMSCoordinateBounds bounds, @NFloat double padding);

	@Generated
	@Selector("init")
	public native GMSCameraUpdate init();

	@Generated
	@Selector("scrollByX:Y:")
	public static native GMSCameraUpdate scrollByXY(@NFloat double dX,
			@NFloat double dY);

	@Generated
	@Selector("setCamera:")
	public static native GMSCameraUpdate setCamera(GMSCameraPosition camera);

	@Generated
	@Selector("setTarget:")
	public static native GMSCameraUpdate setTarget(
			@ByValue CLLocationCoordinate2D target);

	@Generated
	@Selector("setTarget:zoom:")
	public static native GMSCameraUpdate setTargetZoom(
			@ByValue CLLocationCoordinate2D target, float zoom);

	@Generated
	@Selector("zoomBy:")
	public static native GMSCameraUpdate zoomBy(float delta);

	@Generated
	@Selector("zoomBy:atPoint:")
	public static native GMSCameraUpdate zoomByAtPoint(float zoom,
			@ByValue CGPoint point);

	@Generated
	@Selector("zoomIn")
	public static native GMSCameraUpdate zoomIn();

	@Generated
	@Selector("zoomOut")
	public static native GMSCameraUpdate zoomOut();

	@Generated
	@Selector("zoomTo:")
	public static native GMSCameraUpdate zoomTo(float zoom);
}
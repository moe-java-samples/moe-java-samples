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
import ios.coregraphics.struct.CGPoint;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.uikit.UIImage;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSGroundOverlay extends GMSOverlay {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSGroundOverlay(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSGroundOverlay alloc();

	@Generated
	@Selector("anchor")
	@ByValue
	public native CGPoint anchor();

	@Generated
	@Selector("bearing")
	public native double bearing();

	@Generated
	@Selector("bounds")
	public native GMSCoordinateBounds bounds();

	@Generated
	@Selector("groundOverlayWithBounds:icon:")
	public static native GMSGroundOverlay groundOverlayWithBoundsIcon(
			GMSCoordinateBounds bounds, UIImage icon);

	@Generated
	@Selector("groundOverlayWithPosition:icon:zoomLevel:")
	public static native GMSGroundOverlay groundOverlayWithPositionIconZoomLevel(
			@ByValue CLLocationCoordinate2D position, UIImage icon,
			@NFloat double zoomLevel);

	@Generated
	@Selector("icon")
	public native UIImage icon();

	@Generated
	@Selector("init")
	public native GMSGroundOverlay init();

	@Generated
	@Selector("opacity")
	public native float opacity();

	@Generated
	@Selector("position")
	@ByValue
	public native CLLocationCoordinate2D position();

	@Generated
	@Selector("setAnchor:")
	public native void setAnchor(@ByValue CGPoint value);

	@Generated
	@Selector("setBearing:")
	public native void setBearing(double value);

	@Generated
	@Selector("setBounds:")
	public native void setBounds(GMSCoordinateBounds value);

	@Generated
	@Selector("setIcon:")
	public native void setIcon(UIImage value);

	@Generated
	@Selector("setOpacity:")
	public native void setOpacity(float value);

	@Generated
	@Selector("setPosition:")
	public native void setPosition(@ByValue CLLocationCoordinate2D value);
}
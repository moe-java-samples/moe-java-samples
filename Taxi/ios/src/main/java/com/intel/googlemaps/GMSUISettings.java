package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
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
public class GMSUISettings extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSUISettings(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSUISettings alloc();

	@Generated
	@Selector("allowScrollGesturesDuringRotateOrZoom")
	public native boolean allowScrollGesturesDuringRotateOrZoom();

	@Generated
	@Selector("compassButton")
	public native boolean compassButton();

	@Generated
	@Selector("consumesGesturesInView")
	public native boolean consumesGesturesInView();

	@Generated
	@Selector("indoorPicker")
	public native boolean indoorPicker();

	@Generated
	@Selector("init")
	public native GMSUISettings init();

	@Generated
	@Selector("myLocationButton")
	public native boolean myLocationButton();

	@Generated
	@Selector("rotateGestures")
	public native boolean rotateGestures();

	@Generated
	@Selector("scrollGestures")
	public native boolean scrollGestures();

	@Generated
	@Selector("setAllGesturesEnabled:")
	public native void setAllGesturesEnabled(boolean enabled);

	@Generated
	@Selector("setAllowScrollGesturesDuringRotateOrZoom:")
	public native void setAllowScrollGesturesDuringRotateOrZoom(boolean value);

	@Generated
	@Selector("setCompassButton:")
	public native void setCompassButton(boolean value);

	@Generated
	@Selector("setConsumesGesturesInView:")
	public native void setConsumesGesturesInView(boolean value);

	@Generated
	@Selector("setIndoorPicker:")
	public native void setIndoorPicker(boolean value);

	@Generated
	@Selector("setMyLocationButton:")
	public native void setMyLocationButton(boolean value);

	@Generated
	@Selector("setRotateGestures:")
	public native void setRotateGestures(boolean value);

	@Generated
	@Selector("setScrollGestures:")
	public native void setScrollGestures(boolean value);

	@Generated
	@Selector("setTiltGestures:")
	public native void setTiltGestures(boolean value);

	@Generated
	@Selector("setZoomGestures:")
	public native void setZoomGestures(boolean value);

	@Generated
	@Selector("tiltGestures")
	public native boolean tiltGestures();

	@Generated
	@Selector("zoomGestures")
	public native boolean zoomGestures();
}
package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NFloat;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.foundation.NSArray;
import ios.uikit.UIColor;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPolyline extends GMSOverlay {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPolyline(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPolyline alloc();

	@Generated
	@Selector("geodesic")
	public native boolean geodesic();

	@Generated
	@Selector("init")
	public native GMSPolyline init();

	@Generated
	@Selector("path")
	public native GMSPath path();

	@Generated
	@Selector("polylineWithPath:")
	public static native GMSPolyline polylineWithPath(GMSPath path);

	@Generated
	@Selector("setGeodesic:")
	public native void setGeodesic(boolean value);

	@Generated
	@Selector("setPath:")
	public native void setPath(GMSPath value);

	@Generated
	@Selector("setSpans:")
	public native void setSpans(NSArray<?> value);

	@Generated
	@Selector("setStrokeColor:")
	public native void setStrokeColor(UIColor value);

	@Generated
	@Selector("setStrokeWidth:")
	public native void setStrokeWidth(@NFloat double value);

	@Generated
	@Selector("spans")
	public native NSArray<?> spans();

	@Generated
	@Selector("strokeColor")
	public native UIColor strokeColor();

	@Generated
	@Selector("strokeWidth")
	@NFloat
	public native double strokeWidth();
}
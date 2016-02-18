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
public class GMSPolygon extends GMSOverlay {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPolygon(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPolygon alloc();

	@Generated
	@Selector("fillColor")
	public native UIColor fillColor();

	@Generated
	@Selector("geodesic")
	public native boolean geodesic();

	@Generated
	@Selector("holes")
	public native NSArray<?> holes();

	@Generated
	@Selector("init")
	public native GMSPolygon init();

	@Generated
	@Selector("path")
	public native GMSPath path();

	@Generated
	@Selector("polygonWithPath:")
	public static native GMSPolygon polygonWithPath(GMSPath path);

	@Generated
	@Selector("setFillColor:")
	public native void setFillColor(UIColor value);

	@Generated
	@Selector("setGeodesic:")
	public native void setGeodesic(boolean value);

	@Generated
	@Selector("setHoles:")
	public native void setHoles(NSArray<?> value);

	@Generated
	@Selector("setPath:")
	public native void setPath(GMSPath value);

	@Generated
	@Selector("setStrokeColor:")
	public native void setStrokeColor(UIColor value);

	@Generated
	@Selector("setStrokeWidth:")
	public native void setStrokeWidth(@NFloat double value);

	@Generated
	@Selector("strokeColor")
	public native UIColor strokeColor();

	@Generated
	@Selector("strokeWidth")
	@NFloat
	public native double strokeWidth();
}
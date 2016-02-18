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
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.uikit.UIColor;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSCircle extends GMSOverlay {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSCircle(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSCircle alloc();

	@Generated
	@Selector("circleWithPosition:radius:")
	public static native GMSCircle circleWithPositionRadius(
			@ByValue CLLocationCoordinate2D position, double radius);

	@Generated
	@Selector("fillColor")
	public native UIColor fillColor();

	@Generated
	@Selector("init")
	public native GMSCircle init();

	@Generated
	@Selector("position")
	@ByValue
	public native CLLocationCoordinate2D position();

	@Generated
	@Selector("radius")
	public native double radius();

	@Generated
	@Selector("setFillColor:")
	public native void setFillColor(UIColor value);

	@Generated
	@Selector("setPosition:")
	public native void setPosition(@ByValue CLLocationCoordinate2D value);

	@Generated
	@Selector("setRadius:")
	public native void setRadius(double value);

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
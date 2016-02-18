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
import ios.uikit.UIColor;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSStyleSpan extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSStyleSpan(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSStyleSpan alloc();

	@Generated
	@Selector("init")
	public native GMSStyleSpan init();

	@Generated
	@Selector("segments")
	public native double segments();

	@Generated
	@Selector("spanWithColor:")
	public static native GMSStyleSpan spanWithColor(UIColor color);

	@Generated
	@Selector("spanWithColor:segments:")
	public static native GMSStyleSpan spanWithColorSegments(UIColor color,
			double segments);

	@Generated
	@Selector("spanWithStyle:")
	public static native GMSStyleSpan spanWithStyle(GMSStrokeStyle style);

	@Generated
	@Selector("spanWithStyle:segments:")
	public static native GMSStyleSpan spanWithStyleSegments(
			GMSStrokeStyle style, double segments);

	@Generated
	@Selector("style")
	public native GMSStrokeStyle style();
}
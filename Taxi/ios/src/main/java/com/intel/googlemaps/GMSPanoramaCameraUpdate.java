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
import ios.NSObject;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPanoramaCameraUpdate extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPanoramaCameraUpdate(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPanoramaCameraUpdate alloc();

	@Generated
	@Selector("init")
	public native GMSPanoramaCameraUpdate init();

	@Generated
	@Selector("rotateBy:")
	public static native GMSPanoramaCameraUpdate rotateBy(
			@NFloat double deltaHeading);

	@Generated
	@Selector("setHeading:")
	public static native GMSPanoramaCameraUpdate setHeading(
			@NFloat double heading);

	@Generated
	@Selector("setPitch:")
	public static native GMSPanoramaCameraUpdate setPitch(@NFloat double pitch);

	@Generated
	@Selector("setZoom:")
	public static native GMSPanoramaCameraUpdate setZoom(@NFloat double zoom);
}
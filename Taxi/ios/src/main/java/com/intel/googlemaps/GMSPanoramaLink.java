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
public class GMSPanoramaLink extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPanoramaLink(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPanoramaLink alloc();

	@Generated
	@Selector("heading")
	@NFloat
	public native double heading();

	@Generated
	@Selector("init")
	public native GMSPanoramaLink init();

	@Generated
	@Selector("panoramaID")
	public native String panoramaID();

	@Generated
	@Selector("setHeading:")
	public native void setHeading(@NFloat double value);

	@Generated
	@Selector("setPanoramaID:")
	public native void setPanoramaID(String value);
}
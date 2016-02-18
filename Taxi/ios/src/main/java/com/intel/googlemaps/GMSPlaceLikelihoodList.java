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
import ios.foundation.NSArray;
import ios.foundation.NSAttributedString;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPlaceLikelihoodList extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPlaceLikelihoodList(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPlaceLikelihoodList alloc();

	@Generated
	@Selector("attributions")
	public native NSAttributedString attributions();

	@Generated
	@Selector("init")
	public native GMSPlaceLikelihoodList init();

	@Generated
	@Selector("likelihoods")
	public native NSArray<?> likelihoods();

	@Generated
	@Selector("setLikelihoods:")
	public native void setLikelihoods(NSArray<?> value);
}
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
public class GMSAutocompletePrediction extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSAutocompletePrediction(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSAutocompletePrediction alloc();

	@Generated
	@Selector("attributedFullText")
	public native NSAttributedString attributedFullText();

	@Generated
	@Selector("attributedPrimaryText")
	public native NSAttributedString attributedPrimaryText();

	@Generated
	@Selector("attributedSecondaryText")
	public native NSAttributedString attributedSecondaryText();

	@Generated
	@Selector("init")
	public native GMSAutocompletePrediction init();

	@Generated
	@Selector("placeID")
	public native String placeID();

	@Generated
	@Selector("types")
	public native NSArray<?> types();
}
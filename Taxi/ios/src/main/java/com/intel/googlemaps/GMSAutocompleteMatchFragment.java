package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NUInt;
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
public class GMSAutocompleteMatchFragment extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSAutocompleteMatchFragment(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSAutocompleteMatchFragment alloc();

	@Generated
	@Selector("init")
	public native GMSAutocompleteMatchFragment init();

	@Generated
	@Selector("length")
	@NUInt
	public native long length();

	@Generated
	@Selector("offset")
	@NUInt
	public native long offset();
}
package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NInt;
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
public class GMSAutocompleteFilter extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSAutocompleteFilter(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSAutocompleteFilter alloc();

	@Generated
	@Selector("country")
	public native String country();

	@Generated
	@Selector("init")
	public native GMSAutocompleteFilter init();

	@Generated
	@Selector("setCountry:")
	public native void setCountry(String value);

	@Generated
	@Selector("setType:")
	public native void setType(@NInt long value);

	@Generated
	@Selector("type")
	@NInt
	public native long type();
}
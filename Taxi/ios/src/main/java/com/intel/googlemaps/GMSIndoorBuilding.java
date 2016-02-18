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
import ios.foundation.NSArray;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSIndoorBuilding extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSIndoorBuilding(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSIndoorBuilding alloc();

	@Generated
	@Selector("defaultLevelIndex")
	@NUInt
	public native long defaultLevelIndex();

	@Generated
	@Selector("init")
	public native GMSIndoorBuilding init();

	@Generated
	@Selector("isUnderground")
	public native boolean isUnderground();

	@Generated
	@Selector("levels")
	public native NSArray<?> levels();
}
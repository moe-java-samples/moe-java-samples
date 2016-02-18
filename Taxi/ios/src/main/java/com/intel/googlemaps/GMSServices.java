package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSServices extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSServices(Pointer peer) {
		super(peer);
	}

	@Generated
	@Selector("SDKVersion")
	public static native String SDKVersion();

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSServices alloc();

	@Generated
	@Selector("init")
	public native GMSServices init();

	@Generated
	@Selector("openSourceLicenseInfo")
	public static native String openSourceLicenseInfo();

	@Generated
	@Selector("provideAPIKey:")
	public static native boolean provideAPIKey(String APIKey);

	@Generated
	@Selector("sharedServices")
	@MappedReturn(ObjCObjectMapper.class)
	public static native ios.protocol.NSObject sharedServices();
}
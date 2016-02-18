package com.intel.googlemaps;


import com.intel.googlemaps.protocol.GMSIndoorDisplayDelegate;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Mapped;
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
public class GMSIndoorDisplay extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSIndoorDisplay(Pointer peer) {
		super(peer);
	}

	@Generated
	@Selector("activeBuilding")
	public native GMSIndoorBuilding activeBuilding();

	@Generated
	@Selector("activeLevel")
	public native GMSIndoorLevel activeLevel();

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSIndoorDisplay alloc();

	@Generated
	@Selector("delegate")
	@MappedReturn(ObjCObjectMapper.class)
	public native GMSIndoorDisplayDelegate delegate();

	@Generated
	@Selector("init")
	public native GMSIndoorDisplay init();

	@Generated
	@Selector("setActiveLevel:")
	public native void setActiveLevel(GMSIndoorLevel value);

	@Generated
	@Selector("setDelegate:")
	public native void setDelegate_unsafe(
			@Mapped(ObjCObjectMapper.class) GMSIndoorDisplayDelegate value);

	@Generated
	public void setDelegate(
			@Mapped(ObjCObjectMapper.class) GMSIndoorDisplayDelegate value) {
		Object __old = delegate();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setDelegate_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}
}
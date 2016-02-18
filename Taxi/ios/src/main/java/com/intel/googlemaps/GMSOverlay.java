package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;
import ios.foundation.protocol.NSCopying;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSOverlay extends NSObject implements NSCopying {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSOverlay(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSOverlay alloc();

	@Generated
	@Owned
	@Selector("copyWithZone:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object copyWithZone(VoidPtr zone);

	@Generated
	@Selector("init")
	public native GMSOverlay init();

	@Generated
	@Selector("isTappable")
	public native boolean isTappable();

	@Generated
	@Selector("map")
	public native GMSMapView map();

	@Generated
	@Selector("setMap:")
	public native void setMap_unsafe(GMSMapView value);

	@Generated
	public void setMap(GMSMapView value) {
		Object __old = map();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setMap_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}

	@Generated
	@Selector("setTappable:")
	public native void setTappable(boolean value);

	@Generated
	@Selector("setTitle:")
	public native void setTitle(String value);

	@Generated
	@Selector("setZIndex:")
	public native void setZIndex(int value);

	@Generated
	@Selector("title")
	public native String title();

	@Generated
	@Selector("zIndex")
	public native int zIndex();
}
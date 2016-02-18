package com.intel.googlemaps;


import com.intel.googlemaps.protocol.GMSTileReceiver;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.NInt;
import com.intel.inde.moe.natj.general.ann.NUInt;
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
public class GMSTileLayer extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSTileLayer(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSTileLayer alloc();

	@Generated
	@Selector("clearTileCache")
	public native void clearTileCache();

	@Generated
	@Selector("fadeIn")
	public native boolean fadeIn();

	@Generated
	@Selector("init")
	public native GMSTileLayer init();

	@Generated
	@Selector("map")
	public native GMSMapView map();

	@Generated
	@Selector("opacity")
	public native float opacity();

	@Generated
	@Selector("requestTileForX:y:zoom:receiver:")
	public native void requestTileForXYZoomReceiver(@NUInt long x,
			@NUInt long y, @NUInt long zoom,
			@Mapped(ObjCObjectMapper.class) GMSTileReceiver receiver);

	@Generated
	@Selector("setFadeIn:")
	public native void setFadeIn(boolean value);

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
	@Selector("setOpacity:")
	public native void setOpacity(float value);

	@Generated
	@Selector("setTileSize:")
	public native void setTileSize(@NInt long value);

	@Generated
	@Selector("setZIndex:")
	public native void setZIndex(int value);

	@Generated
	@Selector("tileSize")
	@NInt
	public native long tileSize();

	@Generated
	@Selector("zIndex")
	public native int zIndex();
}
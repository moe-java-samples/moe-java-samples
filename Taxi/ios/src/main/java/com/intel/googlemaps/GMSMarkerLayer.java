package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.foundation.NSCoder;
import ios.quartzcore.CALayer;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSMarkerLayer extends CALayer {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSMarkerLayer(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSMarkerLayer alloc();

	@Generated
	@Selector("init")
	public native GMSMarkerLayer init();

	@Generated
	@Selector("initWithCoder:")
	public native GMSMarkerLayer initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithLayer:")
	public native GMSMarkerLayer initWithLayer(
			@Mapped(ObjCObjectMapper.class) Object layer);

	@Generated
	@Selector("latitude")
	public native double latitude();

	@Generated
	@Selector("layer")
	public static native GMSMarkerLayer layer();

	@Generated
	@Selector("longitude")
	public native double longitude();

	@Generated
	@Selector("opacity")
	public native float opacity();

	@Generated
	@Selector("rotation")
	public native double rotation();

	@Generated
	@Selector("setLatitude:")
	public native void setLatitude(double value);

	@Generated
	@Selector("setLongitude:")
	public native void setLongitude(double value);

	@Generated
	@Selector("setOpacity:")
	public native void setOpacity(float value);

	@Generated
	@Selector("setRotation:")
	public native void setRotation(double value);
}
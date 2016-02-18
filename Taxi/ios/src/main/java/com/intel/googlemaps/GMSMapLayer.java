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

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSMapLayer extends GMSCALayer {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSMapLayer(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSMapLayer alloc();

	@Generated
	@Selector("cameraBearing")
	public native double cameraBearing();

	@Generated
	@Selector("cameraLatitude")
	public native double cameraLatitude();

	@Generated
	@Selector("cameraLongitude")
	public native double cameraLongitude();

	@Generated
	@Selector("cameraViewingAngle")
	public native double cameraViewingAngle();

	@Generated
	@Selector("cameraZoomLevel")
	public native float cameraZoomLevel();

	@Generated
	@Selector("init")
	public native GMSMapLayer init();

	@Generated
	@Selector("initWithCoder:")
	public native GMSMapLayer initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithLayer:")
	public native GMSMapLayer initWithLayer(
			@Mapped(ObjCObjectMapper.class) Object layer);

	@Generated
	@Selector("layer")
	public static native GMSMapLayer layer();

	@Generated
	@Selector("setCameraBearing:")
	public native void setCameraBearing(double value);

	@Generated
	@Selector("setCameraLatitude:")
	public native void setCameraLatitude(double value);

	@Generated
	@Selector("setCameraLongitude:")
	public native void setCameraLongitude(double value);

	@Generated
	@Selector("setCameraViewingAngle:")
	public native void setCameraViewingAngle(double value);

	@Generated
	@Selector("setCameraZoomLevel:")
	public native void setCameraZoomLevel(float value);
}
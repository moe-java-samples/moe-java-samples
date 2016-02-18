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
public class GMSPanoramaLayer extends GMSCALayer {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPanoramaLayer(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPanoramaLayer alloc();

	@Generated
	@Selector("cameraFOV")
	public native double cameraFOV();

	@Generated
	@Selector("cameraHeading")
	public native double cameraHeading();

	@Generated
	@Selector("cameraPitch")
	public native double cameraPitch();

	@Generated
	@Selector("cameraZoom")
	public native float cameraZoom();

	@Generated
	@Selector("init")
	public native GMSPanoramaLayer init();

	@Generated
	@Selector("initWithCoder:")
	public native GMSPanoramaLayer initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithLayer:")
	public native GMSPanoramaLayer initWithLayer(
			@Mapped(ObjCObjectMapper.class) Object layer);

	@Generated
	@Selector("layer")
	public static native GMSPanoramaLayer layer();

	@Generated
	@Selector("setCameraFOV:")
	public native void setCameraFOV(double value);

	@Generated
	@Selector("setCameraHeading:")
	public native void setCameraHeading(double value);

	@Generated
	@Selector("setCameraPitch:")
	public native void setCameraPitch(double value);

	@Generated
	@Selector("setCameraZoom:")
	public native void setCameraZoom(float value);
}
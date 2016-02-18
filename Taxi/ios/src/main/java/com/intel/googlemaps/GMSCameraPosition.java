package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.NFloat;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.general.ptr.VoidPtr;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.protocol.NSCopying;
import ios.foundation.protocol.NSMutableCopying;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSCameraPosition extends NSObject implements NSCopying,
		NSMutableCopying {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSCameraPosition(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSCameraPosition alloc();

	@Generated
	@Selector("bearing")
	public native double bearing();

	@Generated
	@Selector("cameraWithLatitude:longitude:zoom:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object cameraWithLatitudeLongitudeZoom(
			double latitude, double longitude, float zoom);

	@Generated
	@Selector("cameraWithLatitude:longitude:zoom:bearing:viewingAngle:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object cameraWithLatitudeLongitudeZoomBearingViewingAngle(
			double latitude, double longitude, float zoom, double bearing,
			double viewingAngle);

	@Generated
	@Selector("cameraWithTarget:zoom:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object cameraWithTargetZoom(
			@ByValue CLLocationCoordinate2D target, float zoom);

	@Generated
	@Selector("cameraWithTarget:zoom:bearing:viewingAngle:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object cameraWithTargetZoomBearingViewingAngle(
			@ByValue CLLocationCoordinate2D target, float zoom, double bearing,
			double viewingAngle);

	@Generated
	@Owned
	@Selector("copyWithZone:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object copyWithZone(VoidPtr zone);

	@Generated
	@Selector("init")
	public native GMSCameraPosition init();

	@Generated
	@Selector("initWithTarget:zoom:bearing:viewingAngle:")
	public native GMSCameraPosition initWithTargetZoomBearingViewingAngle(
			@ByValue CLLocationCoordinate2D target, float zoom, double bearing,
			double viewingAngle);

	@Generated
	@Selector("mutableCopyWithZone:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object mutableCopyWithZone(VoidPtr zone);

	@Generated
	@Selector("target")
	@ByValue
	public native CLLocationCoordinate2D target();

	@Generated
	@Selector("viewingAngle")
	public native double viewingAngle();

	@Generated
	@Selector("zoom")
	public native float zoom();

	@Generated
	@Selector("zoomAtCoordinate:forMeters:perPoints:")
	public static native float zoomAtCoordinateForMetersPerPoints(
			@ByValue CLLocationCoordinate2D coordinate, double meters,
			@NFloat double points);
}
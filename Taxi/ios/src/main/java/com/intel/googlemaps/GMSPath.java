package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.NUInt;
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
public class GMSPath extends NSObject implements NSCopying, NSMutableCopying {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPath(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPath alloc();

	@Generated
	@Selector("coordinateAtIndex:")
	@ByValue
	public native CLLocationCoordinate2D coordinateAtIndex(@NUInt long index);

	@Generated
	@Owned
	@Selector("copyWithZone:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object copyWithZone(VoidPtr zone);

	@Generated
	@Selector("count")
	@NUInt
	public native long count();

	@Generated
	@Selector("encodedPath")
	public native String encodedPath();

	@Generated
	@Selector("init")
	public native GMSPath init();

	@Generated
	@Selector("initWithPath:")
	public native GMSPath initWithPath(GMSPath path);

	@Generated
	@Selector("lengthOfKind:")
	public native double lengthOfKind(int kind);

	@Generated
	@Selector("mutableCopyWithZone:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object mutableCopyWithZone(VoidPtr zone);

	@Generated
	@Selector("path")
	public static native GMSPath path();

	@Generated
	@Selector("pathFromEncodedPath:")
	public static native GMSPath pathFromEncodedPath(String encodedPath);

	@Generated
	@Selector("pathOffsetByLatitude:longitude:")
	@MappedReturn(ObjCObjectMapper.class)
	public native Object pathOffsetByLatitudeLongitude(double deltaLatitude,
			double deltaLongitude);

	@Generated
	@Selector("segmentsForLength:kind:")
	public native double segmentsForLengthKind(double length, int kind);
}
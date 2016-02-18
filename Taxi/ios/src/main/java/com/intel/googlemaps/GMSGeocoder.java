package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCBlock;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.NSObject;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.NSError;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSGeocoder extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSGeocoder(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSGeocoder alloc();

	@Generated
	@Selector("geocoder")
	public static native GMSGeocoder geocoder();

	@Generated
	@Selector("init")
	public native GMSGeocoder init();

	@Generated
	@Selector("reverseGeocodeCoordinate:completionHandler:")
	public native void reverseGeocodeCoordinateCompletionHandler(
			@ByValue CLLocationCoordinate2D coordinate,
			@ObjCBlock(name = "call_reverseGeocodeCoordinateCompletionHandler") Block_reverseGeocodeCoordinateCompletionHandler handler);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_reverseGeocodeCoordinateCompletionHandler {
		@Generated
		void call_reverseGeocodeCoordinateCompletionHandler(
				GMSReverseGeocodeResponse arg0, NSError arg1);
	}
}
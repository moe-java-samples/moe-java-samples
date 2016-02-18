package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.NUInt;
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
public class GMSPanoramaService extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPanoramaService(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPanoramaService alloc();

	@Generated
	@Selector("init")
	public native GMSPanoramaService init();

	@Generated
	@Selector("requestPanoramaNearCoordinate:callback:")
	public native void requestPanoramaNearCoordinateCallback(
			@ByValue CLLocationCoordinate2D coordinate,
			@ObjCBlock(name = "call_requestPanoramaNearCoordinateCallback") Block_requestPanoramaNearCoordinateCallback callback);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_requestPanoramaNearCoordinateCallback {
		@Generated
		void call_requestPanoramaNearCoordinateCallback(GMSPanorama arg0,
				NSError arg1);
	}

	@Generated
	@Selector("requestPanoramaNearCoordinate:radius:callback:")
	public native void requestPanoramaNearCoordinateRadiusCallback(
			@ByValue CLLocationCoordinate2D coordinate,
			@NUInt long radius,
			@ObjCBlock(name = "call_requestPanoramaNearCoordinateRadiusCallback") Block_requestPanoramaNearCoordinateRadiusCallback callback);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_requestPanoramaNearCoordinateRadiusCallback {
		@Generated
		void call_requestPanoramaNearCoordinateRadiusCallback(GMSPanorama arg0,
				NSError arg1);
	}

	@Generated
	@Selector("requestPanoramaWithID:callback:")
	public native void requestPanoramaWithIDCallback(
			String panoramaID,
			@ObjCBlock(name = "call_requestPanoramaWithIDCallback") Block_requestPanoramaWithIDCallback callback);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_requestPanoramaWithIDCallback {
		@Generated
		void call_requestPanoramaWithIDCallback(GMSPanorama arg0, NSError arg1);
	}
}
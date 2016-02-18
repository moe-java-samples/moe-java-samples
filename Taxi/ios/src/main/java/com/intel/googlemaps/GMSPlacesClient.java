package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCBlock;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.NSObject;
import ios.foundation.NSArray;
import ios.foundation.NSError;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPlacesClient extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPlacesClient(Pointer peer) {
		super(peer);
	}

	@Generated
	@Selector("addPlace:callback:")
	public native void addPlaceCallback(
			GMSUserAddedPlace place,
			@ObjCBlock(name = "call_addPlaceCallback") Block_addPlaceCallback callback);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_addPlaceCallback {
		@Generated
		void call_addPlaceCallback(GMSPlace arg0, NSError arg1);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPlacesClient alloc();

	@Generated
	@Selector("autocompleteQuery:bounds:filter:callback:")
	public native void autocompleteQueryBoundsFilterCallback(
			String query,
			GMSCoordinateBounds bounds,
			GMSAutocompleteFilter filter,
			@ObjCBlock(name = "call_autocompleteQueryBoundsFilterCallback") Block_autocompleteQueryBoundsFilterCallback callback);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_autocompleteQueryBoundsFilterCallback {
		@Generated
		void call_autocompleteQueryBoundsFilterCallback(NSArray<?> arg0,
				NSError arg1);
	}

	@Generated
	@Selector("currentPlaceWithCallback:")
	public native void currentPlaceWithCallback(
			@ObjCBlock(name = "call_currentPlaceWithCallback") Block_currentPlaceWithCallback callback);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_currentPlaceWithCallback {
		@Generated
		void call_currentPlaceWithCallback(GMSPlaceLikelihoodList arg0,
				NSError arg1);
	}

	@Generated
	@Selector("init")
	public native GMSPlacesClient init();

	@Generated
	@Selector("lookUpPlaceID:callback:")
	public native void lookUpPlaceIDCallback(
			String placeID,
			@ObjCBlock(name = "call_lookUpPlaceIDCallback") Block_lookUpPlaceIDCallback callback);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_lookUpPlaceIDCallback {
		@Generated
		void call_lookUpPlaceIDCallback(GMSPlace arg0, NSError arg1);
	}

	@Generated
	@Selector("reportDeviceAtPlaceWithID:")
	public native void reportDeviceAtPlaceWithID(String placeID);

	@Generated
	@Selector("sharedClient")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object sharedClient();
}
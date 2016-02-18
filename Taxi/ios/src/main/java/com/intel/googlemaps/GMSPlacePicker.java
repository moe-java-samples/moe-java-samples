package com.intel.googlemaps;


import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCBlock;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.NSObject;
import ios.foundation.NSError;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPlacePicker extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPlacePicker(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPlacePicker alloc();

	@Generated
	@Selector("config")
	public native GMSPlacePickerConfig config();

	@Generated
	@Selector("init")
	public native GMSPlacePicker init();

	@Generated
	@Selector("initWithConfig:")
	public native GMSPlacePicker initWithConfig(GMSPlacePickerConfig config);

	@Generated
	@Selector("pickPlaceWithCallback:")
	public native void pickPlaceWithCallback(
			@ObjCBlock(name = "call_pickPlaceWithCallback") Block_pickPlaceWithCallback callback);

	@Runtime(ObjCRuntime.class)
	@Generated
	public interface Block_pickPlaceWithCallback {
		@Generated
		void call_pickPlaceWithCallback(GMSPlace arg0, NSError arg1);
	}
}
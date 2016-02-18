package com.intel.googlemaps;


import com.intel.googlemaps.protocol.GMSAutocompleteViewControllerDelegate;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.foundation.NSBundle;
import ios.foundation.NSCoder;
import ios.uikit.UIViewController;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSAutocompleteViewController extends UIViewController {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSAutocompleteViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSAutocompleteViewController alloc();

	@Generated
	@Selector("autocompleteBounds")
	public native GMSCoordinateBounds autocompleteBounds();

	@Generated
	@Selector("autocompleteFilter")
	public native GMSAutocompleteFilter autocompleteFilter();

	@Generated
	@Selector("delegate")
	@MappedReturn(ObjCObjectMapper.class)
	public native GMSAutocompleteViewControllerDelegate delegate();

	@Generated
	@Selector("init")
	public native GMSAutocompleteViewController init();

	@Generated
	@Selector("initWithCoder:")
	public native GMSAutocompleteViewController initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithNibName:bundle:")
	public native GMSAutocompleteViewController initWithNibNameBundle(
			String nibNameOrNil, NSBundle nibBundleOrNil);

	@Generated
	@Selector("setAutocompleteBounds:")
	public native void setAutocompleteBounds(GMSCoordinateBounds value);

	@Generated
	@Selector("setAutocompleteFilter:")
	public native void setAutocompleteFilter(GMSAutocompleteFilter value);

	@Generated
	@Selector("setDelegate:")
	public native void setDelegate_unsafe(
			@Mapped(ObjCObjectMapper.class) GMSAutocompleteViewControllerDelegate value);

	@Generated
	public void setDelegate(
			@Mapped(ObjCObjectMapper.class) GMSAutocompleteViewControllerDelegate value) {
		Object __old = delegate();
		if (value != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.associateObjCObject(this,
					value);
		}
		setDelegate_unsafe(value);
		if (__old != null) {
			com.intel.inde.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this,
					__old);
		}
	}
}
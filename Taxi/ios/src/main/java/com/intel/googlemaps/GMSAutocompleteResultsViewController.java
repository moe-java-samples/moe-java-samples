package com.intel.googlemaps;


import com.intel.googlemaps.protocol.GMSAutocompleteResultsViewControllerDelegate;
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
import ios.uikit.UISearchController;
import ios.uikit.UIViewController;
import ios.uikit.protocol.UISearchResultsUpdating;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSAutocompleteResultsViewController extends UIViewController
		implements UISearchResultsUpdating {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSAutocompleteResultsViewController(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSAutocompleteResultsViewController alloc();

	@Generated
	@Selector("autocompleteBounds")
	public native GMSCoordinateBounds autocompleteBounds();

	@Generated
	@Selector("autocompleteFilter")
	public native GMSAutocompleteFilter autocompleteFilter();

	@Generated
	@Selector("delegate")
	@MappedReturn(ObjCObjectMapper.class)
	public native GMSAutocompleteResultsViewControllerDelegate delegate();

	@Generated
	@Selector("init")
	public native GMSAutocompleteResultsViewController init();

	@Generated
	@Selector("initWithCoder:")
	public native GMSAutocompleteResultsViewController initWithCoder(
			NSCoder aDecoder);

	@Generated
	@Selector("initWithNibName:bundle:")
	public native GMSAutocompleteResultsViewController initWithNibNameBundle(
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
			@Mapped(ObjCObjectMapper.class) GMSAutocompleteResultsViewControllerDelegate value);

	@Generated
	public void setDelegate(
			@Mapped(ObjCObjectMapper.class) GMSAutocompleteResultsViewControllerDelegate value) {
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

	@Generated
	@Selector("updateSearchResultsForSearchController:")
	public native void updateSearchResultsForSearchController(
			UISearchController searchController);
}
package com.intel.googlemaps;


import com.intel.googlemaps.protocol.GMSAutocompleteFetcherDelegate;
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
import ios.NSObject;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSAutocompleteFetcher extends NSObject {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSAutocompleteFetcher(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSAutocompleteFetcher alloc();

	@Generated
	@Selector("autocompleteBounds")
	public native GMSCoordinateBounds autocompleteBounds();

	@Generated
	@Selector("autocompleteFilter")
	public native GMSAutocompleteFilter autocompleteFilter();

	@Generated
	@Selector("delegate")
	@MappedReturn(ObjCObjectMapper.class)
	public native GMSAutocompleteFetcherDelegate delegate();

	@Generated
	@Selector("init")
	public native GMSAutocompleteFetcher init();

	@Generated
	@Selector("initWithBounds:filter:")
	public native GMSAutocompleteFetcher initWithBoundsFilter(
			GMSCoordinateBounds bounds, GMSAutocompleteFilter filter);

	@Generated
	@Selector("setAutocompleteBounds:")
	public native void setAutocompleteBounds(GMSCoordinateBounds value);

	@Generated
	@Selector("setAutocompleteFilter:")
	public native void setAutocompleteFilter(GMSAutocompleteFilter value);

	@Generated
	@Selector("setDelegate:")
	public native void setDelegate_unsafe(
			@Mapped(ObjCObjectMapper.class) GMSAutocompleteFetcherDelegate value);

	@Generated
	public void setDelegate(
			@Mapped(ObjCObjectMapper.class) GMSAutocompleteFetcherDelegate value) {
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
	@Selector("sourceTextHasChanged:")
	public native void sourceTextHasChanged(String text);
}
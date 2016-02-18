package com.intel.googlemaps;


import com.intel.googlemaps.protocol.GMSPanoramaViewDelegate;
import com.intel.googlemaps.struct.GMSOrientation;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.Pointer;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Mapped;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.NUInt;
import com.intel.inde.moe.natj.general.ann.Owned;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.ObjCClassBinding;
import com.intel.inde.moe.natj.objc.ann.Selector;
import com.intel.inde.moe.natj.objc.map.ObjCObjectMapper;
import ios.coregraphics.struct.CGPoint;
import ios.coregraphics.struct.CGRect;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.NSCoder;
import ios.uikit.UIView;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCClassBinding
public class GMSPanoramaView extends UIView {
	static {
		NatJ.register();
	}

	@Generated
	protected GMSPanoramaView(Pointer peer) {
		super(peer);
	}

	@Generated
	@Owned
	@Selector("alloc")
	public static native GMSPanoramaView alloc();

	@Generated
	@Selector("animateToCamera:animationDuration:")
	public native void animateToCameraAnimationDuration(
			GMSPanoramaCamera camera, double duration);

	@Generated
	@Selector("camera")
	public native GMSPanoramaCamera camera();

	@Generated
	@Selector("delegate")
	@MappedReturn(ObjCObjectMapper.class)
	public native GMSPanoramaViewDelegate delegate();

	@Generated
	@Selector("init")
	public native GMSPanoramaView init();

	@Generated
	@Selector("initWithCoder:")
	public native GMSPanoramaView initWithCoder(NSCoder aDecoder);

	@Generated
	@Selector("initWithFrame:")
	public native GMSPanoramaView initWithFrame(@ByValue CGRect frame);

	@Generated
	@Selector("layer")
	public native GMSPanoramaLayer layer();

	@Generated
	@Selector("moveNearCoordinate:")
	public native void moveNearCoordinate(
			@ByValue CLLocationCoordinate2D coordinate);

	@Generated
	@Selector("moveNearCoordinate:radius:")
	public native void moveNearCoordinateRadius(
			@ByValue CLLocationCoordinate2D coordinate, @NUInt long radius);

	@Generated
	@Selector("moveToPanoramaID:")
	public native void moveToPanoramaID(String panoramaID);

	@Generated
	@Selector("navigationGestures")
	public native boolean navigationGestures();

	@Generated
	@Selector("navigationLinksHidden")
	public native boolean navigationLinksHidden();

	@Generated
	@Selector("orientationForPoint:")
	@ByValue
	public native GMSOrientation orientationForPoint(@ByValue CGPoint point);

	@Generated
	@Selector("orientationGestures")
	public native boolean orientationGestures();

	@Generated
	@Selector("panorama")
	public native GMSPanorama panorama();

	@Generated
	@Selector("panoramaWithFrame:nearCoordinate:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object panoramaWithFrameNearCoordinate(
			@ByValue CGRect frame, @ByValue CLLocationCoordinate2D coordinate);

	@Generated
	@Selector("panoramaWithFrame:nearCoordinate:radius:")
	@MappedReturn(ObjCObjectMapper.class)
	public static native Object panoramaWithFrameNearCoordinateRadius(
			@ByValue CGRect frame, @ByValue CLLocationCoordinate2D coordinate,
			@NUInt long radius);

	@Generated
	@Selector("pointForOrientation:")
	@ByValue
	public native CGPoint pointForOrientation(
			@ByValue GMSOrientation orientation);

	@Generated
	@Selector("setAllGesturesEnabled:")
	public native void setAllGesturesEnabled(boolean enabled);

	@Generated
	@Selector("setCamera:")
	public native void setCamera(GMSPanoramaCamera value);

	@Generated
	@Selector("setDelegate:")
	public native void setDelegate_unsafe(
			@Mapped(ObjCObjectMapper.class) GMSPanoramaViewDelegate value);

	@Generated
	public void setDelegate(
			@Mapped(ObjCObjectMapper.class) GMSPanoramaViewDelegate value) {
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
	@Selector("setNavigationGestures:")
	public native void setNavigationGestures(boolean value);

	@Generated
	@Selector("setNavigationLinksHidden:")
	public native void setNavigationLinksHidden(boolean value);

	@Generated
	@Selector("setOrientationGestures:")
	public native void setOrientationGestures(boolean value);

	@Generated
	@Selector("setPanorama:")
	public native void setPanorama(GMSPanorama value);

	@Generated
	@Selector("setStreetNamesHidden:")
	public native void setStreetNamesHidden(boolean value);

	@Generated
	@Selector("setZoomGestures:")
	public native void setZoomGestures(boolean value);

	@Generated
	@Selector("streetNamesHidden")
	public native boolean streetNamesHidden();

	@Generated
	@Selector("updateCamera:animationDuration:")
	public native void updateCameraAnimationDuration(
			GMSPanoramaCameraUpdate cameraUpdate, double duration);

	@Generated
	@Selector("zoomGestures")
	public native boolean zoomGestures();
}
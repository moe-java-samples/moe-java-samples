package com.intel.googlemaps.protocol;


import com.intel.googlemaps.GMSMarker;
import com.intel.googlemaps.GMSPanorama;
import com.intel.googlemaps.GMSPanoramaCamera;
import com.intel.googlemaps.GMSPanoramaView;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.ObjCRuntime;
import com.intel.inde.moe.natj.objc.ann.IsOptional;
import com.intel.inde.moe.natj.objc.ann.ObjCProtocolName;
import com.intel.inde.moe.natj.objc.ann.Selector;
import ios.coregraphics.struct.CGPoint;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.NSError;

@Generated
@Library("GoogleMaps")
@Runtime(ObjCRuntime.class)
@ObjCProtocolName("GMSPanoramaViewDelegate")
public interface GMSPanoramaViewDelegate {
	@Generated
	@IsOptional
	@Selector("panoramaView:didMoveCamera:")
	default void panoramaViewDidMoveCamera(GMSPanoramaView panoramaView,
			GMSPanoramaCamera camera) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaView:didMoveToPanorama:")
	default void panoramaViewDidMoveToPanorama(GMSPanoramaView view,
			GMSPanorama panorama) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaView:didMoveToPanorama:nearCoordinate:")
	default void panoramaViewDidMoveToPanoramaNearCoordinate(
			GMSPanoramaView view, GMSPanorama panorama,
			@ByValue CLLocationCoordinate2D coordinate) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaView:didTap:")
	default void panoramaViewDidTap(GMSPanoramaView panoramaView,
			@ByValue CGPoint point) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaView:didTapMarker:")
	default boolean panoramaViewDidTapMarker(GMSPanoramaView panoramaView,
			GMSMarker marker) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaView:error:onMoveNearCoordinate:")
	default void panoramaViewErrorOnMoveNearCoordinate(GMSPanoramaView view,
			NSError error, @ByValue CLLocationCoordinate2D coordinate) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaView:error:onMoveToPanoramaID:")
	default void panoramaViewErrorOnMoveToPanoramaID(GMSPanoramaView view,
			NSError error, String panoramaID) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaView:willMoveToPanoramaID:")
	default void panoramaViewWillMoveToPanoramaID(GMSPanoramaView view,
			String panoramaID) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaViewDidFinishRendering:")
	default void panoramaViewDidFinishRendering(GMSPanoramaView panoramaView) {
		throw new java.lang.UnsupportedOperationException();
	}

	@Generated
	@IsOptional
	@Selector("panoramaViewDidStartRendering:")
	default void panoramaViewDidStartRendering(GMSPanoramaView panoramaView) {
		throw new java.lang.UnsupportedOperationException();
	}
}
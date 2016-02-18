package com.intel.googlemaps.c;


import com.intel.googlemaps.GMSPath;
import com.intel.googlemaps.struct.GMSMapPoint;
import com.intel.inde.moe.natj.c.CRuntime;
import com.intel.inde.moe.natj.c.ann.CFunction;
import com.intel.inde.moe.natj.c.ann.CVariable;
import com.intel.inde.moe.natj.general.NatJ;
import com.intel.inde.moe.natj.general.ann.ByValue;
import com.intel.inde.moe.natj.general.ann.Generated;
import com.intel.inde.moe.natj.general.ann.Library;
import com.intel.inde.moe.natj.general.ann.MappedReturn;
import com.intel.inde.moe.natj.general.ann.Runtime;
import com.intel.inde.moe.natj.objc.map.ObjCStringMapper;
import ios.coregraphics.struct.CGPoint;
import ios.corelocation.struct.CLLocationCoordinate2D;
import ios.foundation.NSArray;
import ios.uikit.UIImage;

@Generated
@Library("GoogleMaps")
@Runtime(CRuntime.class)
public final class GoogleMaps {
	static {
		NatJ.register();
	}

	@Generated
	private GoogleMaps() {
	}

	@Generated
	@CFunction
	@ByValue
	public static native GMSMapPoint GMSProject(
			@ByValue CLLocationCoordinate2D coordinate);

	@Generated
	@CFunction
	@ByValue
	public static native CLLocationCoordinate2D GMSUnproject(
			@ByValue GMSMapPoint point);

	@Generated
	@CFunction
	@ByValue
	public static native GMSMapPoint GMSMapPointInterpolate(
			@ByValue GMSMapPoint a, @ByValue GMSMapPoint b, double t);

	@Generated
	@CFunction
	public static native double GMSMapPointDistance(@ByValue GMSMapPoint a,
			@ByValue GMSMapPoint b);

	@Generated
	@CFunction
	public static native boolean GMSGeometryContainsLocation(
			@ByValue CLLocationCoordinate2D point, GMSPath path,
			boolean geodesic);

	@Generated
	@CFunction
	public static native boolean GMSGeometryIsLocationOnPathTolerance(
			@ByValue CLLocationCoordinate2D point, GMSPath path,
			boolean geodesic, double tolerance);

	@Generated
	@CFunction
	public static native boolean GMSGeometryIsLocationOnPath(
			@ByValue CLLocationCoordinate2D point, GMSPath path,
			boolean geodesic);

	@Generated
	@CFunction
	public static native double GMSGeometryDistance(
			@ByValue CLLocationCoordinate2D from,
			@ByValue CLLocationCoordinate2D to);

	@Generated
	@CFunction
	public static native double GMSGeometryLength(GMSPath path);

	@Generated
	@CFunction
	public static native double GMSGeometryArea(GMSPath path);

	@Generated
	@CFunction
	public static native double GMSGeometrySignedArea(GMSPath path);

	@Generated
	@CFunction
	public static native double GMSGeometryHeading(
			@ByValue CLLocationCoordinate2D from,
			@ByValue CLLocationCoordinate2D to);

	@Generated
	@CFunction
	@ByValue
	public static native CLLocationCoordinate2D GMSGeometryOffset(
			@ByValue CLLocationCoordinate2D from, double distance,
			double heading);

	@Generated
	@CFunction
	@ByValue
	public static native CLLocationCoordinate2D GMSGeometryInterpolate(
			@ByValue CLLocationCoordinate2D from,
			@ByValue CLLocationCoordinate2D to, double fraction);

	@Generated
	@CFunction
	public static native NSArray<?> GMSStyleSpans(GMSPath path,
			NSArray<?> styles, NSArray<?> lengths, int lengthKind);

	@Generated
	@CFunction
	public static native NSArray<?> GMSStyleSpansOffset(GMSPath path,
			NSArray<?> styles, NSArray<?> lengths, int lengthKind,
			double lengthOffset);

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerCameraLatitudeKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerCameraLongitudeKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerCameraBearingKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerCameraZoomLevelKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerCameraViewingAngleKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSAccessibilityCompass();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSAccessibilityMyLocation();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlacesErrorDomain();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSAutocompleteMatchAttribute();

	@Generated
	@CVariable()
	public static native float kGMSMaxZoomLevel();

	@Generated
	@CVariable()
	public static native float kGMSMinZoomLevel();

	@Generated
	@CVariable()
	public static native double kGMSEquatorProjectedMeter();

	@Generated
	public static final double kGMSEarthRadius = 0x41584DB040000000L;

	@Generated
	@CVariable()
	@ByValue
	public static native CGPoint kGMSGroundOverlayDefaultAnchor();

	@Generated
	@CVariable()
	@ByValue
	public static native CGPoint kGMSMarkerDefaultGroundAnchor();

	@Generated
	@CVariable()
	@ByValue
	public static native CGPoint kGMSMarkerDefaultInfoWindowAnchor();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSMarkerLayerLatitude();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSMarkerLayerLongitude();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSMarkerLayerRotation();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSMarkerLayerOpacity();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerPanoramaHeadingKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerPanoramaPitchKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerPanoramaZoomKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSLayerPanoramaFOVKey();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlacePickerErrorDomain();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeAccounting();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeAdministrativeAreaLevel1();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeAdministrativeAreaLevel2();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeAdministrativeAreaLevel3();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeAirport();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeAmusementPark();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeAquarium();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeArtGallery();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeAtm();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeBakery();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeBank();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeBar();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeBeautySalon();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeBicycleStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeBookStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeBowlingAlley();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeBusStation();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCafe();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCampground();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCarDealer();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCarRental();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCarRepair();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCarWash();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCasino();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCemetery();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeChurch();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCityHall();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeClothingStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeColloquialArea();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeConvenienceStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCountry();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeCourthouse();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeDentist();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeDepartmentStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeDoctor();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeElectrician();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeElectronicsStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeEmbassy();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeEstablishment();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeFinance();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeFireStation();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeFloor();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeFlorist();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeFood();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeFuneralHome();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeFurnitureStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeGasStation();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeGeneralContractor();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeGeocode();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeGroceryOrSupermarket();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeGym();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeHairCare();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeHardwareStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeHealth();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeHinduTemple();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeHomeGoodsStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeHospital();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeInsuranceAgency();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeIntersection();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeJewelryStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeLaundry();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeLawyer();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeLibrary();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeLiquorStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeLocalGovernmentOffice();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeLocality();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeLocksmith();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeLodging();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeMealDelivery();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeMealTakeaway();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeMosque();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeMovieRental();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeMovieTheater();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeMovingCompany();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeMuseum();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeNaturalFeature();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeNeighborhood();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeNightClub();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePainter();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePark();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeParking();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePetStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePharmacy();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePhysiotherapist();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePlaceOfWorship();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePlumber();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePointOfInterest();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePolice();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePolitical();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePostBox();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePostOffice();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePostalCode();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePostalCodePrefix();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePostalTown();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypePremise();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeRealEstateAgency();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeRestaurant();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeRoofingContractor();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeRoom();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeRoute();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeRvPark();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSchool();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeShoeStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeShoppingMall();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSpa();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeStadium();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeStorage();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeStore();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeStreetAddress();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSublocality();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSublocalityLevel1();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSublocalityLevel2();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSublocalityLevel3();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSublocalityLevel4();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSublocalityLevel5();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSubpremise();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSubwayStation();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeSynagogue();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeTaxiStand();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeTrainStation();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeTransitStation();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeTravelAgency();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeUniversity();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeVeterinaryCare();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String kGMSPlaceTypeZoo();

	@Generated
	@CVariable()
	public static native UIImage kGMSTileLayerNoTile();
}
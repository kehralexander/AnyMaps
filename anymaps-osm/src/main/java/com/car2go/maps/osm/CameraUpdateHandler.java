/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.osm;

import com.car2go.maps.AnyMap;
import com.car2go.maps.CameraUpdate;
import com.car2go.maps.model.LatLng;

import org.osmdroid.api.IMapController;
import org.osmdroid.views.MapView;

import static com.car2go.maps.osm.util.OsmUtils.toGeoPoint;

/**
 * Handles incoming {@link CameraUpdate} events
 */
class CameraUpdateHandler {

	private final org.osmdroid.views.MapView map;

	public CameraUpdateHandler(MapView map) {
		this.map = map;
	}

	/**
	 * @see AnyMap#moveCamera(CameraUpdate)
	 */
	public void moveCamera(CameraUpdate cameraUpdate) {
		final OsmCameraUpdate osmCameraUpdate = (OsmCameraUpdate) cameraUpdate;

		final IMapController controller = map.getController();

		if (osmCameraUpdate.bounds != null) {
			final LatLng center = osmCameraUpdate.bounds.getCenter();

			controller.setZoom(map.getMaxZoomLevel());

			controller.zoomToSpan(
					osmCameraUpdate.bounds.northeast.latitude - osmCameraUpdate.bounds.southwest.latitude,
					osmCameraUpdate.bounds.northeast.longitude - osmCameraUpdate.bounds.southwest.longitude
			);

			controller.setCenter(
					toGeoPoint(center)
			);

			return;
		}

		if (osmCameraUpdate.zoom != null) {
			controller.setZoom(osmCameraUpdate.zoom);
		}

		if (osmCameraUpdate.center != null) {
			controller.setCenter(
					toGeoPoint(osmCameraUpdate.center)
			);
		}
	}

	/**
	 * @see AnyMap#animateCamera(CameraUpdate)
	 */
	public void animateCamera(CameraUpdate cameraUpdate) {
		final OsmCameraUpdate osmCameraUpdate = (OsmCameraUpdate) cameraUpdate;
		final IMapController controller = map.getController();

		if (osmCameraUpdate.bounds != null) {
			// animation to bounds not supported by OSM
			moveCamera(cameraUpdate);
		}

		if (osmCameraUpdate.zoom != null && osmCameraUpdate.center != null) {
			controller.animateTo(toGeoPoint(osmCameraUpdate.center),
					osmCameraUpdate.zoom.doubleValue(), null);
		} else if (osmCameraUpdate.zoom != null) {
			controller.zoomTo(osmCameraUpdate.zoom);
		} else if (osmCameraUpdate.center != null) {
			controller.animateTo(toGeoPoint(osmCameraUpdate.center));
		}
	}

	/**
	 * @see AnyMap#animateCamera(CameraUpdate, AnyMap.CancelableCallback)
	 */
	public void animateCamera(CameraUpdate cameraUpdate, AnyMap.CancelableCallback callback) {
		moveCamera(cameraUpdate);

		callback.onFinish();
	}

	/**
	 * @see AnyMap#animateCamera(CameraUpdate, int, AnyMap.CancelableCallback)
	 */
	public void animateCamera(CameraUpdate cameraUpdate, int duration, AnyMap.CancelableCallback callback) {
		moveCamera(cameraUpdate);

		callback.onFinish();
	}

}

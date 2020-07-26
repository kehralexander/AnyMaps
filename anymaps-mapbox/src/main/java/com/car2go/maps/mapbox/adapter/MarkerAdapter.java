/*
 * Copyright (c) 2016 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.mapbox.adapter;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.DisplayMetrics;

import com.car2go.maps.model.BitmapDescriptor;
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.Marker;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;

/**
 * Adapts Mapbox Marker to AnyMap Marker
 */
public class MarkerAdapter implements Marker {

	private final Symbol marker;
	private final SymbolManager manager;
	private final AnyMapAdapter anyMapAdapter;

	public MarkerAdapter(Symbol marker, SymbolManager manager, AnyMapAdapter anyMapAdapter) {
		this.marker = marker;
		this.manager = manager;
		this.anyMapAdapter = anyMapAdapter;
	}

	@Override
	public void setIcon(BitmapDescriptor icon) {
		BitmapDescriptorAdapter adapter = (BitmapDescriptorAdapter) icon;
		marker.setIconImage(adapter.id);
		update();
	}

	private void update() {
		if (manager.getAnnotations().containsValue(marker)) {
			manager.update(marker);
		}
	}

	@Override
	public LatLng getPosition() {
		return anyMapAdapter.map(marker.getLatLng());
	}

	@Override
	public void showInfoWindow() {
	}

	@Override
	public void setRotation(float rotation) {
		marker.setIconRotate(rotation);
		update();
	}

	@Override
	public void setVisible(boolean visible) {
		marker.setIconOpacity(visible ? 1f : 0f);
		update();
	}

	@Override
	public void remove() {
		manager.delete(marker);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MarkerAdapter)) return false;

		MarkerAdapter that = (MarkerAdapter) o;

		return marker.getId() == that.marker.getId();
	}

	@Override
	public int hashCode() {
		return Long.valueOf(marker.getId()).hashCode();
	}

	@Override
	public void setZ(int z) {
		marker.setSymbolSortKey((float) z);
		update();
	}

	@Override
	public void setAnchor(float u, float v) {
		DisplayMetrics dm = anyMapAdapter.context.getResources().getDisplayMetrics();
		Bitmap icon = anyMapAdapter.bitmapDescriptorFactory.images.get(marker.getIconImage());
		if (icon != null) {
			marker.setIconOffset(new PointF(
					-u * icon.getWidth() / dm.density,
					-v * icon.getHeight() / dm.density));
			update();
		}
	}
}

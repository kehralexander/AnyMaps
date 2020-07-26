/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.mapbox.adapter.factory.mapbox;

import android.util.DisplayMetrics;

import com.car2go.maps.mapbox.adapter.AnyMapAdapter;
import com.car2go.maps.mapbox.adapter.BitmapDescriptorAdapter;
import com.car2go.maps.mapbox.adapter.factory.Mapper;
import com.car2go.maps.model.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;

/**
 * Maps AnyMap MarkerOptions to Google MarkerOptions
 */
public class MarkerOptionsMapper implements Mapper<MarkerOptions, SymbolOptions> {

	private final AnyMapAdapter anyMapAdapter;

	public MarkerOptionsMapper(AnyMapAdapter anyMapAdapter) {
		this.anyMapAdapter = anyMapAdapter;
	}

	@Override
	public SymbolOptions map(MarkerOptions input) {
		LatLng mapboxLatLng = anyMapAdapter.map(input.getPosition());

		DisplayMetrics dm = anyMapAdapter.context.getResources().getDisplayMetrics();
		BitmapDescriptorAdapter icon = (BitmapDescriptorAdapter) input.getIcon();
		Float[] offset = {
				-input.getAnchorU() * icon.bitmap.getWidth() / dm.density,
				-input.getAnchorV() * icon.bitmap.getHeight() / dm.density
		};
		return new SymbolOptions()
				.withIconOpacity(input.isVisible() ? input.getAlpha() : 0f)
				.withLatLng(mapboxLatLng)
				.withIconAnchor("top-left")
				.withIconOffset(offset)
				.withIconImage(icon.id)
				.withSymbolSortKey((float) input.getZ());
	}

}

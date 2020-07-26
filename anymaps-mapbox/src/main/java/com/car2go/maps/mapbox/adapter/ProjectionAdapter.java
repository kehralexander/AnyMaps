/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.mapbox.adapter;

import com.car2go.maps.Projection;
import com.car2go.maps.model.VisibleRegion;

/**
 * Adapts Google Map projection to AnyMap projection
 */
public class ProjectionAdapter implements Projection {

	private final com.mapbox.mapboxsdk.maps.Projection projection;
	private final AnyMapAdapter anyMapAdapter;

	public ProjectionAdapter(com.mapbox.mapboxsdk.maps.Projection projection, AnyMapAdapter anyMapAdapter) {
		this.projection = projection;
		this.anyMapAdapter = anyMapAdapter;
	}

	@Override
	public VisibleRegion getVisibleRegion() {
		return anyMapAdapter.map(projection.getVisibleRegion());
	}

}

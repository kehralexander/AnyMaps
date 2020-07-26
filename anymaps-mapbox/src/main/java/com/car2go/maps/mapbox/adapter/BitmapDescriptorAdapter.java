/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.mapbox.adapter;

import android.graphics.Bitmap;

import com.car2go.maps.model.BitmapDescriptor;

/**
 * Adapts Mapbox BitmapDescriptor to AnyMap BitmapDescriptor
 */
public class BitmapDescriptorAdapter implements BitmapDescriptor {

	public final String id;
	public final Bitmap bitmap;

	public BitmapDescriptorAdapter(String id, Bitmap bitmap) {
		this.id = id;
		this.bitmap = bitmap;
	}

}

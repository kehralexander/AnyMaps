/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.mapbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.car2go.maps.mapbox.adapter.BitmapDescriptorAdapter;
import com.car2go.maps.model.BitmapDescriptor;
import com.mapbox.mapboxsdk.exceptions.TooManyIconsException;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.utils.BitmapUtils;

import java.util.HashMap;

import androidx.annotation.DrawableRes;

/**
 * Creates instances of {@link com.car2go.maps.model.BitmapDescriptor}
 */
public class BitmapDescriptorFactory implements com.car2go.maps.BitmapDescriptorFactory {

	private final Context context;
	private MapboxMap map;
	private static final String ICON_ID_PREFIX = "com.car2go.maps.mapbox.icon_";
	private int nextId = 0;
	public final HashMap<String, Bitmap> images;

	public BitmapDescriptorFactory(Context context, MapboxMap map) {
		this.context = context;
		this.map = map;
		this.images = new HashMap<>();
	}

	@Override
	public BitmapDescriptor fromBitmap(Bitmap bitmap) {
		if (nextId < 0) {
			throw new TooManyIconsException();
		}
		String id = ICON_ID_PREFIX + ++nextId;
		images.put(id, bitmap);
		if (map.getStyle() != null) {
			map.getStyle().addImage(id, bitmap);
		}
		return new BitmapDescriptorAdapter(id, bitmap);
	}

	@Override
	public BitmapDescriptor fromResource(@DrawableRes int resourceId) {
		Drawable drawable = BitmapUtils.getDrawableFromRes(context, resourceId);
		if (drawable instanceof BitmapDrawable) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
			return fromBitmap(bitmapDrawable.getBitmap());
		} else {
			throw new IllegalArgumentException("Failed to decode image. The resource provided must be a Bitmap.");
		}
	}

}

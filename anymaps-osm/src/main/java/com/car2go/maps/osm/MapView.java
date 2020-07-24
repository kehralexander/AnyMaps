/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.osm;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.car2go.maps.MapContainerView;
import com.car2go.maps.OnMapReadyCallback;

import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.CopyrightOverlay;

/**
 * @see MapContainerView
 */
public class MapView extends MapContainerView {

	private OsmMap anyMap;

	public MapView(Context context) {
		super(context);

		initView(context, null);
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView(context, attrs);
	}

	private void initView(Context context, AttributeSet attrs) {
		org.osmdroid.views.MapView mapView = new org.osmdroid.views.MapView(context);

		addView(mapView);

		XYTileSource tileSource = getTileSource();
		mapView.setTileSource(tileSource);
		mapView.setMultiTouchControls(true);
		mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
		mapView.setTilesScaledToDpi(true);

		CopyrightOverlay co = new CopyrightOverlay(context);
		co.setTextColor(getSecondaryTextColor());
		mapView.getOverlays().add(co);

		anyMap = new OsmMap(mapView);

		setClipToPadding(false);

		applyAttributes(context, attrs);
	}

	private int getSecondaryTextColor() {
		TypedValue typedValue = new TypedValue();
		TypedArray a = getContext().obtainStyledAttributes(typedValue.data, new int[] { android.R.attr.textColorSecondary });
		int color = a.getColor(0, 0);
		a.recycle();
		return color;
	}

	private XYTileSource getTileSource() {
		int density = (int) Math.ceil(getResources().getDisplayMetrics().density);
		density = Math.min(density, 8); // maximum supported size is 8x

		String suffix = density > 1 ? "@" + density + "x.png" : ".png";
		int tileSize = density * 256;

		return new XYTileSource("Carto", 1, 19, tileSize, suffix, new String[]{
				"https://a.basemaps.cartocdn.com/rastertiles/voyager_labels_under/",
				"https://b.basemaps.cartocdn.com/rastertiles/voyager_labels_under/",
				"https://c.basemaps.cartocdn.com/rastertiles/voyager_labels_under/",
		}, "Map data © OpenStreetMap | Tiles © Carto");
	}

	private void applyAttributes(Context context, AttributeSet attrs) {
		if (attrs == null) {
			return;
		}

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MapView);
		try {
			boolean liteMode = typedArray.getBoolean(R.styleable.MapView_anyMapLiteMode, false);

			anyMap.getUiSettings().setAllGesturesEnabled(!liteMode);
		} finally {
			typedArray.recycle();
		}
	}

	@Override
	public void getMapAsync(final OnMapReadyCallback callback) {
		post(new Runnable() {
			@Override
			public void run() {
				callback.onMapReady(anyMap);
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Do nothing
	}

	@Override
	public void onResume() {
		// Do nothing
	}

	@Override
	public void onPause() {
		// Do nothing
	}

	@Override
	public void onDestroy() {
		// Do nothing
	}

	@Override
	public void onLowMemory() {
		// Do nothing
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Do nothing
	}

}

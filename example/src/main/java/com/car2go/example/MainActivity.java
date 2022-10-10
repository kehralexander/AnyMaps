/*
 * Copyright (c) 2017 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.example;

import android.os.Bundle;

import com.car2go.maps.AnyMap;
import com.car2go.maps.MapFragment;
import com.car2go.maps.OnMapReadyCallback;
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.MarkerOptions;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private MapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mapFragment = new MapFragment();
		mapFragment.setPriority(new String[]{MapFragment.OSM});
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.map, mapFragment)
				.commit();

		mapFragment.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(AnyMap map) {
				map.moveCamera(
						map.getCameraUpdateFactory()
								.newLatLngZoom(
										new LatLng(53.5443465, 9.9289326),
										17f
								)
				);

				map.addMarker(
						new MarkerOptions()
								.position(new LatLng(53.5443465, 9.9289326))
								.anchor(0.5f, 0.5f)
								.icon(map.getBitmapDescriptorFactory().fromResource(R.drawable.marker))
				);

				map.setOrientation(45);
			}
		});
	}

}

/*
 * Copyright (c) 2020 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MapFragment extends Fragment {
	private MapContainerView map;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return map;
	}

	public void getMapAsync(@NonNull OnMapReadyCallback callback) {
		map.getMapAsync(callback);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		map = createMap();

		super.onCreate(savedInstanceState);
		map.onCreate(savedInstanceState);
	}

	private MapContainerView createMap() {
		String[] classes = new String[]{
				"com.car2go.maps.google.MapView",
				"com.car2go.maps.baidu.MapView",
				"com.car2go.maps.mapbox.MapView",
				"com.car2go.maps.osm.MapView"
		};
		for (String name : classes) {
			Class<MapContainerView> clazz = getMapClass(name);
			if (clazz != null) {
				try {
					return clazz.getConstructor(Context.class).newInstance(getContext());
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (java.lang.InstantiationException e) {
					throw new RuntimeException(e);
				} catch (NoSuchMethodException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}

	private Class<MapContainerView> getMapClass(String name) {
		try {
			return (Class<MapContainerView>) Class.forName(name);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		map.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		map.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		map.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		map.onLowMemory();
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
		map.onSaveInstanceState(outState);
	}
}

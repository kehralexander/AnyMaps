/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.mapbox;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.car2go.maps.AnyMap;
import com.mapbox.mapboxsdk.Mapbox;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.of;

/**
 * Initializer for Mapbox maps.
 */
public class MapsConfiguration implements com.car2go.maps.MapsConfiguration {

	private static final MapsConfiguration instance = new MapsConfiguration();

	private MapsConfiguration() {
	}

	public static MapsConfiguration getInstance() {
		return instance;
	}

	@Override
	public void initialize(Context context) {
		try {
			ApplicationInfo app = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			Bundle metadata = app.metaData;

			Mapbox.getInstance(context, metadata.getString("com.mapbox.ACCESS_TOKEN"));
			Mapbox.getTelemetry().setUserTelemetryRequestState(false);
			Mapbox.getTelemetry().setDebugLoggingEnabled(false);
		} catch (PackageManager.NameNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Set<AnyMap.Feature> getSupportedFeatures() {
		return unmodifiableSet(of(AnyMap.Feature.MAP_TYPES, AnyMap.Feature.TRAFFIC_LAYER, AnyMap.Feature.REVEALABLE));
	}


}

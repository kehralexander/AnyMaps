/*
 * Copyright (c) 2020 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.mapbox.android.telemetry;

import android.content.Context;

@SuppressWarnings("unused")
public class MapboxTelemetry {
	public MapboxTelemetry(Context appContext, String accessToken, String mapboxEventsUserAgent) {

	}

	public boolean enable() {
		return false;
	}

	public boolean push(Event event) {
		return true;
	}

	public boolean disable() {
		return false;
	}

	public void updateDebugLoggingEnabled(boolean debugLoggingEnabled) {
	}

	public boolean updateSessionIdRotationInterval(SessionInterval sessionInterval) {
		return true;
	}
}

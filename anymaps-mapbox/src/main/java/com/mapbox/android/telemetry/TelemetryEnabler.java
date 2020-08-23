/*
 * Copyright (c) 2020 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.mapbox.android.telemetry;

public class TelemetryEnabler {
	public static TelemetryEnabler.State retrieveTelemetryStateFromPreferences() {
		return State.DISABLED;
	}

	public static void updateTelemetryState(State enabled) {

	}

	public enum State {ENABLED, DISABLED}
}

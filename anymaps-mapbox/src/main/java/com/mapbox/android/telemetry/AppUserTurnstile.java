/*
 * Copyright (c) 2020 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.mapbox.android.telemetry;

import android.os.Parcel;
import android.os.Parcelable;

@SuppressWarnings("unused")
public class AppUserTurnstile extends Event {
	public AppUserTurnstile(String mapboxSdkIdentifier, String mapboxSdkVersion) {

	}

	public void setSkuId(String skuIdMapsMaus) {

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {

	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<AppUserTurnstile> CREATOR = new Parcelable.Creator<AppUserTurnstile>() {
		@Override
		public AppUserTurnstile createFromParcel(Parcel in) {
			return new AppUserTurnstile(null, null);
		}

		@Override
		public AppUserTurnstile[] newArray(int size) {
			return new AppUserTurnstile[size];
		}
	};
}

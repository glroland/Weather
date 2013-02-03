package com.glroland.weather.business;

import java.math.BigInteger;

import com.glroland.weather.integration.NWSForecastServiceAdapter;

public class ForecastService {

	public String getTempForZip(String zip)
	{
		NWSForecastServiceAdapter adapter = new NWSForecastServiceAdapter();
		String latlong = adapter.latLonListZipCode(new String [] { zip });
		BigInteger temp = adapter.NDFDgenLatLonList(latlong);
		return temp.toString();
	}
}

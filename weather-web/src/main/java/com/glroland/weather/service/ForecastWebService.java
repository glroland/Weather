package com.glroland.weather.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.glroland.weather.business.ForecastService;

@WebService
public class ForecastWebService {

	@WebMethod
	public String getTempForZip(String zipCode)
	{
		ForecastService service = new ForecastService();
		return service.getTempForZip(zipCode);
	}
}

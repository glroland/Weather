package com.glroland.weather.integration;

import gov.weather.graphical.xml.DWML.DataType;
import gov.weather.graphical.xml.DWML.Dwml;
import gov.weather.graphical.xml.DWML.ParametersType;
import gov.weather.graphical.xml.DWML.ParametersType.Temperature;
import gov.weather.graphical.xml.DWML.TempValType;
import gov.weather.graphical.xml.DWMLgen.schema.DWML_xsd.ProductType;
import gov.weather.graphical.xml.DWMLgen.schema.DWML_xsd.UnitType;
import gov.weather.graphical.xml.DWMLgen.schema.DWML_xsd.WeatherParametersType;
import gov.weather.graphical.xml.DWMLgen.wsdl.ndfdXML_wsdl.NdfdXMLPortTypeProxy;

import java.io.StringReader;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NWSForecastServiceAdapter {
	
	private static final Log log = LogFactory
			.getLog(NWSForecastServiceAdapter.class);

	private NdfdXMLPortTypeProxy getProxy() {
		NdfdXMLPortTypeProxy proxy = new NdfdXMLPortTypeProxy();
		// proxy.setEndpoint("");

		return proxy;
	}

	public String latLonListZipCode(String[] zipCodes) {
		// validate input parameters
		if ((zipCodes == null) || (zipCodes.length == 0)) {
			String msg = "Input zipCodes parameter is empty or null!";
			log.error(msg);
			throw new RuntimeException(msg);
		}

		// prepare zip code list argument
		StringBuffer zipCodeList = new StringBuffer();
		for (int i = 0; i < zipCodes.length; i++) {
			if (i > 0)
				zipCodeList.append(",");

			String zipCode = zipCodes[i];
			zipCodeList.append(zipCode);
		}

		// call service
		String dwmlXml = null;
		try {
			NdfdXMLPortTypeProxy proxy = getProxy();
			dwmlXml = proxy.latLonListZipCode(zipCodeList.toString());
		} catch (RemoteException e) {
			String msg = "Caught exception while invoking remote service operation!  "
					+ zipCodeList.toString();
			log.error(msg, e);
			throw new RuntimeException(msg, e);
		}
		
		// parse response
		Dwml dwml = parseDWMLResponse(dwmlXml);
		return dwml.getLatLonList();
	}

	public BigInteger NDFDgenLatLonList(String latlong) {
		// validate arguments
		if ((latlong == null) || (latlong.length() == 0)) {
			String msg = "latlong argument passed to method is invalid!";
			log.error(msg);
			throw new RuntimeException(msg);
		}

		Calendar startTime = Calendar.getInstance();

		WeatherParametersType weatherParameters = new WeatherParametersType();
		weatherParameters.setTemp(true);

		// call service
		String dwmlXml = null;
		try {
			NdfdXMLPortTypeProxy proxy = getProxy();
			dwmlXml = proxy.NDFDgenLatLonList(latlong, ProductType.value1,
					startTime, null, UnitType.e, weatherParameters);
		} catch (RemoteException e) {
			String msg = "Caught exception while invoking remote service operation!  "
					+ latlong.toString();
			log.error(msg, e);
			throw new RuntimeException(msg, e);
		}
		
		// parse response
		Dwml dwml = parseDWMLResponse(dwmlXml);
		List<DataType> datas = dwml.getData();
		if ((datas == null) || (datas.size() == 0))
		{
			String msg = "Response from service contained no data blocks!";
			log.error(msg);
			throw new RuntimeException(msg);
		}
		DataType data = datas.get(0);
		if (data == null)
		{
			String msg = "First data block from service was null!";
			log.error(msg);
			throw new RuntimeException(msg);
		}
		List<ParametersType> parameters = data.getParameters();
		ParametersType parameter = parameters.get(0);
		List<Temperature> temperatures = parameter.getTemperature();
		Temperature temperature = temperatures.get(0);
		List<TempValType> values = temperature.getValue();
		TempValType value = values.get(values.size() - 1);
		return value.getValue();
	}

	private Dwml parseDWMLResponse(String responseXml) {
		// validate arguments
		if ((responseXml == null) || (responseXml.length() == 0))
		{
			String msg = "Illegal argument passed into DWML response parser method.  Its null or empty!";
			log.error(msg);
			throw new RuntimeException(msg);
		}
		
		log.debug("DWML Response:\n" + responseXml);
		
		Dwml response = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Dwml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(responseXml);
			try
			{
				response = (Dwml) jaxbUnmarshaller.unmarshal(reader);
			}
			finally
			{
				reader.close();
			}
		} catch (JAXBException e) {
			String msg = "Caught JAX-B exception while parsing DWML response!";
			log.error(msg, e);
			throw new RuntimeException(msg, e);
		}

		if (response == null)
		{
			String msg = "Null object returned from JAX-B parser for DWML response!";
			log.error(msg);
			throw new RuntimeException(msg);
		}
		
		return response;
	}
}

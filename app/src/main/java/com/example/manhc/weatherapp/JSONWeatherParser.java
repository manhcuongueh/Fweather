/**
 * This is a tutorial source code 
 * provided "as is" and without warranties.
 *
 * For any question please visit the web site
 * http://www.survivingwithandroid.com
 *
 * or write an email to
 * survivingwithandroid@gmail.com
 *
 */
package com.example.manhc.weatherapp;

import com.example.manhc.weatherapp.model.Weather;
import com.example.manhc.weatherapp.model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Copyright (C) 2013 Surviving with Android (http://www.survivingwithandroid.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class JSONWeatherParser {
	static String image="";

	public static Weather getWeather(String data) throws JSONException {
		Weather weather = new Weather();

		JSONObject jObj = new JSONObject(data);;
		JSONArray jArr = jObj.getJSONArray("weather");
		JSONObject JSONWeather = jArr.getJSONObject(0);
		weather.setStatus(JSONWeather.getString("main"));
		image= JSONWeather.getString("icon");
		JSONObject mainObj = jObj.getJSONObject("main");
		weather.setTem(mainObj.getDouble("temp"));
		weather.setTemMax(mainObj.getDouble("temp_max"));
		weather.setTemMin(mainObj.getDouble("temp_min"));
		weather.setPressure(mainObj.getInt("pressure"));
		weather.setHumidity(mainObj.getInt("humidity"));
		JSONObject windObj = jObj.getJSONObject("wind");
		weather.setWind(windObj.getDouble("speed"));
		return weather;

	}
}

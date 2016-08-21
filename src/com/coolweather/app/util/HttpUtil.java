package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// HttpURLConnection connection = null;
				HttpClient client = new DefaultHttpClient();
				try {
					// URL url = new URL(address);
					// connection = (HttpURLConnection) url.openConnection();
					// connection.setRequestMethod("GET");
					// connection.setConnectTimeout(8000);
					// connection.setReadTimeout(8000);
					// connection.setDoInput(true);
					// connection.setDoOutput(true);
					// InputStream in = connection.getInputStream();
					// BufferedReader reader = new BufferedReader(
					// new InputStreamReader(in));
					// StringBuilder response = new StringBuilder();
					// String line;
					// while ((line = reader.readLine()) != null) {
					// response.append(line);
					// }

					HttpGet httpGet = new HttpGet(address);
					HttpResponse httpResponse = client.execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						HttpEntity httpEntity = httpResponse.getEntity();
						String response = EntityUtils.toString(httpEntity,
								"UTF-8");

						if (listener != null) {
							// 回调Finish方法
							listener.onFinish(response.toString());
						}
					}
				} catch (Exception e) {
					if (listener != null) {
						listener.onError(e);
					}
				}
				// finally {
				// if (connection != null) {
				// connection.disconnect();
				// }
				// }
			}
		}).start();
	}
}

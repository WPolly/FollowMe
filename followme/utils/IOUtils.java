package com.xiaoshan.polly.followme.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {
	/**
	 * @param is 输入流
	 * @return inputStream到String
	 * @throws IOException
	 */
	public static String convertStreamToString(InputStream is) throws IOException {
		try {
			if (is != null) {
				StringBuilder sb = new StringBuilder();
				String line;
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
					// BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					while ((line = reader.readLine()) != null) {
						// sb.append(line);
						sb.append(line).append("\n");
					}
				} finally {
					is.close();
				}
				return sb.toString();
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
}

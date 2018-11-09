package io.github.genelist.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.logging.Logger;

public abstract class Http {
    private static final Logger LOG = Logger.getLogger("io.github.genelist.util.Http");

    public static String getRequest(String url) {
        return getRequest(url, null, null);
    }

    public static String getRequest(String url, String headerName, String headerValue) {
        StringBuffer sb = new StringBuffer("");
        BufferedReader br = null;
        HttpURLConnection connection = null;
        try {
            // Open HTTP connection
            connection = (HttpURLConnection) new URL(url).openConnection();

            // Set header
            if (headerName != null && headerValue != null)
                connection.setRequestProperty(headerName, headerValue);

            // Evaluate response success
            String responseMsg = connection.getResponseMessage();
            LOG.info(responseMsg);
            if (connection.getResponseCode() >= 300)
                throw new Exception(responseMsg);

            // Parse response
            InputStream in = new BufferedInputStream(connection.getInputStream());
            br = new BufferedReader(new InputStreamReader(in));
            String nextLine = br.readLine();
            while (nextLine != null) {
                sb.append(nextLine);
                nextLine = br.readLine();
            }
        } catch (Exception e) {
            LOG.warning(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.warning(e.getMessage());
                }
            }
            if (connection != null)
                connection.disconnect();
        }
        return sb.toString();
    }
}

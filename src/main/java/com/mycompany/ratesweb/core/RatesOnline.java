
package com.mycompany.ratesweb.core;

import com.mycompany.ratesweb.persistence.FilesDB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;


/***
 * Implement Rates from online API
 * @author zdene
 */
public class RatesOnline implements RatesInterface {

    private static final String GET_URL = "https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e";

    /***
     * Returns JSON data of rates from API
     * @return JSON data or empty JSON array
     */
    @Override
    public String getRates() {
        String result = getData();
        FilesDB.saveToFile(result);
        return result;
    }
    
    /***
     * Returns JSON data rate of one currency from API
     * @param key identification of currency
     * @return JSON data or empty JSON array
     */
    @Override
    public String getRate(String key)
    {
        String data = getData();
        String temp;
        JSONArray arr = new JSONArray(data);
        for (int i = 0; i < arr.length(); i++)
        {
            temp = arr.getJSONObject(i).getString("shortName");
            if(temp.equals(key))
            {
                return arr.getJSONObject(i).toString();
            }
        }
        return "[]";
    }
    
    
    /***
     * I hate this procedure, but we need HTTPS ignore :(
     * @throws Exception 
     */
    private void setHttps() throws Exception {
        /* Start of Fix */
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }

        }};

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        /* End of the fix*/

    }
    
    /***
     * Get data from online API
     * @return JSON data or empty string
     */
    private String getData()
    {
        String result = "[]";
        try {
            setHttps();
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                result = response.toString();
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    

}

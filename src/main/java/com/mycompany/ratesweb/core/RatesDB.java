
package com.mycompany.ratesweb.core;

import com.mycompany.ratesweb.persistence.FilesDB;
import org.json.JSONArray;

/***
 * Implement Rates from file
 * @author zdene
 */
public class RatesDB implements RatesInterface{
    
    /***
     * Returns JSON data of rates from file
     * @return JSON data or empty JSON array
     */
    @Override
    public String getRates()
    {
        String result = FilesDB.readFromFile();
        if (result.isEmpty())
        {
            return "[]";
        }
        else
        {
             return result;
        }
    }
    
    /***
     * Returns JSON data rate of one currency from file
     * @param key identification of currency
     * @return JSON data or empty JSON array
     */
    @Override
    public String getRate(String key)
    {
        String data = FilesDB.readFromFile();
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
}

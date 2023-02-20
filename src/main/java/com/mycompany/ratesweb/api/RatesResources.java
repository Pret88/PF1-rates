
package com.mycompany.ratesweb.api;


import com.mycompany.ratesweb.core.RatesDB;
import com.mycompany.ratesweb.core.RatesInterface;
import com.mycompany.ratesweb.core.RatesOnline;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;;

/**
 * Simple REST api for Rates
 * @author zdene
 */
@Path("/rates")
public class RatesResources {
    
    /***
     * Return JSON data of rates from DB or from Online API
     * @param usedb if true then get data from DB else get data from online API
     * @return JSON data
     */
    @GET
    @Path("/")
    public Response getRates(@QueryParam("usedb") @DefaultValue("false") Boolean usedb) {
        
        RatesInterface rate; 
        if (usedb)
        {
            rate = new RatesDB();
        }
        else
        {
            rate = new RatesOnline();
        }
        
        return Response.ok(rate.getRates(), MediaType.TEXT_PLAIN).build();
    }
    
    /***
     * Return JSON data of one currency from DB or from Online API
     * @param usedb if true then get data from DB else get data from online API
     * @param key identification of currency
     * @return JSON data
     */
    @GET
    @Path("{name}")
    public Response getRate(@QueryParam("usedb") @DefaultValue("false") Boolean usedb, @PathParam("name") @DefaultValue("") String key) {
        
        RatesInterface rate; 
        if (usedb)
        {
            rate = new RatesDB();
        }
        else
        {
            rate = new RatesOnline();
        }
        
        return Response.ok("["+rate.getRate(key)+"]", MediaType.TEXT_PLAIN).build();
    }
}

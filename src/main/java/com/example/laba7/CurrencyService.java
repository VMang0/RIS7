package com.example.laba7;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.File;
import java.net.URL;

@Path("/currency")
public class CurrencyService {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getCurrencyRates(){
        ClassLoader classLoader = CurrencyService.class.getClassLoader();
        URL resource = classLoader.getResource("currency.xml");
        assert resource != null;
        File file = new File(resource.getFile());
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            Response.ResponseBuilder response = Response.ok(file);
            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

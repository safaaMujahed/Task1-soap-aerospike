//To create Book service endpoint, we just need to annotate a POJO with Spring WS annotation to take care of SOAP request.
package com.example.producingwebservice.endpoints;

import com.demo.GetCountryRequest;
import com.demo.GetCountryResponse;
import com.example.producingwebservice.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.logging.Logger;

@Endpoint
public class CountryEndpoint {
//    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private static final String NAMESPACE_URI = "http://demo.com";
//    private static final Logger LOGGER = Logger.getLogger(CountryEndpoint.class.getName());
    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
//        LOGGER.info("try logger jjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
//
//@Endpoint: This annotation is used to register the class with Spring WS for processing incoming SOAP request.
//@PayloadRoot: This annotation helps Spring WS to pick handler method based on message’s namespace and localPart.
//@ResponsePayload: This annotation indicates that incoming message will be mapped to method’s request parameter.
//@ResponsePayload: This annotation is used to Spring WS map the return value to the response payload.
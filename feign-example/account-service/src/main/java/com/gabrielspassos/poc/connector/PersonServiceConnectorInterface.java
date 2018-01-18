package com.gabrielspassos.poc.connector;

import com.gabrielspassos.poc.model.PersonModel;
import feign.Param;
import feign.RequestLine;

public interface PersonServiceConnectorInterface {

    @RequestLine("GET /{id}")
    PersonModel findPersonById(@Param("id") int id);
}

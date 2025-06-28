package com.petstoretests.requests;

import com.petstoretests.utility.APIUtilities;
import io.restassured.response.Response;

import java.util.Map;

public class GetPetByIdRequest {
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    public static Response performGet(Integer id) {
        return APIUtilities.getRequest(URL + id.toString());
    }
}

package com.petstoretests.requests;

import com.petstoretests.utility.APIUtilities;
import io.restassured.response.Response;

public class DeletePetByIdRequest {
    private static final String URL = "https://petstore.swagger.io/v2/pet/";

    public static Response performDelete(Integer id) {
        return APIUtilities.deleteRequest(URL + id.toString());
    }
}

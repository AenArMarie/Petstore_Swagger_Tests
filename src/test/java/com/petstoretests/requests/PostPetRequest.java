package com.petstoretests.requests;

import com.petstoretests.jsondata.models.PetModel;
import com.petstoretests.utility.APIUtilities;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostPetRequest {
    private static final String URL = "https://petstore.swagger.io/v2/pet";

    public static Response performPost(PetModel pet) {
        return APIUtilities.postRequestWithBody(URL, ContentType.JSON, pet);
    }
}

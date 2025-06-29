package com.petstoretests.requests;

import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.UrlData;
import com.petstoretests.jsondata.models.PetModel;
import com.petstoretests.utility.APIUtilities;
import com.utility.files.FilesReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostPetRequest {
    private static final String URL = FilesReader.readJson(PathConstants.URLS_PATH, UrlData.class).getPetStoreUrl();

    public static Response performPost(PetModel pet) {
        return APIUtilities.postRequestWithBody(URL, ContentType.JSON, pet);
    }
}

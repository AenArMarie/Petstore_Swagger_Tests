package com.petstoretests.requests;

import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.UrlData;
import com.petstoretests.jsondata.models.PetModel;
import com.petstoretests.utility.APIUtilities;
import com.utility.files.FilesReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutPetUpdatesRequest {
    private static final String URL = FilesReader.readJson(PathConstants.URLS_PATH, UrlData.class).getPetStoreUrl();

    public static Response performPut(PetModel pet) {
        return APIUtilities.putRequestWithBody(URL, ContentType.JSON, pet);
    }
}

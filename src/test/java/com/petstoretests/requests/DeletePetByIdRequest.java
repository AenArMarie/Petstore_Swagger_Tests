package com.petstoretests.requests;

import com.petstoretests.constants.PathConstants;
import com.petstoretests.constants.Symbols;
import com.petstoretests.jsondata.UrlData;
import com.petstoretests.utility.APIUtilities;
import com.utility.files.FilesReader;
import io.restassured.response.Response;

public class DeletePetByIdRequest {
    private static final String URL = FilesReader.readJson(PathConstants.URLS_PATH, UrlData.class).getPetStoreUrl() + Symbols.SLASH;

    public static Response performDelete(Long id) {
        return APIUtilities.deleteRequest(URL + id.toString());
    }
}

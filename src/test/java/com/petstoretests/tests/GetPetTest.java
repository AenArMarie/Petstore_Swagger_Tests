package com.petstoretests.tests;

import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.models.PetModel;
import com.petstoretests.jsondata.testdata.TestData;
import com.petstoretests.requests.GetPetByIdRequest;
import com.petstoretests.tests.basetest.BaseTest;
import com.petstoretests.utility.APIUtilities;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetPetTest extends BaseTest {

    @Test
    public void getPetTest() {
        testData = FilesReader.readJson(PathConstants.TEST_DATA_PATH, TestData.class);
        Response getPetByIdResponse = GetPetByIdRequest.performGet(testData.getGetPetByIdTestData().getPet().id());
        assertEquals(HttpStatus.SC_OK, getPetByIdResponse.getStatusCode());
        PetModel petFromRequest = APIUtilities.parseResponseAs(getPetByIdResponse, PetModel.class);
        assertEquals(testData.getGetPetByIdTestData().getPet(), petFromRequest);
    }
}

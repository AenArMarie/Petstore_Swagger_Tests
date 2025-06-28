package com.petstoretests.tests;

import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.models.PetModel;
import com.petstoretests.jsondata.testdata.TestData;
import com.petstoretests.requests.PostPetRequest;
import com.petstoretests.tests.basetest.BaseTest;
import com.petstoretests.utility.APIUtilities;
import com.utility.files.FilesReader;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostPetTest extends BaseTest {

    @Test
    public void postPetTest() {
        testData = FilesReader.readJson(PathConstants.TEST_DATA_PATH, TestData.class);
        Response postPetResponse = PostPetRequest.performPost(testData.getGetPetByIdTestData().getPet());
        assertEquals(HttpStatus.SC_OK, postPetResponse.getStatusCode());
        assertEquals(testData.getGetPetByIdTestData().getPet(), APIUtilities.parseResponseAs(postPetResponse, PetModel.class));
    }
}

package com.petstoretests.tests;

import com.petstoretests.constants.CommonLogMessages;
import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.testdata.GetPetByIdTestData;
import com.petstoretests.requests.PostPetRequest;
import com.petstoretests.tests.basetest.BaseTest;
import com.petstoretests.utility.Waiter;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetPetTest extends BaseTest {

    @Test
    public void getPetTest() {
        ProjectLogger.info(CommonLogMessages.READING_TEST_DATA);
        GetPetByIdTestData testData = FilesReader.readJson(PathConstants.GET_PET_BY_ID_TEST_DATA_PATH, GetPetByIdTestData.class);

        ProjectLogger.info(CommonLogMessages.SENDING_POST_PET_REQUEST);
        Response postPetResponse = PostPetRequest.performPost(testData.getPet());
        assertEquals(HttpStatus.SC_OK, postPetResponse.getStatusCode(), CommonLogMessages.POST_PET_UNSUCCESSFUL);

        ProjectLogger.info(CommonLogMessages.SENDING_GET_PET_REQUEST);
        Response getPetByIdResponse = Waiter.waitUntilGetPetIsSuccessfulAndEqualsTo(testData.getPet());
        assertNotNull(getPetByIdResponse, CommonLogMessages.GET_PET_BY_ID_AND_COMPARE_UNSUCCESSFUL);
    }
}

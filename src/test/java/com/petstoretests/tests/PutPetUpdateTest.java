package com.petstoretests.tests;

import com.petstoretests.constants.CommonLogMessages;
import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.models.PetModel;
import com.petstoretests.jsondata.testdata.PutPetUpdatesTestData;
import com.petstoretests.requests.PostPetRequest;
import com.petstoretests.requests.PutPetUpdatesRequest;
import com.petstoretests.tests.basetest.BaseTest;
import com.petstoretests.utility.APIUtilities;
import com.petstoretests.utility.Waiter;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PutPetUpdateTest extends BaseTest {

    @Test
    public void putPetUpdateTest() {
        ProjectLogger.info(CommonLogMessages.READING_TEST_DATA);
        PutPetUpdatesTestData testData = FilesReader.readJson(PathConstants.PUT_PET_UPDATES_TEST_DATA_PATH, PutPetUpdatesTestData.class);

        ProjectLogger.info(CommonLogMessages.SENDING_POST_PET_REQUEST);
        Response postPetResponse = PostPetRequest.performPost(testData.getCreatedPet());
        assertEquals(HttpStatus.SC_OK, postPetResponse.getStatusCode(), CommonLogMessages.POST_PET_UNSUCCESSFUL);
        assertEquals(testData.getCreatedPet(), APIUtilities.parseResponseAs(postPetResponse, PetModel.class), CommonLogMessages.POSTED_PET_IS_DIFFERENT);

        ProjectLogger.info("Отправка запроса на изменение питомца");
        Response putPetUpdateResponse = Waiter.waitUntilNonNull(() -> {
            Response r = PutPetUpdatesRequest.performPut(testData.getChangedPet());
            return r.getStatusCode() == HttpStatus.SC_OK && APIUtilities.parseResponseAs(r, PetModel.class).equals(testData.getChangedPet()) ? r : null;
        });
        assertNotNull(putPetUpdateResponse, "Неверный код ответа при изменении питомца / Питомец в теле ответа не совпадает с тестовыми данными");

        ProjectLogger.info(CommonLogMessages.SENDING_GET_PET_REQUEST);
        Response getPetByIdResponse = Waiter.waitUntilGetPetIsSuccessfulAndEqualsTo(testData.getChangedPet());
        assertNotNull(getPetByIdResponse, CommonLogMessages.GET_PET_BY_ID_AND_COMPARE_UNSUCCESSFUL);
    }
}

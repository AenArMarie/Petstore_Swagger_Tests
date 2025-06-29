package com.petstoretests.tests;

import com.petstoretests.constants.CommonLogMessages;
import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.testdata.DeletePetTestData;
import com.petstoretests.requests.DeletePetByIdRequest;
import com.petstoretests.requests.GetPetByIdRequest;
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

public class DeletePetTest extends BaseTest {

    @Test
    public void deletePetTest() {
        ProjectLogger.info(CommonLogMessages.READING_TEST_DATA);
        DeletePetTestData testData = FilesReader.readJson(PathConstants.DELETE_PET_TEST_DATA_PATH, DeletePetTestData.class);

        ProjectLogger.info(CommonLogMessages.SENDING_POST_PET_REQUEST);
        Response postPetResponse = PostPetRequest.performPost(testData.getPet());
        assertEquals(HttpStatus.SC_OK, postPetResponse.getStatusCode(), CommonLogMessages.POST_PET_UNSUCCESSFUL);

        ProjectLogger.info("Отправка запроса на удаление питомца");
        Response deletePetByIdResponse = Waiter.waitUntilNonNull(() -> {
            Response r = DeletePetByIdRequest.performDelete(testData.getPet().id());
            return r.getStatusCode() == HttpStatus.SC_OK ? r : null;
        });
        assertNotNull(deletePetByIdResponse, "Ответ на запрос удаления питомца не имеет OK статуса");

        ProjectLogger.info(CommonLogMessages.SENDING_GET_PET_REQUEST);
        Response getPetByIdResponse = Waiter.waitUntilNonNull(() -> {
            Response r = GetPetByIdRequest.performGet(testData.getPet().id());
            return r.getStatusCode() == HttpStatus.SC_NOT_FOUND ? r : null;
        });
        assertNotNull(getPetByIdResponse, "Запрос на получение удаленного питомца успешен");
    }
}

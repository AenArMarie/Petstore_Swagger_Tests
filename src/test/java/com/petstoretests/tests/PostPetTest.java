package com.petstoretests.tests;

import com.petstoretests.constants.CommonLogMessages;
import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.models.PetModel;
import com.petstoretests.jsondata.testdata.PostPetTestData;
import com.petstoretests.requests.PostPetRequest;
import com.petstoretests.tests.basetest.BaseTest;
import com.petstoretests.utility.APIUtilities;
import com.petstoretests.utility.Waiter;
import com.utility.files.FilesReader;
import com.utility.logger.ProjectLogger;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostPetTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("petListProvider")
    public void postPetTest(PetModel pet) {
        ProjectLogger.info(CommonLogMessages.SENDING_POST_PET_REQUEST);
        Response postPetResponse = PostPetRequest.performPost(pet);
        assertEquals(HttpStatus.SC_OK, postPetResponse.getStatusCode(), CommonLogMessages.POST_PET_UNSUCCESSFUL);
        assertEquals(pet, APIUtilities.parseResponseAs(postPetResponse, PetModel.class), CommonLogMessages.POSTED_PET_IS_DIFFERENT);

        ProjectLogger.info(CommonLogMessages.SENDING_GET_PET_REQUEST);
        Response getPetByIdResponse = Waiter.waitUntilGetPetIsSuccessfulAndEqualsTo(pet);
        assertNotNull(getPetByIdResponse, CommonLogMessages.GET_PET_BY_ID_AND_COMPARE_UNSUCCESSFUL);
    }

    static Stream<PetModel> petListProvider() {
        ProjectLogger.info(CommonLogMessages.READING_TEST_DATA);
        return FilesReader.readJson(PathConstants.POST_PET_TEST_DATA_PATH, PostPetTestData.class).getPets().stream();
    }
}

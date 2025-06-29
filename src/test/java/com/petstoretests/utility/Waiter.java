package com.petstoretests.utility;

import com.petstoretests.constants.PathConstants;
import com.petstoretests.jsondata.WaitConfig;
import com.petstoretests.jsondata.models.PetModel;
import com.petstoretests.requests.GetPetByIdRequest;
import com.utility.files.FilesReader;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.awaitility.core.ConditionTimeoutException;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

import static org.awaitility.Awaitility.await;

public class Waiter {

    public static <T> T waitUntilNonNull(Supplier<T> supplier) {
        WaitConfig config = FilesReader.readJson(PathConstants.WAIT_CONFIG_PATH, WaitConfig.class);
        try {
            return await()
                    .atMost(Duration.ofSeconds(config.getWaitTimeSeconds()))
                    .pollInterval(Duration.ofMillis(config.getPollTimeMillis()))
                    .until(supplier::get, Objects::nonNull);
        } catch (ConditionTimeoutException e) {
            return null;
        }
    }

    public static Response waitUntilGetPetIsSuccessfulAndEqualsTo(PetModel pet) {
        return waitUntilNonNull(() -> {
            Response r = GetPetByIdRequest.performGet(pet.id());
            return r.getStatusCode() == HttpStatus.SC_OK && APIUtilities.parseResponseAs(r, PetModel.class).equals(pet) ? r : null;
        });
    }
}

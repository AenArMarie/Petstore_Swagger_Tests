package com.petstoretests.jsondata.testdata;

import com.petstoretests.jsondata.models.PetModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutPetUpdatesTestData {

    private PetModel createdPet;
    private PetModel changedPet;
}

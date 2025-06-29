package com.petstoretests.jsondata.testdata;

import com.petstoretests.jsondata.models.PetModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PostPetTestData {

    private ArrayList<PetModel> pets;
}

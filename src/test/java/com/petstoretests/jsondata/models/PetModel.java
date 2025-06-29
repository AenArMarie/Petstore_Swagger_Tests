package com.petstoretests.jsondata.models;


import java.util.ArrayList;

public record PetModel(Long id,
                       CategoryModel category,
                       String name,
                       ArrayList<String> photoUrls,
                       ArrayList<TagModel> tags,
                       String status) {
}

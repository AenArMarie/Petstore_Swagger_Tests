package com.petstoretests.jsondata.models;


import java.util.ArrayList;

public record PetModel(Integer id,
                       CategoryModel category,
                       String name,
                       ArrayList<String> photoUrls,
                       ArrayList<TagModel> tags,
                       String status) {
}

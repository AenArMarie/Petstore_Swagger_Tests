package com.petstoretests.utility;

import java.io.File;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIUtilities {

    public static Response getRequestWithHeadersAndQueryParams(String url, Map<String, String> headers, Map<String, String> queryParams) {
        return given()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .get(url)
                .then()
                .extract().response();
    }

    public static Response getRequest(String url) {
        return given()
                .when()
                .get(url)
                .then()
                .extract().response();
    }

    public static Response deleteRequest(String url) {
        return given()
                .when()
                .delete(url)
                .then()
                .extract().response();
    }

    public static Response postRequestWithBody(String url, ContentType contentType, Object body) {
        return given()
                .contentType(contentType)
                .body(body)
                .when()
                .post(url)
                .then()
                .extract().response();
    }

    public static Response putRequestWithBody(String url, ContentType contentType, Object body) {
        return given()
                .contentType(contentType)
                .body(body)
                .when()
                .put(url)
                .then()
                .extract().response();
    }

    public static Response putRequestWithHeadersAndQueryParams(String url, Map<String, String> headers, Map<String, String> queryParams) {
        return given()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .put(url)
                .then()
                .extract().response();
    }

    public static Response postRequestWithHeadersAndQueryParams(String url, Map<String, String> headers, Map<String, String> queryParams) {
        return given()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .post(url)
                .then()
                .extract().response();
    }

    public static Response deleteRequestWithHeadersAndQueryParams(String url, Map<String, String> headers, Map<String, String> queryParams) {
        return given()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .delete(url)
                .then()
                .extract().response();
    }

    public static <T> T parseResponseAs(Response response, Class<T> targetClass) {
        return response.as(targetClass);
    }
}

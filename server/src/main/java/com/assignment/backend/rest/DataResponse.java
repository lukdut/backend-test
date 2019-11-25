package com.assignment.backend.rest;

public class DataResponse<T> {
    private static final DataResponse OK_DATA_RESPONSE = new DataResponse<>(null, null);

    private final String errorMessage;
    private final T data;

    private DataResponse(String errorMessage, T data) {
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static DataResponse okResponse() {
        return OK_DATA_RESPONSE;
    }

    public static <T> DataResponse okResponse(T data) {
        return new DataResponse<>(null, data);
    }

    public static <T> DataResponse errorResponse(String errorMessage) {
        return new DataResponse<>(errorMessage, null);
    }
}

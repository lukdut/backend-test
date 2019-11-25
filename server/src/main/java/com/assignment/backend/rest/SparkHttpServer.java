package com.assignment.backend.rest;

import spark.Request;
import spark.Response;

import java.util.function.BiFunction;

import static spark.Spark.get;
import static spark.Spark.port;

public class SparkHttpServer {
    private SparkHttpServer() {
    }

    public static void setPort(int port) {
        port(port);
    }

    public static void addHandler(String uri, BiFunction<Request, Response, DataResponse> handler) {
        get(uri, handler::apply, new JsonTransformer());
    }
}
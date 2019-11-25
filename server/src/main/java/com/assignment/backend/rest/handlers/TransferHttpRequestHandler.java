package com.assignment.backend.rest.handlers;

import com.assignment.backend.rest.DataResponse;
import com.assignment.backend.service.RequestHandler;
import spark.Request;
import spark.Response;

import java.util.function.BiFunction;

public class TransferHttpRequestHandler implements BiFunction<Request, Response, DataResponse> {
    private final RequestHandler requestHandler;

    public TransferHttpRequestHandler(RequestHandler requestHandler){
        this.requestHandler = requestHandler;
    }

    @Override
    public DataResponse apply(Request request, Response response) {
        Long numberFrom;
        Long numberTo;
        long amount;
        try {
            numberFrom = Long.valueOf(request.queryParams("from"));
            numberTo = Long.valueOf(request.queryParams("to"));
            amount = Long.parseLong(request.queryParams("amount"));
        } catch (Exception e) {
            //there is no detailed validation according to the requirement #2
            return DataResponse.errorResponse("Incorrect input parameter(s)");
        }

        try {
            return requestHandler.transfer(numberFrom, numberTo, amount) ? DataResponse.okResponse() :
                    DataResponse.errorResponse("Transfer is not available");
        } catch (Exception e) {
            return DataResponse.errorResponse("Transfer error");
        }
    }
}

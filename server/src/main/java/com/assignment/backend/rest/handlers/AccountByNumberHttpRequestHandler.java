package com.assignment.backend.rest.handlers;

import com.assignment.backend.rest.DataResponse;
import com.assignment.backend.storage.InHeapAccountDAO;
import spark.Request;
import spark.Response;

import java.util.function.BiFunction;

public class AccountByNumberHttpRequestHandler implements BiFunction<Request, Response, DataResponse> {
    private final InHeapAccountDAO inHeapAccountDAO;

    public AccountByNumberHttpRequestHandler(InHeapAccountDAO inHeapAccountDAO) {
        this.inHeapAccountDAO = inHeapAccountDAO;
    }

    @Override
    public DataResponse apply(Request request, Response response) {
        Long accountNumber;
        try {
            accountNumber = Long.valueOf(request.params(":number"));
        } catch (Exception e) {
            return DataResponse.errorResponse("Account is not a number or not specified");
        }
        return DataResponse.okResponse(inHeapAccountDAO.getByNumber(accountNumber));
    }
}

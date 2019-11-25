package com.assignment.backend.rest.handlers;

import com.assignment.backend.model.Account;
import com.assignment.backend.rest.DataResponse;
import com.assignment.backend.storage.InHeapAccountDAO;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class AllAccountsHttpRequestHandler implements BiFunction<Request, Response, DataResponse> {

    private final InHeapAccountDAO inHeapAccountDAO;
    private final long startAvailableNum;
    private final long endAvailableNum;

    public AllAccountsHttpRequestHandler(InHeapAccountDAO inHeapAccountDAO, long startAvailableNum, long endAvailableNum){
        this.inHeapAccountDAO = inHeapAccountDAO;
        this.startAvailableNum = startAvailableNum;
        this.endAvailableNum = endAvailableNum;
    }

    @Override
    public DataResponse apply(Request request, Response response) {
        final List<Account> accounts = LongStream.range(startAvailableNum, endAvailableNum)
                .mapToObj(inHeapAccountDAO::getByNumber)
                .map(Optional::get)
                .collect(Collectors.toList());

        return DataResponse.okResponse(accounts);
    }
}

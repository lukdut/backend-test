package com.assignment.backend;

import com.assignment.backend.model.Account;
import com.assignment.backend.rest.SparkHttpServer;
import com.assignment.backend.rest.handlers.AccountByNumberHttpRequestHandler;
import com.assignment.backend.rest.handlers.AllAccountsHttpRequestHandler;
import com.assignment.backend.rest.handlers.TransferHttpRequestHandler;
import com.assignment.backend.service.InMemoryTransferService;
import com.assignment.backend.service.NoCreditAllowedArbiter;
import com.assignment.backend.service.RequestHandler;
import com.assignment.backend.service.TransferService;
import com.assignment.backend.storage.InHeapAccountDAO;
import com.assignment.backend.storage.IncrementalIdGenerator;
import com.assignment.backend.storage.MapBasedAccountDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private static final int TOTAL_ACCOUNTS = 10;
    private static final long DEFAULT_BALANCE = 1000;

    public static void main(String[] args) {
        final InHeapAccountDAO mapBasedAccountDAO = new MapBasedAccountDAO(new IncrementalIdGenerator());
        final TransferService transferService = new InMemoryTransferService(new NoCreditAllowedArbiter());
        final RequestHandler requestHandler = new RequestHandler(mapBasedAccountDAO::getByNumber, transferService);

        for (int i = 0; i < TOTAL_ACCOUNTS; i++) {
            Account account = mapBasedAccountDAO.createNewAccount();
            account.setMinorUnitBalance(DEFAULT_BALANCE);
        }

        final int port = 8080;
        SparkHttpServer.setPort(port);
        SparkHttpServer.addHandler("/transfer", new TransferHttpRequestHandler(requestHandler));
        SparkHttpServer.addHandler("/account/:number", new AccountByNumberHttpRequestHandler(mapBasedAccountDAO));
        SparkHttpServer.addHandler("/accounts",
                new AllAccountsHttpRequestHandler(mapBasedAccountDAO, 0, TOTAL_ACCOUNTS));

        LOGGER.info("Server started using port {}", port);

        LOGGER.info("Available account in range [0;{}) with initial amount {}", TOTAL_ACCOUNTS, DEFAULT_BALANCE);
    }
}

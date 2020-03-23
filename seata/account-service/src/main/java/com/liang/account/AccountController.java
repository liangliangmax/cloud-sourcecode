package com.liang.account;

import com.liang.feign.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController implements AccountClient {
    /**
     * 账户业务逻辑
     */
    @Autowired
    private AccountService accountService;

    @Override
    public void deduction(Integer accountId, Double money) {
        accountService.deduction(accountId, money);
    }
}
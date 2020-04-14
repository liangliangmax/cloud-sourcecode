package com.liang.account;

import com.liang.account.service.AccountService;
import com.liang.feign.AccountClient;
import com.liang.seata.dto.RestApiResult;
import com.liang.seata.dto.ResultCode;
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

    @Override
    public RestApiResult<Boolean> deductionRest(Integer accountId, Double money) {
        try {
            accountService.deduction(accountId, money);
            return RestApiResult.OK(true);
        }catch (Exception e){

            e.printStackTrace();
            return RestApiResult.ERROR(ResultCode.ERROR,e.getMessage());
        }

    }
}
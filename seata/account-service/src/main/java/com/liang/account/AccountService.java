package com.liang.account;

import com.liang.seata.entity.Account;
import com.liang.seata.entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private InfoMapper infoMapper;

    /**
     * {@link EnhanceMapper} 具体使用查看ApiBoot官网文档http://apiboot.minbox.io/zh-cn/docs/api-boot-mybatis-enhance.html
     *
     * @param accountId {@link Account#getId()}
     * @param money     扣除的金额
     */
    public void deduction(Integer accountId, Double money) {
        Account account = accountMapper.selectByPrimaryKey(accountId);

        if (ObjectUtils.isEmpty(account)) {
            throw new RuntimeException("账户：" + accountId + "，不存在.");
        }
        if (account.getMoney() - money < 0) {
            throw new RuntimeException("账户：" + accountId + "，余额不足.");
        }
        account.setMoney(account.getMoney().doubleValue() - money);
        accountMapper.updateByPrimaryKeySelective(account);

        Info info = new Info();
        info.setId(UUID.randomUUID().toString());
        info.setDes("ahahah");
        infoMapper.insertSelective(info);

    }

}
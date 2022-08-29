package com.spdb.service;

import com.spdb.vo.AccountVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<AccountVo> getAllAccounts();
}

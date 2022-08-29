package com.spdb.mapper;

import com.spdb.vo.AccountVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    List<AccountVo> getAllAccounts();
}

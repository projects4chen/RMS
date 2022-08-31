package com.spdb.service;

import com.spdb.pojo.Ident;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IdentService {

    /**
     * 获取所有的权限
     */
    List<Ident> getAllIdents();

    /**
     * 根据权限id获取对应的权限内容
     * @param identity
     * @return
     */
    String getIdentityInfo(Long identity);
}

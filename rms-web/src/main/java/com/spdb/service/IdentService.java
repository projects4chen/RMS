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
}

package com.spdb.mapper;

import com.spdb.pojo.Ident;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdentMapper {

    // 获取所有的权限信息
    List<Ident> getAllIdents();

    // 根据identify编号获取权限的信息
    String getIdentityInfo(Long identity);
}

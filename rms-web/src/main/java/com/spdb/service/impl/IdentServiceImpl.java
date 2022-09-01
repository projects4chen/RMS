package com.spdb.service.impl;

import com.spdb.mapper.IdentMapper;
import com.spdb.pojo.Ident;
import com.spdb.service.IdentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentServiceImpl implements IdentService {

    @Autowired
    private IdentMapper identMapper;

    @Override
    public List<Ident> getAllIdents() {
        return identMapper.getAllIdents();
    }

    @Override
    public String getIdentityInfo(Long identity) {
        return identMapper.getIdentityInfo(identity);
    }
}

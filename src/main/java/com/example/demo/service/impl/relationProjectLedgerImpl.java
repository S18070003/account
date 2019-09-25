package com.example.demo.service.impl;

import com.example.demo.dao.RelationProjectLedgerMapper;
import com.example.demo.entity.RelationProjectLedger;
import com.example.demo.service.relationProjectLedger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class relationProjectLedgerImpl implements relationProjectLedger {
    @Autowired
    public RelationProjectLedgerMapper relationProjectLedgerMapper;
    @Override
    public int insert(RelationProjectLedger relationProjectLedger){
        return relationProjectLedgerMapper.insertSelective(relationProjectLedger);
    }
    @Override
    public int delete(int ledgerid){
        return relationProjectLedgerMapper.delete(ledgerid);
    }
}

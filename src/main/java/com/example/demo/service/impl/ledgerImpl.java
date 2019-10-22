package com.example.demo.service.impl;

import com.example.demo.dao.ContractLedgerMapper;
import com.example.demo.entity.ContractLedger;
import com.example.demo.model.HomeAllLedger;
import com.example.demo.model.LedgerSelect;
import com.example.demo.service.ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ledgerImpl implements ledger {
    @Autowired
    public ContractLedgerMapper contractLedgerMapper;
    @Override
    public List<ContractLedger> getAll(){
        return contractLedgerMapper.getAll();
    }
    @Override
    public int insert(ContractLedger contractLedger){
        return contractLedgerMapper.insertSelective(contractLedger);
    }
    @Override
    public List<ContractLedger> getByProjectId(String id){
        return contractLedgerMapper.getByProjectid(id);
    }
    @Override
    public  int update(ContractLedger contractLedger){
        return contractLedgerMapper.updateByPrimaryKeySelective(contractLedger);
    }
    @Override
    public  int delete(int id){
        return contractLedgerMapper.deleteByPrimaryKey(id);
    }
    @Override
    public List<HomeAllLedger> getHomeLedgerAll(LedgerSelect ledgerSelect){
        return contractLedgerMapper.HomeLedgerAll(ledgerSelect);
    }
    @Override
    public List<HomeAllLedger> ProjectLedgerAll(String id){
        return contractLedgerMapper.ProjectLedgerAll(id);
    }
}

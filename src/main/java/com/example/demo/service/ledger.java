package com.example.demo.service;

import com.example.demo.entity.ContractLedger;
import com.example.demo.model.HomeAllLedger;
import com.example.demo.model.LedgerSelect;

import java.util.List;

public interface ledger {
    List<ContractLedger> getAll();
    int insert(ContractLedger contractLedger);
    List<ContractLedger> getByProjectId(String id);
    int update(ContractLedger contractLedger);
    int delete(int id);
    List<HomeAllLedger> getHomeLedgerAll(LedgerSelect ledgerSelect);
    List<HomeAllLedger> ProjectLedgerAll(String id);
}

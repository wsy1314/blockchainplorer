package io.wsy.blockchainexplorer.controller;


import io.wsy.blockchainexplorer.dto.TransactionInfoDTO;
import io.wsy.blockchainexplorer.dto.TransactionListDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @GetMapping("/getRecentTransactionsById")
  public List<TransactionListDTO> getRecentTransactionsById(@RequestParam Integer blockchainId){
        return null;
    }


    @GetMapping("/getRecentTransactionsByNameType")
    public List<TransactionListDTO> getRecentTransactionsByNameType(@RequestParam String name,
                                                                     @RequestParam String type){
    return  null;
    }

    @GetMapping("/getTransactionInfoByTxid")
    public TransactionInfoDTO getTransactionInfoByTxid(@RequestParam String txid){
    return null;
    }

    @GetMapping("/getTransactionInfoByTxhash")
    public TransactionInfoDTO getTransactionInfoByTxhash(String txhash){
        return null;
    }



}

package io.wsy.blockchainexplorer.controller;


import io.wsy.blockchainexplorer.dao.TransactionMapper;
import io.wsy.blockchainexplorer.dao.Transaction_DetailMapper;
import io.wsy.blockchainexplorer.dto.TransactionDetailDTO;
import io.wsy.blockchainexplorer.dto.TransactionInBlockDTO;
import io.wsy.blockchainexplorer.po.Transaction;
import io.wsy.blockchainexplorer.po.Transaction_Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactiondetail")
@EnableAutoConfiguration
@CrossOrigin
public class TransactionDetailController {

    @Autowired
    private Transaction_DetailMapper transaction_detailMapper;
    @Autowired
    private TransactionMapper transactionMapper;


    @GetMapping("/selectTransactionIdtoAddress")
     public TransactionDetailDTO selectTransactionIdtoAddress(String address){
         TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO();
         transactionDetailDTO.setAddress(address);
         //根据所输入地址查询地址信息
         List<Transaction_Detail> transaction_details = transaction_detailMapper.selectByAddress(address);
         //创建一个存放查询信息集合
         ArrayList<TransactionInBlockDTO> transactionInBlockDTOS = new ArrayList<>();
         for (Transaction_Detail transaction_detail : transaction_details) {
             //根据查询的地址的ID查询交易信息
           TransactionInBlockDTO transactionInBlockDTO = transactionMapper.selectByPrimaryKey(transaction_detail.getTxid());
             List<Transaction_Detail> transaction_details1 = transaction_detailMapper.selectTransactionTxid(transactionInBlockDTO.getTxid());
             transactionInBlockDTO.setTxDetailInTxInfos(transaction_details1);
             transactionInBlockDTOS.add(transactionInBlockDTO);
         }
         transactionDetailDTO.setCount(transactionInBlockDTOS.size());
         transactionDetailDTO.setTransactionInBlockDTOS(transactionInBlockDTOS);
         return transactionDetailDTO;
     }

}

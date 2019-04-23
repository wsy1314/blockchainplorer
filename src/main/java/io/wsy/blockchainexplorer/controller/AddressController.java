package io.wsy.blockchainexplorer.controller;


import io.wsy.blockchainexplorer.dao.Transaction_DetailMapper;
import io.wsy.blockchainexplorer.dto.AddressInfo;
import io.wsy.blockchainexplorer.dto.TransactionInBlockDTO;
import io.wsy.blockchainexplorer.po.Transaction_Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private Transaction_DetailMapper transaction_detailMapper;

    @GetMapping("/getAddressInfo")
      public AddressInfo getAddressInfo(@RequestParam String address){
        return  null;
    }


    @GetMapping("/getAddressTransactions")
    public List<Transaction_Detail> getAddressTransantions(@RequestParam String address,
                                                               @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        List<Transaction_Detail> transaction_details = transaction_detailMapper.selectByAddress(address);
        return  transaction_details;
    }

}

package io.wsy.blockchainexplorer.dto;

import io.wsy.blockchainexplorer.po.Transaction_Detail;

import java.util.List;

public class TransactionDetailDTO extends Transaction_Detail {

    private Integer count;

    private List<TransactionInBlockDTO> transactionInBlockDTOS;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<TransactionInBlockDTO> getTransactionInBlockDTOS() {
        return transactionInBlockDTOS;
    }

    public void setTransactionInBlockDTOS(List<TransactionInBlockDTO> transactionInBlockDTOS) {
        this.transactionInBlockDTOS = transactionInBlockDTOS;
    }
}

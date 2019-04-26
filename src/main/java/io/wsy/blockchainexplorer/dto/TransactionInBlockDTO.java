package io.wsy.blockchainexplorer.dto;

import io.wsy.blockchainexplorer.po.Transaction_Detail;

import java.util.Date;
import java.util.List;

public class TransactionInBlockDTO {

    private String txid;

    private String txhash;

    private Long size;

    private Date time;

    private Double oneTxOutputTotal;

    private List<Transaction_Detail> txDetailInTxInfos;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getOneTxOutputTotal() {
        return oneTxOutputTotal;
    }

    public void setOneTxOutputTotal(Double oneTxOutputTotal) {
        this.oneTxOutputTotal = oneTxOutputTotal;
    }

    public List<Transaction_Detail> getTxDetailInTxInfos() {
        return txDetailInTxInfos;
    }

    public void setTxDetailInTxInfos(List<Transaction_Detail> txDetailInTxInfos) {
        this.txDetailInTxInfos = txDetailInTxInfos;
    }
}

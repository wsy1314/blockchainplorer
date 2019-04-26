package io.wsy.blockchainexplorer.dao;

import io.wsy.blockchainexplorer.dto.TransactionInBlockDTO;
import io.wsy.blockchainexplorer.po.Transaction;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(String txid);

    int truncate();

    int insert(Transaction record);

    int insertSelective(Transaction record);

    TransactionInBlockDTO selectByPrimaryKey(String txid);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    List<TransactionInBlockDTO> selectByBlockhash(String blockhash);
}
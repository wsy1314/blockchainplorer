package io.wsy.blockchainexplorer.dao;

import io.wsy.blockchainexplorer.po.Transaction_Detail;
import io.wsy.blockchainexplorer.po.Transaction_DetailKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Transaction_DetailMapper {
    int deleteByPrimaryKey(Transaction_DetailKey key);

    int truncate();

    int insert(Transaction_Detail record);

    List<Transaction_Detail> selectByAddress(@Param("address") String address);

    int insertSelective(Transaction_Detail record);

    Transaction_Detail selectByPrimaryKey(Transaction_DetailKey key);

    int updateByPrimaryKeySelective(Transaction_Detail record);

    int updateByPrimaryKey(Transaction_Detail record);
}
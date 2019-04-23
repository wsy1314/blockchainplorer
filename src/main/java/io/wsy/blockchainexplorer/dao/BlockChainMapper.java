package io.wsy.blockchainexplorer.dao;

import io.wsy.blockchainexplorer.po.BlockChain;

public interface BlockChainMapper {
    int deleteByPrimaryKey(Integer blockchainId);

    int insert(BlockChain record);

    int insertSelective(BlockChain record);

    BlockChain selectByPrimaryKey(Integer blockchainId);

    int updateByPrimaryKeySelective(BlockChain record);

    int updateByPrimaryKey(BlockChain record);
}
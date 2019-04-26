package io.wsy.blockchainexplorer.dao;

import io.wsy.blockchainexplorer.dto.BlockDetailDTO;
import io.wsy.blockchainexplorer.po.Block;

import java.util.List;

public interface BlockMapper {
    int deleteByPrimaryKey(String blockhash);

    int truncate();

    List<Block> selectRecent();

    int insert(Block record);

    int insertSelective(Block record);

    BlockDetailDTO selectByPrimaryKey(String blockhash);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);

    BlockDetailDTO selectBlockDetailByHeight(Integer blockheight);
}
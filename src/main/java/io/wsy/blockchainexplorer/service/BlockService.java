package io.wsy.blockchainexplorer.service;

import io.wsy.blockchainexplorer.dto.BlockDetailDTO;

public interface BlockService {

    BlockDetailDTO getBlockDetail(Integer blockHeight) throws Throwable;

}

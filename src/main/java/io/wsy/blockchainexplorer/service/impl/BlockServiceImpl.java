package io.wsy.blockchainexplorer.service.impl;

import io.wsy.blockchainexplorer.dto.BlockDetailDTO;
import io.wsy.blockchainexplorer.service.BlockService;
import org.springframework.stereotype.Service;

@Service
public class BlockServiceImpl implements BlockService {

    @Override
    public BlockDetailDTO getBlockDetail(Integer blockHeight) throws Throwable {
        return null;
    }
}

package io.wsy.blockchainexplorer.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.wsy.blockchainexplorer.api.BitcoinApi;
import io.wsy.blockchainexplorer.api.BitcoinJsonRpcClient;
import io.wsy.blockchainexplorer.dao.BlockMapper;
import io.wsy.blockchainexplorer.dao.TransactionMapper;
import io.wsy.blockchainexplorer.dao.Transaction_DetailMapper;
import io.wsy.blockchainexplorer.dto.BlockDetailDTO;
import io.wsy.blockchainexplorer.dto.BlockListDTO;
import io.wsy.blockchainexplorer.dto.TransactionInBlockDTO;
import io.wsy.blockchainexplorer.po.Block;
import io.wsy.blockchainexplorer.po.Transaction_Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/block")
@CrossOrigin
public class BlockController {

    @Autowired
    private BitcoinApi bitcoinApi;
    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private Transaction_DetailMapper transaction_detailMapper;

    @Value("${blockchain.recentCount}")
    private Integer recentCount;

    /***
     * 获取区块链的块的列表
     * 首页的当前币的前五个块
     * @param blockchainId
     * @return
     * @throws Throwable
     */
    @GetMapping("/getRecentBlocks")
    public List<BlockListDTO> getRecentBlocks(@RequestParam(defaultValue = "2") Integer blockchainId) throws Throwable {



//        String bestBlcokhash = bitcoinJsonRpcClient.getBestBlcokhash();
//        String tempBlockhash = bestBlcokhash;
//
//        List<BlockListDTO> blockListDTOS = new LinkedList<>();
//
//        for (int i = 0; i < 5; i++) {
//            JSONObject block = bitcoinApi.getNoTxBlock(tempBlockhash);
//            BlockListDTO blockListDTO = new BlockListDTO();
//            blockListDTO.setHeight(block.getInteger("height"));
//            Long time = block.getLong("time");
//            Date date = new Date(time * 1000);
//            blockListDTO.setTime(date);
//            blockListDTO.setTxSize(block.getJSONArray("tx").size());
//            blockListDTO.setSizeOnDisk(block.getLong("size"));
//            blockListDTOS.add(blockListDTO);
//            tempBlockhash = block.getString("previousblockhash");
//        }

//        JSONObject chainInfo = bitcoinApi.getChainInfo();
//        Integer height = chainInfo.getInteger("blocks");
//        height -= 5;
//        String blockHashByHeight = bitcoinJsonRpcClient.getBlockHashByHeight(height);
//        JSONArray blockHeaders = bitcoinApi.getBlockHeaders(5, blockHashByHeight);
//
//        LinkedList<BlockListDTO> blockListDTOS = new LinkedList<>();
//        for (int i = 4; i > -1; i--) {
//            JSONObject block = blockHeaders.getJSONObject(i);
//            BlockListDTO blockListDTO = new BlockListDTO();
//            blockListDTO.setHeight(block.getInteger("height"));
//            Long time = block.getLong("time");
//            Date date = new Date(time * 1000);
//            blockListDTO.setTime(date);
//            //todo add size on disk
//            blockListDTO.setTxSize(block.getInteger("nTx"));
//            blockListDTOS.add(blockListDTO);
//        }

        //根据数据库查询最新的五条数据
        List<Block> blocks = blockMapper.selectRecent();
        List<BlockListDTO> blockListDTOS = blocks.stream().map(block -> {
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setHeight(block.getHeight());
            blockListDTO.setTime(block.getTime().getTime());
            blockListDTO.setTxSize(block.getTxSize());
            blockListDTO.setSizeOnDisk(block.getSizeOnDisk());

            return blockListDTO;
        }).collect(Collectors.toList());


        return blockListDTOS;

       // bitcoinApi.getBlockHeaders(recentCount,bestBlcokhash);

    }


    @GetMapping("/getRecentBlocksByNameType")

    public List<BlockListDTO> getRecentBlocksByNameType(@RequestParam String name,
                                                        @RequestParam String type) {
        return null;
    }


    /***
     * 根据块哈希查询块信息
     * @param blockhash
     * @return
     */
    @GetMapping("/getBlockDetailByHash")
    public BlockDetailDTO getBlockDetailByHash(@RequestParam String blockhash) {
        //查询快信息
        BlockDetailDTO blockDetailDTO = blockMapper.selectByPrimaryKey(blockhash);
        //根据快的hash查询出每笔交易的信息
        List<TransactionInBlockDTO> transactionInBlockDTOS = transactionMapper.seleByBlockhash(blockDetailDTO.getBlockhash());
        //根据每笔交易ID查询出交易信息
        for (TransactionInBlockDTO transactionInBlockDTO : transactionInBlockDTOS) {
            List<Transaction_Detail> txDetailInTxInfos = transaction_detailMapper.selectTransactionTxid(transactionInBlockDTO.getTxid());
            transactionInBlockDTO.setTxDetailInTxInfos(txDetailInTxInfos);
        }
        //把查询出来的交易信息放入其中
        blockDetailDTO.setTransactions(transactionInBlockDTOS);

        return blockDetailDTO;
    }


    @GetMapping("/getBlockDetailByHeight")
    public BlockDetailDTO getBlockDetailByHeight(@RequestParam Integer blockheight) {
        return null;
    }
}

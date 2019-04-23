package io.wsy.blockchainexplorer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.wsy.blockchainexplorer.api.BitcoinApi;
import io.wsy.blockchainexplorer.api.BitcoinJsonRpcClient;
import io.wsy.blockchainexplorer.dao.BlockMapper;
import io.wsy.blockchainexplorer.dao.TransactionMapper;
import io.wsy.blockchainexplorer.dao.Transaction_DetailMapper;
import io.wsy.blockchainexplorer.enumeration.TransactionDetailType;
import io.wsy.blockchainexplorer.po.Block;
import io.wsy.blockchainexplorer.po.Transaction;
import io.wsy.blockchainexplorer.po.Transaction_Detail;
import io.wsy.blockchainexplorer.service.MiscService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MiscServiceImpl implements MiscService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BitcoinApi bitcoinApi;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private Transaction_DetailMapper transaction_detailMapper;
    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @Async
    @Override
    public void importFromHeight(Integer blockHeight, Boolean isClean) {

    }


    @Async
    @Override
    public void importFromHash(String blockHash, Boolean isClean) throws Throwable {
        if (isClean){
blockMapper.truncate();
transactionMapper.truncate();
transaction_detailMapper.truncate();
//todo tuncate transaction table
        }
        String temphash = blockHash;

        while (temphash != null && !temphash.isEmpty()){
            JSONObject blockOrigin = bitcoinApi.getBlock(temphash);
            Block block = new Block();
            block.setBlockhash(blockOrigin.getString("hash"));
            block.setBlockchainId(2);
            block.setHeight(blockOrigin.getInteger("height"));
            Long time = blockOrigin.getLong("time");
            Date date = new Date(time * 1000);
            block.setTime(date);

            JSONArray txes = blockOrigin.getJSONArray("tx");
            for (int i = 0;i<txes.size() ; i++){
                importTx(txes.getJSONObject(i),temphash,date);
            }

            block.setTxSize(txes.size());
            block.setSizeOnDisk(blockOrigin.getLong("size"));
            block.setDifficulty(blockOrigin.getDouble("difficulty"));
            block.setPrevBlockhash(blockOrigin.getString("previousblockhash"));
            block.setNextBlockhash(blockOrigin.getString("nextblockhash"));
            block.setMarkleRoot(blockOrigin.getString("merkleroot"));
            blockMapper.insert(block);

             temphash = blockOrigin.getString("nextblockhash");
        }

        logger.info("sync finshed");

    }

    public void importTx(JSONObject tx, String currentBlockHash, Date time) throws Throwable {
        Transaction transaction = new Transaction();
        String txid = tx.getString("txid");
        transaction.setTxid(txid);
        transaction.setTxhash(tx.getString("hash"));
        transaction.setBlockhash(currentBlockHash);
        transaction.setSize(tx.getLong("size"));
        transaction.setWeight(tx.getInteger("weight"));
        transaction.setTime(time);
        transactionMapper.insert(transaction);
        JSONArray vouts = tx.getJSONArray("vout");
        for (int i = 0; i < vouts.size() ; i++) {
    importVoutDetail(vouts.getJSONObject(i),txid);
        }
        
        JSONArray vins = tx.getJSONArray("vin");

        //todo vin0 coinbase tx

        for (int i = 1; i < vins.size(); i++) {
            importVinDetail(vins.getJSONObject(i),txid);
        }

    }

    public void importVoutDetail(JSONObject vout,String txid){
        Transaction_Detail transaction_detail = new Transaction_Detail();
        transaction_detail.setTxid(txid);
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        //todo check whether sync addresses
        if (addresses !=null && !addresses.isEmpty()){
            String address = addresses.getString(0);
            transaction_detail.setAddress(address);
}
        Double amount = vout.getDouble("value");
        transaction_detail.setAmount(amount);

        transaction_detail.setType((byte) TransactionDetailType.Receive.ordinal());

        transaction_detailMapper.insert(transaction_detail);
    }

    public void importVinDetail(JSONObject vin,String txidOrigin) throws Throwable {
        String txid = vin.getString("txid");
        Integer vout = vin.getInteger("vout");

//        //todo check coinbass tx
//        if (txid == null){
//            return;
//        }

        JSONObject rawTransaction = bitcoinJsonRpcClient.getRawTransaction(txid);
        JSONArray vouts = rawTransaction.getJSONArray("vout");
        JSONObject jsonObject = vouts.getJSONObject(vout);

        Transaction_Detail transaction_detail = new Transaction_Detail();
        transaction_detail.setTxid(txidOrigin);
        transaction_detail.setType((byte) TransactionDetailType.send.ordinal());
        Double amount = jsonObject.getDouble("value");
        transaction_detail.setAmount(amount);
        JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses != null){
            String address = addresses.getString(0);
            transaction_detail.setAddress(address);
        }

        transaction_detailMapper.insert(transaction_detail);
    }
}

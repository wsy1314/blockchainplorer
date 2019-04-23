package io.wsy.blockchainexplorer.controller;




import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.wsy.blockchainexplorer.api.BitcoinApi;
import io.wsy.blockchainexplorer.api.BitcoinJsonRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempController {

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @GetMapping("/test")
    public void test() throws Throwable {
        JSONObject chainInfo = bitcoinApi.getChainInfo();

        String txhash = "9b982f2a376af06c13e4db538aa73c3d73122717ffdb3e71279e710e4d2de16a";

        JSONObject transaction = bitcoinApi.getTransaction(txhash);

        String blockhash = "00000000000ac8ea42a563c4fe9d212090dba96cd2746bc5ef6ed7b9e8ccf3a8";
        JSONObject block = bitcoinApi.getBlock(blockhash);

        JSONObject noTxBlock = bitcoinApi.getNoTxBlock(blockhash);


        String blockhash2 = "000000000067beb907e9da98ddd4d18525917bba40126acaa567ea5cc863653d";
        JSONArray blockHeaders = bitcoinApi.getBlockHeaders(10, blockhash2);


        JSONObject mempoolInfo = bitcoinApi.getMempoolInfo();

        JSONObject mempoolContents = bitcoinApi.getMempoolContents();

        String blockHashByHeight = bitcoinJsonRpcClient.getBlockHashByHeight(1489667);

        String address = "";
        Double balance = bitcoinJsonRpcClient.getBalance(address);


        String txid = "df6860c7cb4dbba3e630733dc074253a0797a3407b238a86cb499a82b1892c1a";
//        Integer n =0;
//        bitcoinApi.getUTXO(txid,n);

        JSONObject rawTransaction = bitcoinJsonRpcClient.getRawTransaction(txid);

    }

}

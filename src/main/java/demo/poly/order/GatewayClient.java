package demo.poly.order;

import org.hyperledger.fabric.gateway.*;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author nature
 * @date 28/5/2022 9:31 上午
 * @email 924943578@qq.com
 */
public class GatewayClient {
    //创建 SingleObject 的一个对象
    private static GatewayClient instance = null;

    private static Contract contract = null;

    //让构造函数为 private，这样该类就不会被实例化
    private GatewayClient(){}

    //获取唯一可用的对象
    public static GatewayClient getInstance(){
        if(instance == null) {
            try {
                Gateway gateway = connectToOrg1();
                Network network = gateway.getNetwork("mychannel");
                contract = network.getContract("peth");
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = new GatewayClient();
        }

        return instance;
    }

    public String getMyAddress(){
        return "8c6f11b1e7e22bdbe2662dcad51c09190720ef6c";
    }

    public Integer balanceOf(String address)  {
        Integer balance = 0;
        try {
            byte[] bytes = contract.evaluateTransaction("balanceOf", address);
            String hex = bytesToHex(bytes);
            if(hex == null || hex.equalsIgnoreCase(""))
                hex = "0";
            balance = Integer.parseInt(hex, 16);
        } catch (ContractException e) {
            throw new RuntimeException(e);
        }
        return balance;
    }

    public String getBondAddress(){
        return getMyAddress();
    }

    public Integer bondBalanceOf(String address)  {
       return balanceOf(address);
    }


    public Boolean createOrder(String address, String amount)  {
        Boolean flags = true;
//        try {
//            contract.submitTransaction("createOrder", address, amount);
//            flags = true;
//        } catch (ContractException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (TimeoutException e) {
//            throw new RuntimeException(e);
//        }
        return flags;
    }
    // helper function for getting connected to the gateway
    private static Gateway connectToOrg1() throws Exception{
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("src/main/resources/wallet");
        Wallet wallet = Wallet.createFileSystemWallet(walletPath);
//        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
//        Path networkConfigPath = Paths.get("src/main/resources/connection-org1.json");
        Path networkConfigPath = Paths.get("src/main/resources/connection-org1-ca.json");
//        Path networkConfigPath = Paths.get("src/main/resources/config_e2e.yaml");

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "Admin@org1.example.com").networkConfig(networkConfigPath);
        return builder.connect();
    }

    private final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private  String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static void main(String[] args) throws Exception {
        String myAddress = GatewayClient.getInstance().getMyAddress();
        Integer myBalance = GatewayClient.getInstance().balanceOf(myAddress);
        System.out.println("myAddress:"+myAddress);
        System.out.println("myBalance:"+myBalance);

    }

}

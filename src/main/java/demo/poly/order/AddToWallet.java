
package demo.poly.order;

import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallet.Identity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddToWallet {


  public static void addOrg1(){
    try {
      // A wallet stores a collection of identities
      Path walletPath = Paths.get("src/main/resources/wallet");
      Wallet wallet = Wallet.createFileSystemWallet(walletPath);

      Path credentialPath = Paths.get("src/main/resources/crypto-config/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp");
      System.out.println("credentialPath: " + credentialPath.toString());
      Path certificatePath = credentialPath.resolve(Paths.get("signcerts",
              "Admin@org1.example.com-cert.pem"));
      System.out.println("certificatePem: " + certificatePath.toString());
      Path privateKeyPath = credentialPath.resolve(Paths.get("keystore", "b8d1a75f1ea66d20ca0f40d46e7bfd656484edd650d8e91cb76a810e531b69f1_sk"));



      Identity identity = Identity.createIdentity("Org1MSP", Files.newBufferedReader(certificatePath), Files.newBufferedReader(privateKeyPath));


      String identityLabel = "Admin@org1.example.com";
      wallet.put(identityLabel, identity);

      System.out.println("Write wallet info into " + walletPath.toString() + " successfully.");

    } catch (IOException e) {
      System.err.println("Error adding to wallet");
      e.printStackTrace();
    }
  }

  public static void addOrg2(){
    try {
      // A wallet stores a collection of identities
      Path walletPath = Paths.get("src/main/resources/wallet");
      Wallet wallet = Wallet.createFileSystemWallet(walletPath);

      Path credentialPath = Paths.get("src/main/resources/crypto-config/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp");
      System.out.println("credentialPath: " + credentialPath.toString());
      Path certificatePath = credentialPath.resolve(Paths.get("signcerts",
              "Admin@org2.example.com-cert.pem"));
      System.out.println("certificatePem: " + certificatePath.toString());
      Path privateKeyPath = credentialPath.resolve(Paths.get("keystore", "71249aa8f44c8259e89763e7bf9dc472393cb7ad5d7d428314b470c1b908ec39_sk"));


      String identityLabel = "Admin@org2.example.com";
      Identity identity = Identity.createIdentity("Org2MSP", Files.newBufferedReader(certificatePath), Files.newBufferedReader(privateKeyPath));

      wallet.put(identityLabel, identity);


      System.out.println("Write wallet info into " + walletPath.toString() + " successfully.");

    } catch (IOException e) {
      System.err.println("Error adding to wallet");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    addOrg1();
    addOrg2();
  }

}

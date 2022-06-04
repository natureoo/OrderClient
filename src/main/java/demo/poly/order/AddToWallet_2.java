///*
//SPDX-License-Identifier: Apache-2.0
//fabric gateway 2.x
//*/
//
//package demo.poly.order;
//
//import org.hyperledger.fabric.gateway.Wallet;
//import org.hyperledger.fabric.protos.msp.Identities;
//
//import java.io.IOException;
//import java.io.Reader;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.InvalidKeyException;
//import java.security.PrivateKey;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//
//public class AddToWallet {
//
//  private static X509Certificate readX509Certificate(final Path certificatePath) throws IOException, CertificateException {
//    try (Reader certificateReader = Files.newBufferedReader(certificatePath, StandardCharsets.UTF_8)) {
//      return Identities.readX509Certificate(certificateReader);
//    }
//  }
//
//  private static PrivateKey getPrivateKey(final Path privateKeyPath) throws IOException, InvalidKeyException {
//    try (Reader privateKeyReader = Files.newBufferedReader(privateKeyPath, StandardCharsets.UTF_8)) {
//      return Identities.readPrivateKey(privateKeyReader);
//    }
//  }
//
//  public static void addOrg1(){
//    try {
//      // A wallet stores a collection of identities
//      Path walletPath = Paths.get("src/main/resources/wallet");
//      Wallet wallet = Wallets.newFileSystemWallet(walletPath);
//
//      Path credentialPath = Paths.get("src/main/resources/crypto-config/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp");
//      System.out.println("credentialPath: " + credentialPath.toString());
//      Path certificatePath = credentialPath.resolve(Paths.get("signcerts",
//              "Admin@org1.example.com-cert.pem"));
//      System.out.println("certificatePem: " + certificatePath.toString());
//      Path privateKeyPath = credentialPath.resolve(Paths.get("keystore", "b8d1a75f1ea66d20ca0f40d46e7bfd656484edd650d8e91cb76a810e531b69f1_sk"));
////      Path privateKeyPath = credentialPath.resolve(Paths.get("keystore", "0d00160f15bed75591f4f440352df7d40090042192afdee5f33a034924b4f3a9_sk"));
//
//      X509Certificate certificate = readX509Certificate(certificatePath);
//      PrivateKey privateKey = getPrivateKey(privateKeyPath);
//
//      Identity identity = Identities.newX509Identity("Org1MSP", certificate, privateKey);
//
//
//      String identityLabel = "Admin@org1.example.com";
//      wallet.put(identityLabel, identity);
//
//      System.out.println("Write wallet info into " + walletPath.toString() + " successfully.");
//
//    } catch (IOException | CertificateException | InvalidKeyException e) {
//      System.err.println("Error adding to wallet");
//      e.printStackTrace();
//    }
//  }
//
//  public static void addOrg2(){
//    try {
//      // A wallet stores a collection of identities
//      Path walletPath = Paths.get("src/main/resources/wallet");
//      Wallet wallet = Wallets.newFileSystemWallet(walletPath);
//
//      Path credentialPath = Paths.get("src/main/resources/crypto-config/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp");
//      System.out.println("credentialPath: " + credentialPath.toString());
//      Path certificatePath = credentialPath.resolve(Paths.get("signcerts",
//              "Admin@org2.example.com-cert.pem"));
//      System.out.println("certificatePem: " + certificatePath.toString());
//      Path privateKeyPath = credentialPath.resolve(Paths.get("keystore", "71249aa8f44c8259e89763e7bf9dc472393cb7ad5d7d428314b470c1b908ec39_sk"));
////      Path privateKeyPath = credentialPath.resolve(Paths.get("keystore", "0d00160f15bed75591f4f440352df7d40090042192afdee5f33a034924b4f3a9_sk"));
//
//      X509Certificate certificate = readX509Certificate(certificatePath);
//      PrivateKey privateKey = getPrivateKey(privateKeyPath);
//
//      Identity identity = Identities.newX509Identity("Org2MSP", certificate, privateKey);
//
//
//      String identityLabel = "Admin@org2.example.com";
//      wallet.put(identityLabel, identity);
//
//      System.out.println("Write wallet info into " + walletPath.toString() + " successfully.");
//
//    } catch (IOException | CertificateException | InvalidKeyException e) {
//      System.err.println("Error adding to wallet");
//      e.printStackTrace();
//    }
//  }
//
//  public static void main(String[] args) {
//    addOrg2();;
//  }
//
//}

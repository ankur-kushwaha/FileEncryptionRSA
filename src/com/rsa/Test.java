package com.rsa;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class Test {
	public static void main(String[] args) throws IOException {
		RSAEncryptionDescryption rsaObj = new RSAEncryptionDescryption();

		// Encrypt Data using Public Key
		byte[] encryptedData = rsaObj.encryptData("Anuj Patel - Classified Information !");
        //String str="[B@11d7fff";
        String s1 = Base64.getEncoder().encodeToString(encryptedData);
        System.out.println(s1);
		//String s1="e7Haxf7R/SG22ip7hapvYetbMjKs0k8fpYDedRfDKB5WznFUSrEcBG/D9WGVPSNeo07WM7wg7zumx96/T25Kj0p9djr+VKDWPv4f1xBbhPjtpwvyZM9oop5FdL2PCSmq+/b+vCMtQ+EfnfACxc1RyoR7OREhnhS4UO0Ptuh7LqW1ECxVP8iYgwUy14xul7WaWDCMHd1MM3Gsg74x4qHS//25Ai6yRMl+V2OFvpDxOnM3X8A+xQq5nIQdF7dlvsPOtT3PnMdAF5grS3YJES07MZVnq4IkEJG4yfpe6z8zaogx7/+moH6WUlvRO7TjXX5E//g0PwLceEEonmrlAweZIg==";

		byte[] e=Base64.getDecoder().decode(s1);
		//byte[] dec = Base64.getDecoder().decode(str);
		//byte[] utf8 = dcipher.doFinal(dec);
		
		//Descypt Data using Private Key
		rsaObj.decryptData(e);
	}
}

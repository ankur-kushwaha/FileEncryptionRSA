package com.rsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;


public class MainProgram {
	
	RSAEncryptionDescryption rsaObj = new RSAEncryptionDescryption();

	public static void main(String[] args) throws Exception {
		
		
		MainProgram x=new MainProgram();
		//create keys
		//x.rsaObj.createKeys()
		//x.encryptFile("pom.xml");
		x.decryptFile("pom.xml");
		//System.out.println(ef);
	}
	
	public void decryptFile(String fileName) throws Exception{
		List<String> l=readFile(fileName+".e");
		/*String line="FQL5WJE6DJdfmHiFZkTjXlfU4uRYmOSJfvUHW6BqkIiyhEAvgsJNeqM03xB68zGEhymCTaENZNrCxLUGHlVlc9QUpj/4gCPyzdVRh9ePuKejRINGOeeW2DS4uMIOZn+6Prw/g3cDpSDO2abk4oXdBaa28gWnS9KK1e2EMsUVont8e9FCodSJMyehofLdKis7T3IdWTuQmiGpX+oUeekxK3DsyEVlzcVsF4Wvix3U3PbP9l4x2umei6p4k9SXf8pbisdrRIEaGIeRFB+AZvtt89HmMMcnVzwLqVG3C8Xez/7N5K+l7OB6WvhtHxxEBJemRbVNrHP2jd9LFqlvxIgHgw==";
		byte[] ba=Base64.getDecoder().decode(line);
		String s=rsaObj.decryptData(ba);
System.out.println(s);*/
		List<String> dl=new ArrayList<>();
		for (String line : l) {
			//System.out.println(line);
			byte[] ba=Base64.getDecoder().decode(line);
			String s=rsaObj.decryptData(ba);
			//System.out.println(s);
			
			dl.add(new String(hexToBytes(s)));
		}
		writeFile(dl, fileName, "d");
	}
	
	public void encryptFile(String  fileName) throws Exception{
		//why 244
		//http://stackoverflow.com/questions/10007147/getting-a-illegalblocksizeexception-data-must-not-be-longer-than-256-bytes-when
				
		byte[][] t=toByteArray(new File(fileName),100);
		List<String> lines=new ArrayList<>();
		for (byte[] bs : t) {
			String es=encryptString(bs);
			lines.add(es);
		}
		writeFile(lines,fileName,"e");
	}
	
	
	private void writeFile(List<String> lines,String fileName,String type) throws Exception{
		FileWriter fw = new FileWriter(new File(fileName+"."+type));
			
		BufferedWriter bw = new BufferedWriter(fw);
		for (String string : lines) {
			bw.write(string);
			if(!type.equals("d"))
			bw.newLine();
		}
		bw.close();
	}
	
	private List<String> readFile(String fileName) throws Exception{
		String sCurrentLine;

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		List<String> l=new ArrayList<>();
		while ((sCurrentLine = br.readLine()) != null) {
			//System.out.println(sCurrentLine);
			l.add(sCurrentLine);
		}
		return l;
	}
	
	private String encryptString(byte[] bs) throws IOException {
		// Encrypt Data using Public Key
		byte[] encryptedData = rsaObj.encryptData(bytesToHex(bs));
        //String str="[B@11d7fff";
        String s1 = Base64.getEncoder().encodeToString(encryptedData);
        return s1;
	}
	
	public static String bytesToHex(byte[] in) {
		final StringBuilder builder = new StringBuilder();
	    for(byte b : in) {
	        builder.append(String.format("%02x", b));
	    }
	    //System.out.println(builder.toString());
	    return builder.toString();
	}
	public byte[] hexToBytes(String hexString) {
		int l=hexString.length()/2;
		byte[] b=new byte[l];
		for (int i = 0; i < b.length; i++) {
			String hex=hexString.substring(i*2, i*2+2);
		//	System.out.println(hex);
			b[i]=(byte)Integer.parseInt(hex, 16);
		}
		//System.out.println(new String(b));
		//System.exit(0);
		
		return b;
	}
	


	public static String convert(int n) {
		  return Integer.toHexString(n);
	}
	
	public byte[][] toByteArray(File file, int byteBlockSize) throws IOException {

	    InputStream in = new FileInputStream(file);
	    long noOfBlocks = (long) Math.ceil((double)file.length() / (double)byteBlockSize);
	    byte[][] result = new byte[(int)noOfBlocks][byteBlockSize];
	    int offset = 0;
	    for(int i = 0; i < result.length; i++) {
	        result[i] = readByteBlock(in, offset, byteBlockSize);
	    }
	    return result;
	}
	
	private byte[] readByteBlock(InputStream in, int offset, int noBytes) throws IOException {
	    byte[] result = new byte[noBytes];
	    in.read(result, offset, noBytes);
	    return result;
	}
}

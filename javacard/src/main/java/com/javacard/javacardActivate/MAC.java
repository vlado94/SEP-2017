package com.javacard.javacardActivate;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class MAC {
	

	
	    private static final int MAC_LENGTH = 4;
		private Cipher cipher;

	    public void Mac(SecretKey key)
	            throws Exception {
	        cipher = Cipher.getInstance("DES/CBC/NoPadding");
	        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0}));
	    }

	    private byte[] pad(byte[] msg, int offset, int length, int blockLength)  {
	        // Add 1 to add 0x80 at the end.
	        int paddedLength = length + 1;
	        int numBlocks = (int) (paddedLength / blockLength);
	        int remBytes = paddedLength - (numBlocks * blockLength);
	        if (remBytes > 0) {
	            numBlocks++;
	        }
	        byte[] paddedMsg = new byte[numBlocks * blockLength];
	        System.arraycopy(msg, offset, paddedMsg, 0, length);
	        paddedMsg[length] = (byte) 0x80;
	        // Fill message with zeroes to fit blocks
	        for (int i = (length + 1); i < paddedMsg.length; i++) {
	            paddedMsg[i] = (byte) 0x00;
	        }
	        return paddedMsg;
	    }
	    
	    boolean checkMAC(byte[] buffer,int offset) throws Exception {
	        // Generate the MAC for the response
	        byte[] paddedMsg = pad(buffer, 0,offset, cipher.getBlockSize());
	        byte[] encryptedMsg = cipher.doFinal(paddedMsg);
	        byte[] hostMAC =  new byte[MAC_LENGTH];
	        System.arraycopy(encryptedMsg, encryptedMsg.length - MAC_LENGTH, hostMAC, 0, MAC_LENGTH);
	        byte[] cardMAC = new byte[MAC_LENGTH];
	        System.arraycopy(buffer, offset, cardMAC, 0, MAC_LENGTH);
	        // Verify message signature
	        return Arrays.equals(hostMAC, cardMAC);
	    }

	    protected short generateMAC(byte[] buffer, int offset)
	            throws Exception {
	        // Sign request message and append the MAC to the request message
	        byte[] paddedMsg = pad(buffer, 0, offset, cipher.getBlockSize());
	        byte[] encryptedMsg = cipher.doFinal(paddedMsg);
	        System.arraycopy(encryptedMsg, encryptedMsg.length - MAC_LENGTH, buffer, offset, MAC_LENGTH);
	        return (short) (offset + MAC_LENGTH);
	    }
	    

}

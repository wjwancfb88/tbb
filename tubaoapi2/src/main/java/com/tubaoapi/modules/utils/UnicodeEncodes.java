package com.tubaoapi.modules.utils;

import java.io.*;

public class UnicodeEncodes {
	static char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	
	public static String encodeUTF16BEWithoutSplit(String s) throws UnsupportedEncodingException {
		return bytesToString(s.getBytes("UTF-16BE"),true);
	}

	public static String encodeUTF16BE(String s) throws UnsupportedEncodingException {
		return bytesToString(s.getBytes("UTF-16BE"));
	}
	
	public static String encodeUTF16LE(String s) throws UnsupportedEncodingException {
		return bytesToString(s.getBytes("UTF-16LE"));
	}
	
	public static String encodeUTF8(String s) throws UnsupportedEncodingException {
		return bytesToString(s.getBytes("UTF-8"));
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//System.out.println(encodeUTF8("8"));
		//System.out.println(encodeUTF16BE("3我𧄒"));
		//System.out.println(decodeUTF16BE(encodeUTF16BE("3")));
		//System.out.println(encodeUTF16BE("我𧄒"));
		//System.out.println(encodeUTF16BE("𧄒"));
		//System.out.println(encodeUTF8("3"));
		//System.out.println(decodeUTF16BE("\uFFFD"));
		

	}
	
	private static String bytesToString(byte[] bs,boolean withoutSplit) {
		StringBuilder sb = new StringBuilder();
		int i =0;
		for (byte b : bs) {
			if(withoutSplit==false){
				if(i%2==0){
					sb.append("\\u");
				}
			}
			sb.append(byteToHex(b));
			i++;
		}
		return sb.toString();
	}

	private static String bytesToString(byte[] bs) {
		return bytesToString(bs,false);
	}

	private static String byteToHex(byte b) {
		char[] a = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
		return new String(a);
	}
	
  public static String decodeUTF16BE(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
 
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }


	private static void charsToString(char[] cs) {
		StringBuilder sb = new StringBuilder();
		for (char c : cs) {
			sb.append(charToHex(c));
		}
	}

	private static String charToHex(char c) {
		byte hi = (byte) (c >>> 8);
		byte lo = (byte) (c & 0xff);
		return byteToHex(hi) + byteToHex(lo);
	}
	
}

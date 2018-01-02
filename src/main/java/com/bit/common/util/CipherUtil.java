/**
 * 2016年6月9日
 * @author Zhou Liang
 */
package com.bit.common.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class CipherUtil {
	
	public static String hash(String passsword, String privateSalt, boolean generatePublicSalt, String algorithmName, int hashIterations, boolean storedCredentialsHexEncoded){
		//默认算法SHA-512
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        if(algorithmName != null)
        	hashService.setHashAlgorithmName(algorithmName);
        //私盐，默认无
        hashService.setPrivateSalt( new SimpleByteSource("scm") );
        if(privateSalt != null)
        	hashService.setPrivateSalt( new SimpleByteSource(privateSalt) );
        //是否生成公盐，默认false
        if(generatePublicSalt){
	        hashService.setGeneratePublicSalt(generatePublicSalt);
	        //用于生成公盐，默认就这个
	        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        }
        //生成Hash值的迭代次数
        hashService.setHashIterations(hashIterations);
        HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5").
                setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("ZL")).
                setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        if( !storedCredentialsHexEncoded )
        	hex = hashService.computeHash(request).toBase64();
        return hex;
	}
	
	public static String simpleHash(String algorithmName, Object source, Object salt, int hashIterations, boolean storedCredentialsHexEncoded){
		Hash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
		if(storedCredentialsHexEncoded)
			return simpleHash.toHex();
		else
			return simpleHash.toBase64();
	}

}

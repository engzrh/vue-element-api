package time.no.see.encry;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class EncryHelper {

	  private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /**
     * 
     * @param origin	加密的字符串
     * @param key 盐
     * @param algorithmName	算法名
     * @param hashIterations 加密次數
     * @return
     */
	  
	  
    public static String encryptPassword(String origin,String salt) {
    	DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
    	hashService.setHashAlgorithmName("SHA-512");
    	hashService.setPrivateSalt(new SimpleByteSource("salt")); //私盐，默认无
		/*
		 * hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
		 * hashService.setRandomNumberGenerator(new
		 * SecureRandomNumberGenerator());//用于生成公盐。默认就这个
		 */    	
    	 hashService.setHashIterations(2); //生成Hash值的迭代次数
		 HashRequest request = new HashRequest.Builder()
    	            .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes(origin))
    	            .setSalt(ByteSource.Util.bytes(salt)).setIterations(2).build();
    	String hex = hashService.computeHash(request).toHex();
		/*
		 * String hex = hashService.computeHash(request).toHex(); String encryValue =
		 * new SimpleHash( algorithmName, origin, ByteSource.Util.bytes(key),
		 * hashIterations).toHex();
		 */
    	return hex;
    }
    
    
	
}

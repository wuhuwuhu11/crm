package com.bjpowernode.crm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 不可逆的加密算法
 * 		明文的数据加密后生成密文,无法转换明文数据
 * 		但凡说能够破译MD5,这种方式,都是将MD5的各种加密运算的数据,存入到数据库中,进行匹配
 */
public class MD5Util {
	
	public static String getMD5(String password) {
		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}

			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	
}

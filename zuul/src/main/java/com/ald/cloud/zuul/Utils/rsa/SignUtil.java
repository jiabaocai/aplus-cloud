//package com.ald.cloud.zuul.Utils.rsa;
//
//
//import com.ald.cloud.zuul.Utils.StringUtil;
//import com.ald.cloud.zuul.exceptions.BussinessException;
//import com.alibaba.fastjson.annotation.JSONField;
//import org.apache.log4j.Logger;
//import tool.util.ReflectUtil;
//
//import java.lang.reflect.Field;
//import java.security.KeyFactory;
//import java.security.PrivateKey;
//import java.security.Signature;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Map;
//
///**
// * ras生成签名和验签的工具类
// * @author wj
// * @version 1.0
// * @date 2015年12月1日 上午10:41:40
// * Copyright 杭州融都科技股份有限公司 统一资金接入系统 UFX  All Rights Reserved
// * 官方网站：www.erongdu.com
// * 研发中心：rdc@erongdu.com
// * 未经授权不得进行修改、复制、出售及商业使用
// */
//public class SignUtil {
//
//	private static final Logger logger = Logger.getLogger(SignUtil.class);
//
//	/**
//	 * 待加密关键参数
//	 */
//	public static final HashSet<String> encryptId = new HashSet<String>();
//
//	static {
//		encryptId.add("realName");
//		encryptId.add("idNo");
//		encryptId.add("phone");
//		encryptId.add("email");
//		encryptId.add("cardNo");
//	}
//
//	/**
//	 * 生成签名的方法,必须为utf-8格式
//	 * @param str  签名明文字符串
//	 * @param privateKey  私钥
//	 * @return
//	 */
//	public static String subsign(String str,String privateKey){
//		logger.debug("签名明文："+str);
//		logger.debug("签名私钥："+privateKey);
//		String signature = null;
//		try {
//			byte[] prikeybytes = RSAUtil.keyToBytes(privateKey, RSAUtil.KEY_GEN_STYLE);
//			// 构造PKCS8EncodedKeySpec对象
//			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(prikeybytes);
//			// 指定的加密算法
//			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//			// 取私钥匙对象
//			PrivateKey privatekey = keyFactory.generatePrivate(pkcs8KeySpec);
//			Signature instance = Signature.getInstance("SHA1withRSA");
//			instance.initSign(privatekey);
//			byte[] digest = str.getBytes("UTF-8");
//			instance.update(digest);
//			byte[] sign = instance.sign();
//			signature = RSAUtil.byteArr2HexString(sign);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//
//		logger.debug("签名参数sign："+signature);
//		return signature;
//	}
//
//	/**
//	 * 校验签名参数,必须为utf-8格式
//	 * @param str  签名明文
//	 * @param sign  签名参数
//	 * @param pubKey 公钥
//	 * @return 返回true和false，true代表验签通过，false代表验签失败
//	 */
//	public static boolean checkSign(String str,String sign,String pubKey){
//		logger.info("验签明文：" + StringUtil.getLoggerStr(str));
//		logger.info("签名参数sign：" + StringUtil.getLoggerStr(sign));
//		boolean flag = false;
//		try {
//			Signature instance = Signature.getInstance("SHA1withRSA");
//			instance.initVerify(RSAUtil.genPublicKey(pubKey, RSAUtil.KEY_GEN_STYLE));
//			byte[] digest = str.getBytes("UTF-8");
//			instance.update(digest);
//			flag = instance.verify(RSAUtil.hexString2ByteArr(sign));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		logger.info("签名结果："+flag);
//		return flag;
//	}
//
//
//	/**
//	 * 回调签名
//	 * @param responseParamNames
//	 * @param privateKey
//	 * @param map
//	 * @return
//	 */
//	public static Map<String, Object> signResp(String[] responseParamNames,String privateKey, Map<String, Object> map){
//		if (null==responseParamNames||responseParamNames.length==0) {
//			return null;
//		}
//		Arrays.sort(responseParamNames);
//		String signData = getRespSignData(responseParamNames, privateKey, map);
//		try {
//			String sign = subsign(signData, privateKey);
//			logger.info("回调签名，RSA签名[content = " + signData + "] 结果：" + sign);
//			map.put("signInfo", sign);
//		} catch (Exception e) {
//			logger.error("回调的签名，RSA签名[content = " + signData + "]时发生异常！", e);
//		}
//		return map;
//	}
//
//	@JSONField(serialize=false)
//	public static String getRespSignData(String[] paramNames,String privateKey,Map<String, Object> map) {
//		StringBuffer signData = new StringBuffer();
//		if (paramNames==null) {
//			return "";
//		}
//		for (int i = 0; i < paramNames.length; i++) {
//			String name = paramNames[i];
//			Object result = map.get(name);
//			String value = (result == null ? "" : result.toString());
//			signData.append(value);
//			// 处理关键信息加密
//			String priValue = encryptData(privateKey, name, value);
//			if (StringUtil.isNoneBlank(priValue)) {
//				map.put(name, priValue);
//			}
//		}
//		logger.info("签名明文串：" + signData.toString());
//		return signData.toString();
//	}
//
//	/**
//	 * 加密关键信息
//	 * @param privateKeyStr 加密私钥串
//	 * @param name 关键词名称
//	 * @param value 关键词值
//	 */
//	private static String encryptData(String privateKeyStr, String name, String value)  {
//		String priValue = "";
//		if (encryptId.contains(name) && StringUtil.isNotBlank(value)) {
//			try {
//				priValue = RSAUtil.encrypt(privateKeyStr, value);
//			} catch (SecurityException e) {
//				throw new BussinessException("无权访问方法:" + name);
//			} catch (IllegalArgumentException e) {
//				throw new BussinessException("参数不正确:" + value);
//			}
//		}
//		return priValue;
//	}
//
//	//测试方法-签名
//	/**
//	 * 请求时签名
//	 *--测试使用
//	 * @throws Exception
//	 */
//	public static String signReq(Object model,String privateKey,String[] requestParams) {
//		String signData = getReqSignData(model,requestParams,privateKey);
//		String sign = "";
//		try {
//			sign = SignUtil.subsign(signData, privateKey);
//			logger.info("RSA签名[content = " + signData + "] 结果：" + sign);
//		} catch (Exception e) {
//			logger.error("请求时的签名（测试用，正式环境下无此方法），RSA签名[content = " + signData + "]时发生异常！", e);
//		}
//		return sign;
//	}
//
//
//	/**
//	 * --测试使用
//	 * @param model
//	 * @param privateKey
//	 * @return
//	 */
//	@JSONField(serialize=false)
//	public static String getReqSignData(Object model,String[] paramNames,String privateKey) {
//		StringBuffer signData = new StringBuffer();
//		for (int i = 0; i < paramNames.length; i++) {
//			String name = paramNames[i];
//			if ("signInfo".equals(name)) {
//				continue;
//			}
//			Object result = ReflectUtil.invokeGetMethod(model.getClass(), model, name);
//			String value = (result == null ? "" : result.toString());
//			signData.append(value);
//			// 处理关键信息加密
//			encryptData(model, privateKey, name, value);
//		}
//		return signData.toString();
//	}
//
//	/**
//	 * 加密关键信息--测试使用
//	 * @param model
//	 * @param privateKeyStr 加密私钥串
//	 * @param name 关键词名称
//	 * @param value 关键词值
//	 */
//	private static void encryptData(Object model, String privateKeyStr, String name, String value)  {
//		if (SignUtil.encryptId.contains(name) && StringUtil.isNotBlank(value)) {
//
//			Field field = null;
//			try {
//				field = model.getClass().getDeclaredField(name);
//			} catch (NoSuchFieldException e) {
//				try {
//					field = model.getClass().getSuperclass().getDeclaredField(name);
//				} catch (NoSuchFieldException e2) {
//					throw new BussinessException("无效的属性名称：" + name);
//				}
//			}
//
//			field.setAccessible(true);
//			// 加密顺序：RSA私钥加密→Base64编码（Base64编码是为了避免出现非法字符）
//			try {
//				field.set(model, RSAUtil.encrypt(privateKeyStr, value));
//			} catch (IllegalArgumentException e) {
//				throw new BussinessException("参数不正确:" + value);
//			} catch (IllegalAccessException e) {
//				throw new BussinessException("无法访问" + model.getClass().getName() + "类中方法：" + name);
//			}
//		}
//	}
//
//	/**
//	 * 生成签名
//	 * @param params
//	 * @param privateKey
//	 * @param requestParams
//	 * @return
//	 */
//	public static String signReq(Map<String,String> params,String privateKey,String[] requestParams) {
//		String signData = getReqSignData(params,requestParams,privateKey);
//		String sign = "";
//		try {
//			sign = SignUtil.subsign(signData, privateKey);
//			logger.info("RSA签名[content = " + signData + "] 结果：" + sign);
//		} catch (Exception e) {
//			logger.error("请求时的签名（测试用，正式环境下无此方法），RSA签名[content = " + signData + "]时发生异常！", e);
//		}
//		return sign;
//	}
//
//	/**
//	 * 组装签名明文
//	 * @param params
//	 * @param paramNames
//	 * @param privateKey
//	 * @return
//	 */
//	@JSONField(serialize=false)
//	public static String getReqSignData(Map<String,String> params,String[] paramNames,String privateKey) {
//		StringBuffer signData = new StringBuffer();
//		for (int i = 0; i < paramNames.length; i++) {
//			String name = paramNames[i];
//			if ("signInfo".equals(name)) {
//				continue;
//			}
//			String result = params.get(name);
//			String value = (result == null ? "" : result.toString());
//			signData.append(value);
//			// 处理关键信息加密
//			encryptData(params, privateKey, name, value);
//		}
//		return signData.toString();
//	}
//
//	/**
//	 * 加密签名中的关键信息
//	 * @param params
//	 * @param privateKeyStr
//	 * @param name
//	 * @param value
//	 */
//	private static void encryptData(Map<String,String> params, String privateKeyStr, String name, String value)  {
//		if (SignUtil.encryptId.contains(name) && StringUtil.isNotBlank(value)) {
//			params.put(name, RSAUtil.encrypt(privateKeyStr, value));
//		}
//	}
//}

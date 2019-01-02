//package com.ald.cloud.zuul.Utils;
//
//import org.apache.commons.lang.StringUtils;
//import tool.util.DateUtil;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.util.Collection;
//import java.util.Date;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * 工具类-字符串处理
// *
// * @author xx
// * @version 2.0
// * @since 2014年1月28日
// */
//public final class StringUtil extends tool.util.StringUtil {
//
//	/**
//	 * 构造函数
//	 */
//	private StringUtil() {
//
//	}
//
//	/**
//	 * 判断输入的手机号码是否有效
//	 *
//	 * @param str 手机号码
//	 * @return 检验结果（true：有效 false：无效）
//	 */
//	public static boolean isPhone(String str) {
//		String phone = isNull(str);
//		Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
//		Matcher matcher = regex.matcher(phone);
//		boolean isMatched = matcher.matches();
//		return isMatched;
//	}
//
//	/**
//	 * 判断邮箱是否有效
//	 * @param str 邮箱
//	 * @return 检验结果（true：有效 false：无效）
//	 */
//	public static boolean isMail(String str) {
//		String mail = isNull(str);
//		Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
//		Matcher matcher = regex.matcher(mail);
//		boolean isMatched = matcher.matches();
//		return isMatched;
//	}
//
//	/**
//	 * 判断输入的身份证号码是否有效
//	 *
//	 * @param str 身份证号码
//	 * @return 检验结果（true：有效 false：无效）
//	 */
//	public static boolean isCard(String str) {
//		String cardId = isNull(str);
//		// 身份证正则表达式(15位)
//		Pattern isIDCard1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
//		// 身份证正则表达式(18位)
//		Pattern isIDCard2 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
//		Matcher matcher1 = isIDCard1.matcher(cardId);
//		Matcher matcher2 = isIDCard2.matcher(cardId);
//		boolean isMatched = matcher1.matches() || matcher2.matches();
//		return isMatched;
//	}
//
//    public static String toString(String separate,int...objs){
//        StringBuilder sb=new StringBuilder();
//        for(int i=0;i<objs.length;i++){
//            if(i>0)sb.append(separate);
//            sb.append(objs[i]);
//        }
//        return sb.toString();
//    }
//
//
//	public static String toStringArray(Object... list){
//		StringBuilder sb=new StringBuilder();
//		int index=0;
//
//		for (Object o : list) {
//			if(index>0)sb.append(",");
//			sb.append(o.toString());
//			index++;
//		}
//		return sb.toString();
//	}
//
//	public static String toString(Collection list){
//		return toString(list,",");
//	}
//    public static String toString(Collection list,String delim){
//        StringBuilder sb=new StringBuilder();
//		int index=0;
//
//		for (Object o : list) {
//			if(index>0)sb.append(delim);
//			sb.append(o.toString());
//			index++;
//		}
//        return sb.toString();
//    }
//
//    public static String getRelativePath(File file,File parentFile){
//        return file.getAbsolutePath().replaceFirst("^\\Q"+parentFile.getAbsolutePath()+"\\E","").replace("\\","/");
//    }
//    public static String getFileUri(HttpServletRequest request, File file){
//        String pre=request.getRealPath("/");
//        String fullpath=file.getAbsolutePath();
//        return fullpath.replace(pre.replaceFirst("[\\\\/]$",""),"").replace("\\","/");
//    };
//
//    public static String getRepairedFileUri(String fullpath){
//        return fullpath.replaceFirst("[\\\\/]$","").replace("\\","/").replace("//","/");
//    };
//
//
//    /**
//     * 根据身份证号码获取年龄
//     * @param idNo
//     * @return
//     */
//    public static int getAge(String idNo){
//    	if (isBlank(idNo)) {
//    		return 0;
//    	}
//		int currYear = DateUtil.getTimeYear(DateUtil.getNow());
//		int currMonth = DateUtil.getTimeMonth(DateUtil.getNow());
//		int currDay = DateUtil.getTimeDay(DateUtil.getNow());
//
//		String idNoDateStr = "";
//		if (idNo.length() == 18) {
//			idNoDateStr = idNo.substring(6, 14);
//		} else if (idNo.length() == 15) {
//			idNoDateStr = "19" + idNo.substring(6, 12);
//		} else {
//			return 0;
//		}
//		Date idNoDate = DateUtil.valueOf(idNoDateStr,"yyyyMMdd");
//		int idNoYear = DateUtil.getTimeYear(idNoDate);
//		int idNoMonth = DateUtil.getTimeMonth(idNoDate);
//		int idNoDay = DateUtil.getTimeDay(idNoDate);
//		int age = currYear - idNoYear;
//		if(currMonth < idNoMonth){
//			age--;
//		}else if(currMonth == idNoMonth && currDay<idNoDay){
//			age--;
//		}
//        return age;
//	}
//
//    /**
//     * logger字符串截取, 默认输出100字符长度
//     * @param str
//     * @return
//     */
//    public static String getLoggerStr(String str) {
//    	return getLoggerStr(str, 100);
//    }
//
//    /**
//     * logger字符串截取
//     * @param str
//     * @param length
//     * @return
//     */
//    public static String getLoggerStr(String str, int length) {
//    	if (isBlank(str)) {
//    		return "";
//    	}
//
//    	int strLength = str.length();
//    	if (strLength < length) {
//    		return str;
//    	}
//
//		int l2 = length / 2;
//		StringBuffer sb = new StringBuffer(length);
//		sb.append(str.substring(0, l2));
//		sb.append("...");
//		sb.append(str.substring((strLength-l2), strLength));
//		return sb.toString();
//    }
//
//	public static boolean isMobile(String phone) {
//		// 判断是否是手机号（国内）
//		String pattern = "^1[3|4|5|7|8][0-9]{9}$";
//        return phone.matches(pattern);
//	}
//}

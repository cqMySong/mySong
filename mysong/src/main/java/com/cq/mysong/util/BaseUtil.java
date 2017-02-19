package com.cq.mysong.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *------------------☆重庆MySong☆------------------------
 * @author ：mySong		@Date : 2017-2-10 
 * @version ：  V1.0
 * @Description :   
 *	 基础常用工具类
 *------------------☆重庆MySong☆------------------------
 */
public abstract class BaseUtil {

	public static boolean isEmpty(Object obj) {
		
		return (obj == null) ||(obj.toString()==null)|| (obj.toString().matches("\\s*"));
	}
	
	/**
	  * 获取map对象里面的所有key
	  */
	 public static List getMapKeys(Map items){
		 List datas = new ArrayList();
		 if(!isEmpty(items)&&items.size()>0){
			 Set key = items.keySet();
			 for (Iterator it = key.iterator(); it.hasNext();) {
				 datas.add(it.next());
			 }
		 }
		 return datas;
	 }
	 
	 /**
	  * 数字转换大写
	  */
	 public static String digitUppercase(String amount){
		 try {
			 if(isEmpty(amount)) return "0.00";
			 
			 Double n = Double.valueOf(amount);
			 String fraction[] = {"角", "分"};
			 String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
			 String unit[][] = {{"元", "万", "亿"},{"", "拾", "佰", "仟"}};
			 String head = n < 0? "负": "";
			 n = Math.abs(n);
			 String s = "";
			 if(amount.indexOf(".")>0){
				int idx = amount.lastIndexOf(".");
				String stfix = amount.substring(idx+1, amount.length());
				for(int i=0;i<stfix.length()&&i<2;i++){
					s += (digit[Integer.parseInt(String.valueOf(stfix.charAt(i)))]+fraction[i]).replaceAll("(零.)+", "");
				}
			 }
			 if(s.length()<1){
			     s = "整";   
			 }
			 
//			 int integerPart = (int)Math.floor(n);
			 BigInteger integerPart = BigInteger.valueOf((long) Math.floor(n));
			 
			 for (int i = 0; i < unit[0].length && integerPart.compareTo(BigInteger.ZERO)==1; i++) {
			     String p ="";
			     for (int j = 0; j < unit[1].length && n > 0; j++) {
			         p = digit[integerPart.mod(BigInteger.TEN).intValue()]+unit[1][j] + p;
			         integerPart = integerPart.divide(BigInteger.TEN);
			     }
			     s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
			 }
			 
			 return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "0.00";
	 }
	
}

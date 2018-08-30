package leetcode;

import java.util.HashMap;
/**
 * 
 * 
	题目描述：分数到小数
	给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。

	如果小数部分为循环小数，则将循环的部分括在括号内。
	
	示例 1:
	
	输入: numerator = 1, denominator = 2
	输出: "0.5"
	示例 2:
	
	输入: numerator = 2, denominator = 1
	输出: "2"
	示例 3:
	
	输入: numerator = 2, denominator = 3
	输出: "0.(6)"
	
	解题思路：总体思想就是商存储，余数*10.然后继续计算。才了很多坑，关于Int异常的问题。正数位为0的负数场景，多位循环的问题。
	
	题目链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal/description/
 * @author woniu
 *
 */
public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
        
		//这里针对于0的场景特殊处理了
		if(numerator == 0) {
			return "0";
		}
		
		//余数
		long remainder = 0;
		//商
		long quotient = 0;
		
		StringBuilder sb = new StringBuilder();
		
		//用于判断符号
		boolean flag = numerator < 0 ^ denominator < 0;
		
		//防止int型溢出，所以转成long处理
		remainder = ((long)numerator) / ((long)denominator);
		quotient = ((long)numerator) % ((long)denominator);
		
		//针对于正数位为0的负数场景做了特殊处理
		if(flag && !(remainder < 0)) {
			sb.append("-");
		}
		sb.append(remainder);
		sb.append(".");
		int index = 1;//用于记录余数的索引位置，当重复余数时，即开始重复，则需要加括号了。
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		while(quotient != 0) {
			
			if(!map.containsKey(quotient)) {
				map.put(quotient, index);
				index ++;
				remainder = (quotient * 10) / denominator;
				quotient = (quotient * 10) % denominator;
				if(flag) {
					sb.append(-1 * remainder);//负数场景
				} else {
					sb.append(remainder);
				}
				
			} else {
				int preIndex = sb.indexOf(".") + map.get(quotient);
				return sb.substring(0, preIndex) + "(" + sb.substring(preIndex) + ")";
			}
		}
		//针对于小数位为空的场景，特殊处理了。
		if(sb.toString().lastIndexOf(".") == sb.toString().length() - 1) {
			return sb.toString().substring(0, sb.toString().lastIndexOf("."));
		}
		return sb.toString();
    }
	
	public static void main(String[] args) {
//		System.out.println(new Solution().fractionToDecimal(1, 333));
//		System.out.println(new Solution().fractionToDecimal(2, 1));
//		System.out.println(new Solution().fractionToDecimal(3, 4));
//		System.out.println(new Solution().fractionToDecimal(4, 333));
//		System.out.println(new Solution().fractionToDecimal(2, 3));
//		System.out.println(new Solution().fractionToDecimal(1, 6));
//		System.out.println(new Solution().fractionToDecimal(-50, 8));
//		System.out.println(new Solution().fractionToDecimal(7, -12));
//		System.out.println(new Solution().fractionToDecimal(-2, -3));
		System.out.println(new FractionToRecurringDecimal().fractionToDecimal(-1, -2147483648));
		System.out.println(new FractionToRecurringDecimal().fractionToDecimal(-2147483648, -1));
	}
}

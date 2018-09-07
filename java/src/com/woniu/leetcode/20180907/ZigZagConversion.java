package leetcode.date_20180907;

/**
 * 
 * 题目描述：Z字形变换 
 * 
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
 * 
 * P A H N A P L S I I G Y I R 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
 * 
 * 实现一个将字符串进行指定行数变换的函数:
 * 
 * string convert(string s, int numRows); 示例 1:
 * 
 * 输入: s = "PAYPALISHIRING", numRows = 3 输出: "PAHNAPLSIIGYIR" 示例 2:
 * 
 * 输入: s = "PAYPALISHIRING", numRows = 4 输出: "PINALSIGYAHRPI" 解释:
 * 
 * P I N A L S I G Y A H R P I
 * 
 * 
 * 解题思路:
 * 
 * 以numRows为3为例说明： 0 4 8 12 1 3 5 7 9 11 13 2 6 10 15
 * 
 * 可以看到0和4的差距为4 = 2 * (numRows - 1) 1和5间的规律与0和4相同，其中3，为5-2*i的位置，即：j - 2 * i
 * 能找到这个规律，这个题就只剩下编码了。
 * 
 * 题目链接：https://leetcode-cn.com/problems/zigzag-conversion/description/
 * 
 * @author woniu
 *
 */
public class ZigZagConversion {
	public String convert(String s, int numRows) {
		// 对于numRows的特例，直接返回原子符串
		if (numRows == 1) {
			return s;
		}
		int step = (2 * (numRows - 1));// 此题的关键，相关的两个字符的步子是多大
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			for (int j = i; j < s.length();) {
				sb.append(s.charAt(j));
				j = j + step;
				if (i != 0 && i != (numRows - 1) && (j - 2 * i) > 0 && (j - 2 * i) < s.length()) {
					sb.append(s.charAt(j - 2 * i));
				}
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		new ZigZagConversion().convert("PAYPALISHIRING", 4);
		System.out.println(new ZigZagConversion().convert("A", 1));
		System.out.println(new ZigZagConversion().convert("AB", 1));
		System.out.println(new ZigZagConversion().convert("ABCD", 5));
	}
}

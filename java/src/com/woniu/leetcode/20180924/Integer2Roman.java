package leetcode.date_20180924;

/**
 * 
 	题目描述：整数转罗马数字
 	
 	罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

	字符          数值
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000
	例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
	
	通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
	
	I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
	X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
	C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
	给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

	示例 5:

	输入: 1994
	输出: "MCMXCIV"
	解释: M = 1000, CM = 900, XC = 90, IV = 4.
	
	题目链接：https://leetcode-cn.com/problems/integer-to-roman/description/
	
 * @author woniu
 *
 */
public class Integer2Roman {
	/**
	 * 
	 *  解题思路：此题通过一种替换的方式来处理的
	 *  1，初始化了初始化了一个1-9的数字，用于个位的计算
	 *  2，然后需要计算当前我们计算到哪一层了，用于后边的字符替换
	 *  3，对于M进行了特殊处理，因为他的计算规则需其他为不一样。
	 * @param num
	 * @return
	 */
	public String intToRoman(int num) {

		String[] nums = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		int count = 1;
		String result = "";
		while (num > 0) {
			int n = num % 10;
			num = num / 10;
			if(count == 4) {
				result = replaceNum(n+"", count) + result;
			} else {
				if(n > 0) {
					result = replaceNum(nums[n - 1], count) + result;
				}
			}
			
			count ++;
		}

		return result;
	}

	public String replaceNum(String num, int count) {

		switch (count) {
		case 1:
			return num;
		case 2:
			return num.replace("X", "C").replace("V", "L").replace("I", "X");
		case 3:
			return num.replace("X", "M").replace("V", "D").replace("I", "C");
		default:
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<Integer.parseInt(num); i++) {
				sb.append("M");
			}
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new Integer2Roman().intToRoman(1994));//"MCMXCIV"
		System.out.println(new Integer2Roman().intToRoman(58));//"LVIII"
		System.out.println(new Integer2Roman().intToRoman(9));// "IX"
		System.out.println(new Integer2Roman().intToRoman(3));// "III"
		System.out.println(new Integer2Roman().intToRoman(500));// "D"
	}
}

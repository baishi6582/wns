package com.woniu.prolem.problem1;

import java.util.Arrays;

public class Problem1 {
	private String[] ss = null;
	public Problem1() {
		ss = new String[]{"woniu"};
	}
	public String[] getSs() {
//		return ss;//使用这种方式，我们希望在得到的是woniu，但是,却得到的非woniu的提示信息。
		return Arrays.copyOf(ss, ss.length);//但是使用这种方式，我们可以正确得到想要数据信息，因此，不要将私有变量外泄，以免引起不必要的变更，导致不可控。
	}
	public void setSs(String[] ss) {
		this.ss = ss;
	}
	
	public static void main(String[] args) {
		Problem1 problem1 = new Problem1();
		String[] testSS = problem1.getSs();
		testSS[0] = "I'm not woniu";
		
		System.out.println(problem1.getSs()[0]);
	}
	
}

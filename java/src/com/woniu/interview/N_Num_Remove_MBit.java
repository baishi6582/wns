package com.test;
/**
 * 
 * 题目描述：n为数，删除其中的m位，使结果最小。
 * 如30451，m=2,结果为041
 * 
 * @author woniu
 *
 */
public class N_Num_Remove_MBit
{
    public int getIndexOfMinNum(String str, int start, int end){
        int index = start;
        char c = str.charAt(start);
        while(start<end){
            if(c > str.charAt(start)){
                c = str.charAt(start);
                index = start;
            }
            start ++;
        }
        return index;
    }
    
    public String getMinNumByN_Remove_M (String str, int m) {
        
        int n = str.length();
        int begin = getIndexOfMinNum(str, 0, n - m);
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(begin));
        for(int i=1; i<(n-m); i++){
            begin = getIndexOfMinNum(str, begin+1, n-(m-sb.length()));
            sb.append(str.charAt(begin));
        }
        return sb.toString();
    }
    
    public static void main(String[] args)
    {
        System.out.println(new N_Num_Remove_MBit().getMinNumByN_Remove_M("30471", 2));
    }
}

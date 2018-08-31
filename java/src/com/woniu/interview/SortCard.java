package com.woniu.interview;

import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * 问题描述：一副从1到n的牌，每次从牌堆顶取一张放桌子上，再取一张放牌堆底，直到手上没牌，最后桌子上的牌是从1到n有序，设计程序，输入n，输出牌堆的顺序数组。
 * 
 * 解题思路：逆序解决这个问题，最后的输出是有序的，所以我们反着处理，1、2、3、4、5，。。。反过来取就是：1,0,2,0,3,.....之后再来填充0的位置。
 * 
 * 链接：https://blog.kaaass.net/archives/902?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io
 * 
 * @author woniu
 *
 */
public class SortCard
{
    public static void main(String[] args)
    {
        sort_card(10);
    }
    
    public static void sort_card(int n)
    {
        int[] array = new int[n];
        int index = 1;//用于表示取拍时的顺序
        boolean next = false; //用于表示是否为放在堆低的牌
        while(index <= n)
        {
            boolean hasZero = false;//表明是否为0,即是否进行了数字的替换
            for(int i=n-1; i>=0; i--)
            {
                if(array[i] == 0)//如果该位置为0,则表明该位置的数字是可以被替换的。
                {
                    hasZero = true;
                    if(!next)
                    {
                        array[i] = index;
                        index++ ;
                    }
                    next = !next;
                }
                
            }
            if(!hasZero)
            {
                return;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}

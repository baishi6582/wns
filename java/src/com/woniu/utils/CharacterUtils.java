package com.woniu.demo.utils;

import java.lang.reflect.Field;

/**
 * 字符提供工具类
 * @author woniu
 * @date 2019/011/08 10:51
 */
public class CharacterUtils {

    /**
     * 处理对象中某些字符转移，如在操作数据库前将%进行转移
     * 适用场景，多个对象都需要进行相同的操作，但是对应中的字段名称又不一致，可以考虑该方式
     * @param obj 需要处理的对象
     * @param symbol 需要替换的字符
     * @throws IllegalAccessException
     */
    public static void replace(Object obj, String symbol) throws IllegalAccessException {
        if (symbol == null || symbol.length() == 0 || null == obj) {
            return;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (null != field.get(obj) && field.getType().equals(String.class)) {
                String regex = field.get(obj).toString();
                regex = regex.replaceAll(symbol, "\\\\" + symbol);
                field.set(obj, regex);
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        Demo demo = new Demo("woniu%");
        replace(demo, "%");
        System.out.println(demo); // Demo{name='woniu\%'}
    }

    static class Demo {
        private String name;

        public Demo(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Demo{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}

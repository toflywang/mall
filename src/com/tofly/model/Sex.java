package com.tofly.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别枚举类
 * @author Wang Hefei
 * Created by lenovo on 2017/3/25.
 */
public enum Sex {

    Boy{
        @Override
        public String getName() {
            return "男";
        }
    },
    GIRL{
        @Override
        public String getName() {
            return "女";
        }
    },
    ALL{
        @Override
        public String getName() {
            return "不限";
        }
    };
    /**
     * 获取名称的抽象方法
     * @return 名称
     */
     public abstract String getName();
     public static List<String> getValues() {
         List<String> list = new ArrayList<String>();
         for (Sex sex : Sex.values()) {
             list.add(sex.getName());
         }
         return list;
     }
}

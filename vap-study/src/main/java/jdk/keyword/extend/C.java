package jdk.keyword.extend;

import com.alibaba.fastjson.JSON;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-06-23 11:27
 */
public class C extends P {

    private int age;

    public static void main(String[] args) {
        C c = new C();
        c.setAge(1);
        P p = c;
        System.out.println(JSON.toJSONString((C)p));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

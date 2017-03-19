package basic.test;

import basic.DI.service.Knight;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/3/15.
 */
public class KnightMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext("context.xml");
        Knight knight=context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }
}

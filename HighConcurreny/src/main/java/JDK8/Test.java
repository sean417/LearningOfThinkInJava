package JDK8;

import java.math.BigDecimal;

/**
 * Created by chenyang on 2017/7/3.
 */
public class Test {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(Double.valueOf("25"));
        BigDecimal b2 = new BigDecimal(Double.valueOf("3"));
        b1=b1.add(b2);
        System.out.println(b1.doubleValue());
    }
}

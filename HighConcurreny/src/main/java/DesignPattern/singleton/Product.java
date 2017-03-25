package DesignPattern.singleton;

/**不变模式：
 * 不变模式天生是段线程友好的，一个对象一旦被创建，
 * 则它内部状态永远不会变。
 * 1.final修饰类，保证无子类
 * 2.私有熟悉，不会被其他对象修改。
 * 3，final修饰属性，保证不会被第二次赋值。
 * Created by chenyang on 2017/3/24.
 */
public final class Product {
    private final String no;
    private final String name;
    private final double price;

    public Product(String no, String name, double price) {
        super();
        this.no = no;
        this.name = name;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

package InitializeAndCleanUp.part9;

/**
 * 由于switch是要在有限的可能集合中进行选择，
 * 因此它跟eunm是最佳组合。
 * Created by chenyang on 2017/1/19.
 */
public class Burrito {
    Spiciness degree;
    public Burrito(Spiciness degree){
        this.degree=degree;
    }
    public void describe(){
        System.out.println("This burrito is ");
        switch (degree){
            case NOT:
                        System.out.println("not spicy at all");
                        break;
            case MILD:
            case MEDIUM:
                        System.out.println("a little hot");
                        break;
            case HOT:
            case FLAMING:
            default:    System.out.println("not spicy at all");
        }
    }

    public static void main(String[] args) {
        Burrito
                plain=new Burrito(Spiciness.NOT),
                greenChile=new Burrito(Spiciness.MEDIUM),
                jalapeno=new Burrito(Spiciness.HOT);
        plain.describe();
        greenChile.describe();
        jalapeno.describe();
    }
}

package Lock;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by chenyang on 2017/2/28.
 *  用无锁的方式实现Vector
 * @param <E> the type parameter
 */
public class LockFreeVector<E> extends AbstractList<E> {


    private static final boolean debug=false;

     /*第一个桶放8个元素,第2个16个，第3个32个，依次下去 30个桶
       Size of the first bucket.sizeof(bucket[i+1])=2*sizeof(bucket[i])
     */

    private static final int FIRST_BUCKET_SIZE=8;

    /*
        最多三十个桶，30个桶将会允许存放8*(2^30-1)个元素。
     */
    private static final int N_BUCKET=30;

    private  AtomicReferenceArray<AtomicReferenceArray<E>> buckets;

    /**
     * The type Write descriptor.
     *
     * @param <E> the type parameter
     */
    static class WriteDescriptor<E> {
        /**
         * The Old v.
         */
        public E oldV;
        /**
         * The New v.
         */
        public E newV;
        /**
         * The Addr.
         */
        public AtomicReferenceArray<E> addr;
        /**
         * The Addr ind.
         */
        public int addr_ind;

        /**
         * Instantiates a new Write descriptor.
         *
         * @param addr     the addr
         * @param addr_ind the addr ind
         * @param oldV     the old v
         * @param newV     the new v
         */
        public WriteDescriptor(AtomicReferenceArray<E> addr,int addr_ind,
                               E oldV,E newV){
            this.addr=addr;
            this.addr_ind=addr_ind;
            this.oldV=oldV;
            this.newV=newV;
        }

        /**
         * Do it.
         */
/*
             set newV
         */
        public void doIt(){
            addr.compareAndSet(addr_ind,oldV,newV);
        }
    }

    /**
     * The type Descriptor.
     *
     * @param <E> the type parameter
     */
    static class Descriptor<E>{
        /**
         * The Size.Descriptor维护一个size作用：用来插入前看看这个bucket是否满了，如何满了申请新的bucket
         */
        public int size;
        /**
         * The Writeop.
         */
        volatile WriteDescriptor<E> writeop;


        /**
         * Instantiates a new Descriptor.
         *
         * @param size    the size
         * @param writeop the writeop
         */
        public Descriptor(int size,WriteDescriptor<E> writeop){
            this.size=size;
            this.writeop=writeop;
        }

        public void completeWrite(){
            WriteDescriptor<E> tmpOp=writeop;
            if(tmpOp!=null){
                tmpOp.doIt();
                writeop=null;
            }
        }
    }
    private AtomicReference<Descriptor<E>> descriptor;

    private static final int zeroNumFirst=Integer.numberOfLeadingZeros(FIRST_BUCKET_SIZE);


    public LockFreeVector() {
        buckets=new AtomicReferenceArray<AtomicReferenceArray<E>>(N_BUCKET);
        buckets.set(0,new AtomicReferenceArray<E>(FIRST_BUCKET_SIZE));
        descriptor=new AtomicReference<Descriptor<E>>(new Descriptor<E>(0,null));
    }

    public void push_back(E e){
        Descriptor<E> desc;
        Descriptor<E> newd;
        do{
            desc=descriptor.get();
            desc.completeWrite();

            int pos=desc.size+FIRST_BUCKET_SIZE;

            int zeroNumPos=Integer.numberOfLeadingZeros(pos);

            //二维数组的索引，哪个桶，通过前导零的差判断二维数组的索引
            int bucketInd=zeroNumFirst-zeroNumPos;
            if(buckets.get(bucketInd)==null){
                //取上个bucket的长度的2倍，作为新bucket的长度。
                int newLen=2*buckets.get(bucketInd-1).length();
                if(debug)
                    System.out.println("New Length is:"+newLen);
                buckets.compareAndSet(bucketInd,null,new AtomicReferenceArray<E>(newLen));
            }

            //一维数组的索引，数据在一维数组的位置。0x80000000表示1，0x80000000>>>zeroNumPos表示1右移zeroNumPos位。
            //^表示异或操作：(0x80000000>>>zeroNumPos)^pos：表示去掉左边第一个1后的值。

            int idx=(0x80000000>>>zeroNumPos)^pos;
            //维护
            newd=new Descriptor<E>(desc.size+1,new WriteDescriptor<E>(
                    buckets.get(bucketInd),idx,null,e
            ));
        }while (!descriptor.compareAndSet(desc,newd));
        descriptor.get().completeWrite();
    }

    public E get(int index) {
        int pos=index+FIRST_BUCKET_SIZE;
        int zeroNumPos=Integer.numberOfLeadingZeros(pos);
        int bucketInd=zeroNumFirst-zeroNumPos;
        int idx=(0x80000000>>>zeroNumPos)^pos;
        return buckets.get(bucketInd).get(idx);
    }

    public int size() {
        return 0;
    }

    public static void main(String[] args) {
        LockFreeVector<String> aa=new LockFreeVector<String>();
        aa.push_back("abc");
        aa.push_back("123");
        aa.push_back("def");
        aa.push_back("456");
    }
}

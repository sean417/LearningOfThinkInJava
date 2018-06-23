package JVMargument;

/*
 @author:   chenyang
 @date  2018/4/7 下午6:17
 动态类的加载是非常隐蔽的，由代码的逻辑控制，不在文件系统中。这样加载的类的情况不明。
-XX:+TraceClassUnloading -XX:+TraceClassLoading 可以跟踪类的加载和卸载的过程。
从日志看出，先加载的事java.lang.Object,最后是Example。
-XX:+TraceClassUnloading -XX:+TraceClassLoading
*/


import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class UnloadClass implements jdk.internal.org.objectweb.asm.Opcodes{
    public static void main(String[] args) throws NoSuchMethodException,SecurityException,
            IllegalAccessException,IllegalArgumentException,InvocationTargetException{
        ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_7,ACC_PUBLIC,"Example",null,"java/lang/Object",null);
        MethodVisitor mw=cw.visitMethod(ACC_PUBLIC,"<init>","()V",null,null);
        mw.visitVarInsn(ALOAD,0);
        mw.visitMethodInsn(INVOKESPECIAL,"java/lang/Object","<init>","()V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0,0);
        mw.visitEnd();
        mw=cw.visitMethod(ACC_PUBLIC+ACC_STATIC,"main","([Ljava/lang/String;)V",null,null);
        mw.visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
        mw.visitLdcInsn("Hello world!");
        mw.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0,0);
        mw.visitEnd();
        byte[] code=cw.toByteArray();
        for(int i=0;i<1;i++){
            ClassLoader loader=UnloadClass.class.getClassLoader();
            Method m=ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            m.setAccessible(true);
            m.invoke(loader,"Example",code,0,code.length);
            m.setAccessible(false);
            System.gc();
        }

    }

}

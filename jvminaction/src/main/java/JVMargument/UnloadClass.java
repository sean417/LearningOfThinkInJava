package JVMargument;

/*
 @author:   chenyang
 @date  2018/4/7 下午6:17

*/


import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.lang.reflect.InvocationTargetException;


public class UnloadClass implements jdk.internal.org.objectweb.asm.Opcodes{
    public static void main(String[] args) throws NoSuchMethodException,SecurityException,
            IllegalAccessException,IllegalArgumentException,InvocationTargetException{
        ClassWriter cw=new ClassWriter(ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_7,ACC_PUBLIC,"<init>","()V",null,null);
        MethodVisitor mw=cw.visitMethod(ACC_PUBLIC,"<init>","()V",null,null);
        mw.visitVarInsn(ALOAD,0);
        mw.visitMethodInsn(INVOKESPECIAL,"java/lang/Object","<init>","()V");
        mw.visitInsn(RETURN);
        mw.visitMaxs(0,0);
        mw.visitEnd();
        cw.visitMethod(ACC_PUBLIC+ACC_STATIC,"main","([Ljava/lang/String;)V",null,null);


    }

}

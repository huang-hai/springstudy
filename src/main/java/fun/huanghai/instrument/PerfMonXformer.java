package fun.huanghai.instrument;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class PerfMonXformer implements ClassFileTransformer{

    /**
     * Transforms the given class file and returns a new replacement class file.
     * This method is invoked when the {@link Module Module} bearing {@link
     * ClassFileTransformer#transform(Module, ClassLoader, String, Class, ProtectionDomain, byte[])
     * transform} is not overridden.
     *
     * @param loader              the defining loader of the class to be transformed,
     *                            may be {@code null} if the bootstrap loader
     * @param className           the name of the class in the internal form of fully
     *                            qualified class and interface names as defined in
     *                            <i>The Java Virtual Machine Specification</i>.
     *                            For example, <code>"java/util/List"</code>.
     * @param classBeingRedefined if this is triggered by a redefine or retransform,
     *                            the class being redefined or retransformed;
     *                            if this is a class load, {@code null}
     * @param protectionDomain    the protection domain of the class being defined or redefined
     * @param classfileBuffer     the input byte buffer in class file format - must not be modified
     * @return a well-formed class file buffer (the result of the transform),
     * or {@code null} if no transform is performed
     * @throws IllegalClassFormatException if the input does not represent a well-formed class file
     * @implSpec The default implementation returns null.
     * @revised 9
     * @spec JPMS
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        System.out.println("Transforming "+className);
        ClassPool pool = ClassPool.getDefault();
        CtClass cl = null;
        try {
            cl = pool.makeClass(new java.io.ByteArrayInputStream(classfileBuffer));
            if(!cl.isInterface()){
                CtBehavior[] methods = cl.getDeclaredBehaviors();
                for (int i = 0; i < methods.length; i++) {
                    if(!methods[i].isEmpty()){
                        //修改字节码
                        doMethod(methods[i]);
                    }
                }
                transformed = cl.toBytecode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(cl!=null){
                cl.detach();
            }
        }
        return transformed;
    }

    private void doMethod(CtBehavior method) throws NotFoundException, CannotCompileException{

        method.insertBefore("long stime = System.nanoTime();");
        method.insertAfter("System.out.println(\"leave \"+method.getName()+\" and time:\"+(System.nanoTime()-stime));");
    }
}

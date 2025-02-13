package MethodHandles;

import org.aspectj.weaver.ast.ITestVisitor;
import org.aspectj.weaver.ast.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Method_Handles {
    /// pour appeler ue methode ou une clasee dynamiquement le but ici c de comprendre seulement que handles est mieux que javareflexion car il est lent et moins securisé c tt
    void sayHello() {
        System.out.println("Hello, world!");
    }
public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(void.class);

        MethodHandle methodHandle = lookup.findVirtual(Method_Handles.class, "sayHello", methodType);

    Method_Handles obj = new Method_Handles() ;
    methodHandle.invoke(obj); // 👈 Appel dynamique avec Method Handles
    }
}


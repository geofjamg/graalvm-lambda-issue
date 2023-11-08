package org.example;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

public class MyLib {

    private MyLib() {
    }

    private static void printMessage(Runnable runnable) {
        runnable.run();
    }

    @CEntryPoint(name = "hello")
    public static void hello(IsolateThread thread, CCharPointer messagePtr) {
        printMessage(() -> {
            String message = CTypeConversion.utf8ToJavaString(messagePtr);
            System.out.println("Say " + message);
        });
    }
}

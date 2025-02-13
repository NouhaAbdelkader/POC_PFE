package JavaReflexion;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Bean {}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Serialized {
    SerializedFormat format() default SerializedFormat.JSON;
}

enum SerializedFormat {
    BINARY, XML, JSON
}

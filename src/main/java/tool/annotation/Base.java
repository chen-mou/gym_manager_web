package tool.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Base {

    enum ParamType {
        JSON,
        Form,
        Query
    }

    String packages() default "";

    String field() default "";

    String prefix() default "";

    String suffix() default "";

    ParamType type();
}

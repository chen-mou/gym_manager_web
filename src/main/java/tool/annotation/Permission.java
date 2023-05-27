package tool.annotation;

import tool.enums.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 陈桢梁
 * @desc Permission.java
 * @date 2023-05-09 01:02
 * @logs[0] 2023-05-09 01:02 陈桢梁 创建了Permsion.java文件
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    Role value() default Role.Login;

}

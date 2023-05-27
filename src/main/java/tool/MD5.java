package tool;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author 陈桢梁
 * @desc MD5.java
 * @date 2023-05-27 14:43
 * @logs[0] 2023-05-27 14:43 陈桢梁 创建了MD5.java文件
 */
public class MD5 {

    public static String salt(String s){
        String timestamp = String.valueOf(new Date().getTime());
        return DigestUtils.md5DigestAsHex((s + timestamp).getBytes(StandardCharsets.UTF_8));

    }

}

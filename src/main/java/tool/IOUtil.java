package tool;

import java.io.IOException;
import java.io.Reader;

/**
 * @author 陈桢梁
 * @desc IOUtil.java
 * @date 2023-05-11 16:26
 * @logs[0] 2023-05-11 16:26 陈桢梁 创建了IOUtil.java文件
 */
public class IOUtil {

    public static String readAll(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buff = new char[1024];
        int i;
        while((i = reader.read(buff)) != -1){
            sb.append(subCharArray(buff, i));
        }
        return sb.toString();
    }

    private static String subCharArray(char[] arr, int end){
        if(end == arr.length){
            return new String(arr);
        }
        return new String(arr).substring(0, end);
    }

}

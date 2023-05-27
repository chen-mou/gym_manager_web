package tool;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 陈桢梁
 * @desc Result.java
 * @date 2023-05-09 00:52
 * @logs[0] 2023-05-09 00:52 陈桢梁 创建了Result.java文件
 */
@Data
@AllArgsConstructor
public class Result {

    private int code;

    private Object data;

    public static Result fail(String msg){
        return new Result(1, msg);
    }

    public static Result success(Object data){
        return new Result(0, data);
    }

}

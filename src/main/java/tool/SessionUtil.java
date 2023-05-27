package tool;

import com.gm.gym_manager_web.entity.UserEntity;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 陈桢梁
 * @desc Session.java
 * @date 2023-05-09 01:08
 * @logs[0] 2023-05-09 01:08 陈桢梁 创建了Session.java文件
 */
public class SessionUtil {

    private static final ThreadLocal<HttpSession> session = new ThreadLocal<>();

    public static HttpSession getSession(){
        return session.get();
    }

    public static void setSession(HttpSession session){
        SessionUtil.session.set(session);
    }

    public static UserEntity getCurrentUser(){
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            throw new RuntimeException("多线程环境不能使用这个方法");
        }
        return (UserEntity) session.getAttribute("user");
    }

}

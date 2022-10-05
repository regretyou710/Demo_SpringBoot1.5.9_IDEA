package tw.com.springboot.exception;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用戶不存在");
    }
}

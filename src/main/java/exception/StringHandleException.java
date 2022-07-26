package exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Kendrick Chen
 * @Date: 2022/07/26/9:56 AM
 * @Mail: KENDRICK.CHEN@OOCL.COM
 */
public class StringHandleException extends RuntimeException{
    private static final String ERROR_MESSAGE = "Calculate Error";

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}

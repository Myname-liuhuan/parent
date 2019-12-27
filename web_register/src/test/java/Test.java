import com.example.java.web.register.utils.EmailUtil;

/**
 * @author 刘欢
 * @Date 2019/12/13
 */
public class Test {

    @org.junit.Test
    public void test1(){
        double d = Math.random();
        System.out.println((int)(10*Math.random()));
    }

    @org.junit.Test
    public void test2(){
        EmailUtil.sendEmail();
    }
}

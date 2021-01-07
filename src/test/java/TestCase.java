import com.zzcedu.blog.dao.ShareDao;
import com.zzcedu.blog.dao.UserDao;
import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.service.NoteServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Evan
 * @Date: 2020/12/29 15:35
 */
public class TestCase {
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = ac.getBean("userDao", UserDao.class);
    }
    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        NoteService noteService = ac.getBean("noteServiceImpl", NoteServiceImpl.class);
        System.out.println(noteService.searchShareNote("1", 1));
    }
}

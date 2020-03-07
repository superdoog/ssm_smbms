import com.lv.pojo.Role;
import com.lv.pojo.User;
import com.lv.service.RoleService;
import com.lv.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void test1(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        RoleService roleServiceImpl = (RoleService) context.getBean("RoleServiceImpl");
        List<Role> roleList = roleServiceImpl.getRoleList();
        for (Role role : roleList) {
            System.out.println(role.getRoleName());
        }
    }

    @Test
    public void test02(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = (UserService) context.getBean("UserServiceImpl");
        List<User> userList = userServiceImpl.getUserList(null, 0, 1, 15);
        for (User user : userList) {
            System.out.println(user.getUserName());
        }
    }

}

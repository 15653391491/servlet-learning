package mybatis.mapper;
import mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
public interface UserDao {
    List<User> getUserList();
    List<User> selectUserByAccount(@Param("user") HashMap<String,Object> user);
    int addAccount(@Param("account") String account,@Param("password1") String passoword1);
}

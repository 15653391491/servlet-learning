package mybatis.mapper;
import mybatis.pojo.User;
import java.util.List;
public interface UserDao {
    List<User> getUserList();
}

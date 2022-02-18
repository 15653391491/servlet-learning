package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;

public class mybatisTest {
    public static void main(String[] args) {
        try {
            String resource = "mybatis/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            try(SqlSession session = sqlSessionFactory.openSession()){
                BlogMapper mapper = session.getMapper(BlogMapper.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

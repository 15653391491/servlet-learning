package mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    protected static SqlSessionFactory sqlSessionFactory;

    private MybatisUtils() {

    }

    static {
        try {
            String resource = "mybatis/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话session
     *
     * @return 会话session
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 关闭会话session
     *
     * @param session 会话session
     */
    public static void closeSqlSession(SqlSession session) {
        session.close();
    }
}

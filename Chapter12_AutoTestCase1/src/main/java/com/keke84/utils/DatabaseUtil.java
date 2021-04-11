package com.keke84.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

public class DatabaseUtil {
    private static SqlSession sqlSession =null;

    public static SqlSession getSqlSession(){
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            sqlSession = sqlSessionFactory.openSession();
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
    public static void closeSession(SqlSession sqlSession){
        if (null!=sqlSession){
            sqlSession.close();
        }
    }
}

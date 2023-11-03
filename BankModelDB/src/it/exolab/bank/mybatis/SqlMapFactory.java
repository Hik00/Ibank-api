package it.exolab.bank.mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapFactory {
    private static SqlMapFactory instance = new SqlMapFactory();
    private final String resource = "datasource.xml";
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    private SqlMapFactory() {
        initializeSqlSessionFactory();
    }

    private void initializeSqlSessionFactory() {
        try (Reader reader = Resources.getResourceAsReader(this.resource)) {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = builder.build(reader);
        } catch (Exception e) {
            throw new RuntimeException("Errore nell'inizializzazione della classe SqlSessionFactory. Causa: " + e);
        }
    }

    public static SqlMapFactory instance() {
        return instance;
    }

    public SqlSession openSession() {
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
        }
        return sqlSession;
    }

    public SqlSession openSessionNoAutoCommit() {
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession(false);
        }
        return sqlSession;
    }

    public void commitSession() {
        if (sqlSession != null) {
            sqlSession.commit();
            closeSession();
        }
    }

    public void closeSession() {
        if (sqlSession != null) {
            sqlSession.close();
            sqlSession = null;
        }
    }

    public void rollbackSession() {
        if (sqlSession != null) {
            sqlSession.rollback();
            closeSession();
        }
    }

    public <T> T getMapper(Class<T> type) {
        if (sqlSession != null) {
            return sqlSession.getMapper(type);
        }
        return null;
    }
}

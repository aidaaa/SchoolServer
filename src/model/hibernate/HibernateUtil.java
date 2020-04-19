package model.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static final SessionFactory session_factory;

    static {
        session_factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static Session getSession() {
        ThreadLocal thread_session = new ThreadLocal();
        Session session = (Session) thread_session.get();

        if (session == null ) {
            session = session_factory.openSession();
            thread_session.set(session);
        }

        return session;
    }
}

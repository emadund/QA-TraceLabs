package test.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import test.db.ModulHibernate;

import java.util.ArrayList;
import java.util.List;

public class HibernateStart {


    public HibernateStart() {
    }

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ModulHibernate.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            List<ModulHibernate> modules= new ArrayList<>();
            session.beginTransaction();
            modules=session.createQuery("from testinputs where type='negative21'").getResultList();
            session.getTransaction().commit();
            System.out.println(modules);
        }
        finally {
            session.close();
            factory.close();
        }
    }

    public static String username (String type) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ModulHibernate.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            List<ModulHibernate> modules = new ArrayList<>();
            session.beginTransaction();
            modules = session.createQuery("from testinputs where type='negative21'").getResultList();
            session.getTransaction().commit();
            String result = modules.get(0).getUsername();
            System.out.println(modules);
            return result;
        } finally {
            session.close();
            factory.close();
        }
    }

        public static String password (String type) {
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(ModulHibernate.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession();
            try {
                List<ModulHibernate> modules = new ArrayList<>();
                session.beginTransaction();
                modules = session.createQuery("from testinputs where type='negative21'").getResultList();
                session.getTransaction().commit();
                String result = modules.get(0).getPassword();
                System.out.println(modules);
                return result;
            } finally {
                session.close();
                factory.close();
            }

        }
        public static String email (String type) {
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(ModulHibernate.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession();
            try {
                List<ModulHibernate> modules = new ArrayList<>();
                session.beginTransaction();
                modules = session.createQuery("from testinputs where type='negative21'").getResultList();
                session.getTransaction().commit();
                String result = modules.get(0).getEmail();
                System.out.println(modules);
                return result;
            } finally {
                session.close();
                factory.close();
            }
    }
}

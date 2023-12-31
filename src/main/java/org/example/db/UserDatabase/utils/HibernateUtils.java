package org.example.db.UserDatabase.utils;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.dbEntities.StatsEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    private HibernateUtils() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(UserEntity.class);
                configuration.addAnnotatedClass(SongEntity.class);
                configuration.addAnnotatedClass(PlayListEntity.class);
                configuration.addAnnotatedClass(StatsEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

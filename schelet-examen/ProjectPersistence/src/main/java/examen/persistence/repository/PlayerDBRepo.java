package examen.persistence.repository;

import examen.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class PlayerDBRepo implements IPlayerRepo {
    private static SessionFactory sessionFactory;

    public PlayerDBRepo() {
        System.out.println("constructor");
        initialize();
    }




    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    public static void main(String ... arg) {
        initialize();

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new Player("gww", "aaa"));
            session.getTransaction().commit();
        }


        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Player> result = session.createQuery("from Player", Player.class).list();
            for (Player player :  result) {
                System.out.println(player);
            }
            Player player = (Player)session.createQuery("from Player where id = ?1")
                    .setParameter(1, 2L).setMaxResults(1)
                    .uniqueResult();
            System.out.println(player);

            PlayerDBRepo db = new PlayerDBRepo();
            player = db.findByUsername("a");

            session.getTransaction().commit();
        }

        close();
    }

    @Override
    public Player findOne(Long aLong) throws IllegalArgumentException{
        Player player = new Player();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                player = session.createQuery("from Player where id = ?1", Player.class)
                        .setParameter(1, aLong)
                        .setMaxResults(1)
                        .uniqueResult();
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
        return player;
    }

    @Override
    public Iterable<Player> findAll() {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Player> result = session.createQuery("from Player", Player.class).getResultList();
            return result;
        }

    }

    @Override
    public Player save(Player entity) {
        return null;
    }

    @Override
    public Player delete(Long aLong) {
        return null;
    }

    @Override
    public Player update(Player entity) {
        return null;
    }

    @Override
    public Player findByUsername(String username) throws IllegalArgumentException{
        Player player = new Player();
        System.out.println("find by username");
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                player = session.createQuery("from Player where username = ?1", Player.class)
                        .setParameter(1, username)
                        .setMaxResults(1)
                        .uniqueResult();
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
        System.out.println("hibernate gasit Player " + player);
        return player;
    }
}
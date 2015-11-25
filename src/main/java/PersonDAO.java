import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/**
 * Created by Matexo on 2015-11-15.
 */
public class PersonDAO implements CRUDInterface<Person>{
    private SessionFactory sessionFactory;
    private ServiceRegistry serviceRegistry;

    public PersonDAO() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public Long create(Person item)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save( item );
            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return item.getId();
    }

    public Person read(Long id)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Person person = null;
        try {
            transaction = session.beginTransaction();
            person = (Person) session.get(Person.class , id);
            if(person == null) throw new NullPointerException();
            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return person;
    }

    public void update(Long id , Person item)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Person person = (Person) session.get(Person.class , id);
            if(person == null) throw new NullPointerException();
            person.setName(item.getName());
            person.setSurname(item.getSurname());
            person.setAdress(item.getAdress());
            //person.setList(item.getList());
            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Long id)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Person person = (Person) session.get(Person.class , id);
            session.delete(person);
            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}

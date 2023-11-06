package ef.repository.DatabaseRepoImpl;

import ef.model.Writer;
import ef.repository.WriterRepository;
import ef.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class WriterRepoImpl implements WriterRepository {

    @Override
    public Writer save(Writer writer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(writer);
        session.getTransaction().commit();
        return writer;
    }

    @Override
    public Writer getById(Long aLong) {
        Writer writer = HibernateUtil.getSessionFactory().openSession().load(Writer.class, aLong);
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Writer writer1 = session.get(Writer.class,writer.getId());
        session.getTransaction().commit();
        session.clear();
        writer.setPosts(writer1.getPosts());
        writer.setRegion(writer1.getRegion());

        session.beginTransaction();
        session.update(writer);
        session.getTransaction().commit();
        session.close();
        return writer;
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Writer writer = new Writer();
        writer.setId(aLong);
        session.delete(writer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Writer> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Writer> writerList = session.createQuery("FROM Writer").list();
        session.getTransaction().commit();
        return writerList;
    }
}


package ef.repository.DatabaseRepoImpl;

import ef.model.Writer;
import ef.repository.WriterRepository;
import ef.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class WriterRepoImpl implements WriterRepository {

    @Override
    public Writer save(Writer writer) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Writer createdWriter = session.merge(writer);
            session.getTransaction().commit();
            return createdWriter;
        }
    }

    @Override
    public Writer getById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("SELECT w FROM Writer w LEFT JOIN FETCH w.posts LEFT JOIN FETCH w.region WHERE w.id=:id", Writer.class)
                    .setParameter("id",id)
                    .getSingleResult();
        }
    }

    @Override
    public Writer update(Writer writer) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Writer writer1 = session.get(Writer.class, writer.getId());
            session.getTransaction().commit();
            session.clear();
            writer.setPosts(writer1.getPosts());
            writer.setRegion(writer1.getRegion());

            session.beginTransaction();
            Writer updatedWriter = session.merge(writer);
            session.getTransaction().commit();
            return updatedWriter;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Writer writer = new Writer();
            writer.setId(id);
            session.remove(writer);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Writer> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
         //   List<Writer> writerList = session.createQuery("FROM Writer", Writer.class).list();
            List<Writer> writerList = session.createQuery("SELECT w FROM Writer w JOIN FETCH w.posts JOIN FETCH w.region", Writer.class).getResultList();
            session.getTransaction().commit();
            return writerList;
        }
    }
}


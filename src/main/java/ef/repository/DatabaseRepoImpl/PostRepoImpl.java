package ef.repository.DatabaseRepoImpl;

import ef.model.Post;
import ef.repository.PostRepository;
import ef.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

import static java.time.Instant.now;

public class PostRepoImpl implements PostRepository {

    @Override
    public Post save(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        post.setCreated(now().toEpochMilli());
        post.setUpdated(0L);
        session.save(post);
        session.getTransaction().commit();
        return post;
    }

    @Override
    public Post getById(Long aLong) {
        Post post = HibernateUtil.getSessionFactory().openSession().load(Post.class, aLong);
        return post;
    }

    @Override
    public Post update(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        post.setUpdated(now().toEpochMilli());
        post.setCreated(session.load(Post.class,post.getId()).getCreated());
        session.getTransaction().commit();
        session.clear();
        session.beginTransaction();
        session.update(post);
        session.getTransaction().commit();
        session.close();
        return post;
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Post post = new Post();
        post.setId(aLong);
        session.delete(post);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Post> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Post> postList = session.createQuery("FROM Post").list();
        session.getTransaction().commit();
        session.close();
        return postList;
    }
}


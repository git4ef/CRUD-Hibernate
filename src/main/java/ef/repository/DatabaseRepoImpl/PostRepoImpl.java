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
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            post.setCreated(now().toEpochMilli());
            post.setUpdated(0L);
            Post createdPost = session.merge(post);
            session.getTransaction().commit();
            return createdPost;
        }
    }

    @Override
    public Post getById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Post.class, id);
        }
    }

    @Override
    public Post update(Post post) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            post.setUpdated(now().toEpochMilli());
            post.setCreated(session.get(Post.class, post.getId()).getCreated());
            session.getTransaction().commit();
            session.clear();
            session.beginTransaction();
            Post updatedPost = session.merge(post);
            session.getTransaction().commit();
            return updatedPost;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Post post = new Post();
            post.setId(id);
            session.remove(post);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Post> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<Post> postList = session.createQuery("FROM Post",Post.class).getResultList();
            session.getTransaction().commit();
            return postList;
        }
    }
}


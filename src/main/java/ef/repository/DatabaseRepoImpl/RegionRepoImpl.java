package ef.repository.DatabaseRepoImpl;

import ef.util.HibernateUtil;
import ef.model.Region;
import ef.repository.RegionRepository;
import org.hibernate.Session;

import java.util.List;

public class RegionRepoImpl implements RegionRepository {

    @Override
    public Region save(Region region) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Region createdRegion = session.merge(region);
            session.getTransaction().commit();
            return createdRegion;
        }
    }

    @Override
    public Region getById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Region.class, id);
        }
    }

    @Override
    public Region update(Region region) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Region updatedRegion = session.merge(region);
            session.getTransaction().commit();
            return updatedRegion;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Region region = new Region();
            region.setId(id);
            session.remove(region);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Region> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<Region> regionList = session.createQuery("FROM Region",Region.class).getResultList();
            session.getTransaction().commit();
            return regionList;
        }
    }
}


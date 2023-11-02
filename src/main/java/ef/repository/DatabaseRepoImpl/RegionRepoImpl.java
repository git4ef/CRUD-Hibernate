package ef.repository.DatabaseRepoImpl;

import ef.util.HibernateUtil;
import ef.model.Region;
import ef.repository.RegionRepository;
import org.hibernate.Session;

import java.util.List;

public class RegionRepoImpl implements RegionRepository {

    @Override
    public Region save(Region region) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(region);
        session.getTransaction().commit();
        return region;
    }

    @Override
    public Region getById(Long aLong) {
        Region region = HibernateUtil.getSessionFactory().openSession().load(Region.class, aLong);
        return region;
    }

    @Override
    public Region update(Region region) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(region);
        session.getTransaction().commit();
        session.close();
        return region;
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Region region = new Region();
        region.setId(aLong);
        session.delete(region);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Region> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Region> regionList = session.createQuery("FROM Region").list();
        session.getTransaction().commit();
        session.close();
        return regionList;
    }
}


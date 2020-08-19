package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from user");
      return query.getResultList();
   }

   @Override
   public User getUserByNameAndSeries(String name, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().
              createQuery("select u from User u where u.car.name = :name and u.car.series = :series");
              query.setParameter(series, series);
              query.setParameter(name,name);
      return  ((org.hibernate.query.Query<User>) query).uniqueResult();

   }
}

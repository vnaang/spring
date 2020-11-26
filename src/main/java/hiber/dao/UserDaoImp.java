package hiber.dao;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public  void getUserByCar(String model, int series){
      try {
         Session session = sessionFactory.getCurrentSession();
         String HQL = "FROM Car car LEFT OUTER JOIN FETCH car.user WHERE car.model =:carModel AND car.series =:carSeries ";
         Car car = session.createQuery(HQL, Car.class).setParameter("carModel",model).setParameter("carSeries",series).uniqueResult();
         System.out.println(car.getUser().getFirstName());
      }catch (HibernateException exception){
         exception.printStackTrace();
      }
   }
}

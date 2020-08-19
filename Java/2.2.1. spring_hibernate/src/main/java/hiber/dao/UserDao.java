package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserDao {
   void add(User user);
   List<User> listUsers();
   User getUserByNameAndSeries(String name, int series);
}

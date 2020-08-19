package web.Dao;

import web.Model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarDaoImp implements CarDao {

        private List<Car> cars = new ArrayList<Car>();

        public CarDaoImp() {
            Car car = new Car();
            car.setId(1);
            car.setName("LADA");
            car.setSpecialty("Sedan");
            cars.add(car);
            Car car2 = new Car();
            car2.setId(2);
            car2.setName("KAMAZ");
            car2.setSpecialty("Truck");
            cars.add(car2);
            Car car3 = new Car();
            car3.setId(3);
            car3.setName("NIVA");
            car3.setSpecialty("4x4");
            cars.add(car3);
        }

        public void save(Car car) {
            cars.add(car);
        }

        public void delete(Car car) {
            cars.remove(car);
        }

        public List<Car> getAll() {
            return cars;
        }

        public Car getById(Integer id) {
            return cars.get(id);
        }
    }


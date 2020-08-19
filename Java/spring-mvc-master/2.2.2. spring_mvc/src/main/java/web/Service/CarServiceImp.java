package web.Service;

import web.Dao.CarDao;
import web.Dao.CarDaoImp;
import web.Model.Car;

import java.util.List;

public class CarServiceImp implements CarService {

    private CarDao carDao = new CarDaoImp();

    @Override
    public void save(Car car) {
        if(car!=null) {
            List<Car> cars = carDao.getAll();
            if (!cars.isEmpty()) {
                Car lastCar = cars.get(cars.size() - 1);
                car.setId(lastCar.getId() + 1);
                carDao.save(car);
            }
        }
    }

    @Override
    public void delete(Car car) {
        if(car!=null) {
            carDao.delete(car);
        }
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car getById(Integer id) {
        if(id!=null) {
            return carDao.getById(id);
        }
        return null;
    }
}

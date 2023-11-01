package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    private static final List<Car> cars = new ArrayList<>() {{
        add(new Car("Bugatti Type 57SC", "синий", 1934));
        add(new Car("Delahaye 175", "голубой", 1947));
        add(new Car("Jaguar XK150", "зеленый", 1957));
        add(new Car("Mercedes-Benz W100", "черный", 1963));
        add(new Car("Rolls-Royce Phantom VI", "бордовый", 1968));
    }};

    @Override
    public List<Car> getAllCars() {
        return cars;
    }

    @Override
    public List<Car> getCars(int amount) {
        return amount > 0 ? cars.subList(0, Math.min(amount, cars.size())) : new ArrayList<>();
    }
}

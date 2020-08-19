package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.Model.Car;
import web.Service.CarService;
import web.Service.CarServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    private CarService carService = new CarServiceImp();

    @RequestMapping(value="/cars", method= RequestMethod.GET)
    public String getOrderPage(Model model) {
        List<Car> cars = carService.getAll();
        model.addAttribute("carList", cars);
        return "cars";
    }

}

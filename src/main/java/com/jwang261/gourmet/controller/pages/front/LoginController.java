package com.jwang261.gourmet.controller.pages.front;


import com.jwang261.gourmet.pojo.entity.*;
import com.jwang261.gourmet.service.*;
import com.jwang261.gourmet.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pages/front")
public class LoginController{

    @Resource
    CustomerService customerService;

    @Resource
    RestaurantService restaurantService;

    @Resource
    ProductService productService;

    @Resource
    DriverService driverService;

    @Resource
    RecordService recordService;

    @Resource
    OrderDetailService orderDetailService;

    @RequestMapping("loginChoosePage")
    String loginChoosePage(){
        return "pages/front/login/loginChoosePage";
    }

    @RequestMapping("driverLoginPage")
    String driverLoginPage(){
        return "pages/front/login/driverLoginPage";
    }

    @RequestMapping("driverLogin")
    String driverLogin(Driver driver, Model model, HttpSession session){

        Driver realDriver;
        if(session.getAttribute("driver") == null){
            if("".equals(driver.getPwd()) || driver.getPwd() == null){
                model.addAttribute("errorMsg","Please enter the password");
                return  "pages/front/login/driverLoginPage";
            }else if("".equals(driver.getPhoneNum()) || driver.getPhoneNum() == null){
                model.addAttribute("errorMsg","Please enter your phone number");
                return  "pages/front/login/driverLoginPage";
            }
            realDriver = driverService.login(driver);
        }else{
            realDriver = (Driver) session.getAttribute("driver");
        }




        if(realDriver != null){

            List<DriverRecord> driverRecords = new ArrayList<>();

            List<Record> records = recordService.listAll();


            for(Record r : records){

                DriverRecord dr = new DriverRecord(r.getId(),r.getOrderDate(),
                        r.getCustomerPhone(),r.getCustomerAddress(),r.getTotalPrice());

                driverRecords.add(dr);

            }


            model.addAttribute("homePageList",driverRecords);
            session.setAttribute("driver",realDriver);
            return "pages/front/driver/homePage";
        }else{
            //fail
            model.addAttribute("errorMsg","wrong password or phone number!");
            return "pages/front/login/driverLoginPage";
        }

    }

    @RequestMapping("customerLoginPage")
    String customerLoginPage(){

        return "pages/front/login/customerLoginPage";
    }

    @RequestMapping("restaurantLoginPage")
    String restaurantLoginPage(){
        return "pages/front/login/restaurantLoginPage";
    }

    @RequestMapping("customerLogin")
    String customerLogin(Customer customer, Model model, HttpSession session){
        if("".equals(customer.getPwd()) || customer.getPwd() == null){
            model.addAttribute("errorMsg","Please enter the password");
            return  "pages/front/login/customerLoginPage";
        }else if("".equals(customer.getId()) || customer.getId() == null){
            model.addAttribute("errorMsg","Please enter the id");
            return  "pages/front/login/customerLoginPage";
        }
        Customer realCustomer = customerService.login(customer);
        if(realCustomer != null){
            //Success
            List<Product> products = productService.listProduct();
            model.addAttribute("products",products);
            session.setAttribute("customer", realCustomer);
            return "index";
        }else{
            //fail
            model.addAttribute("errorMsg","wrong password or userId!");
            return "pages/front/login/customerLoginPage";
        }

    }

    @RequestMapping("restaurantLogin")
    String restaurantLogin(Restaurant restaurant, Model model, HttpSession session){
        //chucuo
//        if(session.getAttribute("restaurant") != null){
//            return "pages/back/backHome";
//        }
        if("".equals(restaurant.getPwd()) || restaurant.getPwd() == null){
            model.addAttribute("errorMsg","Please enter the password");
            return  "pages/front/login/restaurantLoginPage";
        }else if("".equals(restaurant.getPhoneNum()) || restaurant.getPhoneNum() == null){
            model.addAttribute("errorMsg","Please enter the phone number");
            return  "pages/front/login/restaurantLoginPage";
        }
        Restaurant realRestaurant = restaurantService.login(restaurant);
        if(realRestaurant != null){
            //Success
            session.setAttribute("restaurant", realRestaurant);
            /*
            Restaurant me = (Restaurant) session.getAttribute("restaurant");

            Map<RestaurantOrder.State,List<RestaurantOrder>> map = restaurantService.getAllOrders(me.getPhoneNum());

            model.addAttribute("cancel",map.get(RestaurantOrder.State.CANCELED));
            model.addAttribute("prepare",map.get(RestaurantOrder.State.PREPARING));
            model.addAttribute("ontheway",map.get(RestaurantOrder.State.ONTHEWAY));
            model.addAttribute("request",map.get(RestaurantOrder.State.REQUESTREFUND));
            model.addAttribute("refund",map.get(RestaurantOrder.State.REQUESTOK));
            model.addAttribute("place",map.get(RestaurantOrder.State.PlACED));
            model.addAttribute("confirm",map.get(RestaurantOrder.State.CONFIRMED));
            model.addAttribute("comment",map.get(RestaurantOrder.State.COMMENT));

            */

            return "pages/back/backHome";
        }else{
            //fail
            model.addAttribute("errorMsg","wrong password or phone number!");
            return "pages/front/login/restaurantLoginPage";
        }
    }

    @RequestMapping("alreadyLoginCheck")
    @ResponseBody()
    public JsonResult<Void> alreadyLoginCheck(HttpSession session){
        if(session.getAttribute("customer") == null){
            return new JsonResult<>(-1);
        }else{
            return new JsonResult<>(1);
        }
    }

    //前端未实现
    @RequestMapping("customerLogout")
    public String customerLogout(HttpSession session){
        session.removeAttribute("customer");
        return "pages/front/index";
    }
    //前端未实现
    @RequestMapping("restaurantLogout")
    public String restaurantLogout(HttpSession session){
        session.removeAttribute("restaurant");
        return "pages/back/restaurantLogout";
    }


}

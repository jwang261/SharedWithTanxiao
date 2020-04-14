package com.jwang261.gourmet.controller.pages.front;


import com.jwang261.gourmet.pojo.entity.Customer;
import com.jwang261.gourmet.pojo.entity.Driver;
import com.jwang261.gourmet.pojo.entity.Restaurant;
import com.jwang261.gourmet.service.CustomerService;
import com.jwang261.gourmet.service.DriverService;
import com.jwang261.gourmet.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/pages/front")
public class RegisterController {

    @Resource
    CustomerService customerService;

    @Resource
    RestaurantService restaurantService;

    @Resource
    DriverService driverService;

    @RequestMapping("customerRegisterPage")
    String customerRegisterPage(){
        return "pages/front/register/customerRegisterPage";
    }

    //还没有前一个页面
    @RequestMapping("restaurantRegisterPage")
    String restaurantRegisterPage(){
        return "pages/front/register/restaurantRegisterPage";
    }

    @RequestMapping("driverRegisterPage")
    String driverRegisterPage(){
        return "pages/front/register/driverRegisterPage";
    }

    //李在 赣神魔
    @RequestMapping("driverRegister")
    String driverRegister(Driver driver, Model model){
        boolean b = driverService.canCreate(driver);

        //check and phone
        if(b){

            //下面两个页面还没做(好像做了 ———— 4/3)

            boolean res = driverService.insert(driver);

            if(res){
                model.addAttribute("msg","Create successfully" );
            }else{
                model.addAttribute("msg","Fail!" );
            }


        }else{

            model.addAttribute("msg","Phone number has already exist");

        }

        return "pages/front/register/driverRegisterResult";


    }



    @RequestMapping("customerRegister")
    String customerRegister(Customer customer, Model model, RedirectAttributes redirectAttributes){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        boolean b = customerService.canCreate(customer);

        //check email and phone
        if(b){


            int newId = customerService.insert(customer);
            model.addAttribute("msg","Create successfully, your id is " + newId + " ." );

        }else{

            model.addAttribute("msg","Phone number or Email has already exist");

        }
        return "pages/front/register/customerRegisterResult";



    }
    @RequestMapping("restaurantRegister")
    String restaurantRegister(Restaurant restaurant, Model model){



        boolean b = restaurantService.canCreate(restaurant);

        //check and phone
        if(b){

            //下面两个页面还没做(好像做了 ———— 4/3)
            boolean res = restaurantService.insert(restaurant);
            if(res){
                model.addAttribute("msg","Create successfully" );
            }else{
                model.addAttribute("msg","Fail!" );
            }


        }else{

            model.addAttribute("msg","Phone number has already exist");

        }

        return "pages/front/register/restaurantRegisterResult";


    }


}

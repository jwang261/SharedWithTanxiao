package com.jwang261.gourmet.controller.pages.back;

import com.jwang261.gourmet.pojo.entity.*;
import com.jwang261.gourmet.service.OrderDetailService;
import com.jwang261.gourmet.service.ProductService;
import com.jwang261.gourmet.service.RecordService;
import com.jwang261.gourmet.service.RestaurantService;
import com.jwang261.gourmet.util.JsonResult;
import com.sun.xml.internal.org.jvnet.fastinfoset.RestrictedAlphabet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pages/back")
public class restaurantController {

    @Resource
    RestaurantService restaurantService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    RecordService recordService;

    @Autowired
    ProductService productService;

    @RequestMapping("restaurantOrder")
    String restaurantOrder(HttpSession session, Model model){


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



        return "pages/back/restaurantOrder";
    }


    @RequestMapping("orders")
    String finished(Model model, HttpSession session){
        Restaurant me = (Restaurant) session.getAttribute("restaurant");

        Map<RestaurantOrder.State,List<RestaurantOrder>> map = restaurantService.getAllOrders(me.getPhoneNum());

        model.addAttribute("cancel",map.get(RestaurantOrder.State.CANCELED));
        model.addAttribute("confirm",map.get(RestaurantOrder.State.CONFIRMED));
        model.addAttribute("comment",map.get(RestaurantOrder.State.COMMENT));
        model.addAttribute("refund",map.get(RestaurantOrder.State.REQUESTOK));
        model.addAttribute("prepare",map.get(RestaurantOrder.State.PREPARING));
        model.addAttribute("ontheway",map.get(RestaurantOrder.State.ONTHEWAY));
        model.addAttribute("request",map.get(RestaurantOrder.State.REQUESTREFUND));
        model.addAttribute("place",map.get(RestaurantOrder.State.PlACED));
        return "pages/back/orders";
    }

    @RequestMapping("viewProducts")
    String viewProducts(Model model, HttpSession session){

        Restaurant me = (Restaurant) session.getAttribute("restaurant");

        List<Product> products = productService.getAllProducts(me.getId());

        model.addAttribute("products",products);
        return "pages/back/products";
    }
/*
    @RequestMapping("unfinished")
    String unfinished(Model model, HttpSession session){
        Restaurant me = (Restaurant) session.getAttribute("restaurant");

        Map<RestaurantOrder.State,List<RestaurantOrder>> map = restaurantService.getAllOrders(me.getPhoneNum());

        model.addAttribute("prepare",map.get(RestaurantOrder.State.PREPARING));
        model.addAttribute("ontheway",map.get(RestaurantOrder.State.ONTHEWAY));
        model.addAttribute("place",map.get(RestaurantOrder.State.PlACED));
        return "/pages/back/order/unfinished";
    }

    @RequestMapping("others")
    String others(Model model, HttpSession session){
        Restaurant me = (Restaurant) session.getAttribute("restaurant");

        Map<RestaurantOrder.State,List<RestaurantOrder>> map = restaurantService.getAllOrders(me.getPhoneNum());

        model.addAttribute("request",map.get(RestaurantOrder.State.REQUESTREFUND));
        return "/pages/back/order/others";
    }
    */


    @RequestMapping("confirmOrder")
    @ResponseBody
    public JsonResult<Void> confirmOrder(int orderDetailId){
        int recordId = orderDetailService.getRecordIdById(orderDetailId);
        recordService.changeState(recordId,2);
        return new JsonResult<>(1);
    }

    @RequestMapping("approveRefund")
    @ResponseBody
    public JsonResult<Void> approveRefund(int orderDetailId){
        int recordId = orderDetailService.getRecordIdById(orderDetailId);
        recordService.changeState(recordId,-2);
        return new JsonResult<>(1);
    }



    @RequestMapping("backHome")
    public String backHome(){
        return "pages/back/backHome";
    }

    @RequestMapping("viewComments")
    public String viewComments(Model model, HttpSession session){

        Restaurant me = (Restaurant) session.getAttribute("restaurant");


        List<Comment> comments = restaurantService.getComment(me.getPhoneNum());

        model.addAttribute("comment",comments);

        return "pages/back/comments";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session, Model model){
        List<Product> products = productService.listProduct();
        model.addAttribute("products",products);
        session.removeAttribute("restaurant");



        return "index";
    }


}

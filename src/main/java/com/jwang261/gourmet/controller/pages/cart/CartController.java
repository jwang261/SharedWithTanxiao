package com.jwang261.gourmet.controller.pages.cart;


import com.jwang261.gourmet.dao.CustomerDao;
import com.jwang261.gourmet.pojo.entity.*;
import com.jwang261.gourmet.service.*;
import com.jwang261.gourmet.util.JsonResult;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pages/cart")
public class CartController {

    private static final int OK = 2000;

    @Resource
    CartService cartService;

    @Resource
    ProductService productService;

    @Resource
    RestaurantService restaurantService;

    @Resource
    CustomerService customerService;

    @Resource
    OrderDetailService orderDetailService;

    @Resource
    RecordService recordService;

    @RequestMapping("addCart")
    @ResponseBody
    public JsonResult<Void> addToCart(int productId, HttpSession session) {



        Product realProduct = productService.selectByProductId(productId);

        Customer me = (Customer) session.getAttribute("customer");

        if(me == null){
            return new JsonResult<>(-1);
        }

        List<Cart> list = cartService.getCartByCustomerId(me.getId());

        for(Cart c : list){
            if(c.getProductId() == realProduct.getId()){
                cartService.addCart(c.getId());
                return new JsonResult<>(OK);
            }
        }


        cartService.addNewCart(realProduct, me.getId());


        // 响应成功
        return new JsonResult<>(OK);
    }


    @RequestMapping("checkAlreadyLogin")
    @ResponseBody
    public JsonResult<Void> checkAlreadyLogin(HttpSession session){
        Customer me = (Customer) session.getAttribute("customer");
        if(me == null){
            return new JsonResult<>(-1);
        }else{

            //投机取巧的写法
            return new JsonResult<>(me.getId());
        }
    }

    @RequestMapping("customerCart")
    public String customerCart(Model model, HttpSession session){

        Customer me = (Customer) session.getAttribute("customer");

        List<Cart> carts = cartService.getCartByCustomerId(me.getId());

        model.addAttribute("cart",carts);




        return "pages/front/cart";
    }

    @RequestMapping("getProductDetail")
    public String getProductDetail(int productId){
        return "";
    }

    @RequestMapping("removeCart/{productId}")
    public String removeProduct(@PathVariable int productId){

        cartService.deleteCart(productId);


        return "redirect:/pages/cart/customerCart";
    }

    @RequestMapping("checkOut")
    public String checkOut(HttpSession session, Model model){

        Date date = new Date();

        //添加到record
        Record record = new Record();

        //From Customer：c_phone,c_name,c_address
        Customer me = (Customer) session.getAttribute("customer");
        record.setCustomerName(me.getCustomerName());
        record.setCustomerAddress(me.getAddress());
        record.setCustomerPhone(me.getPhoneNum());
        record.setComment("");
        //From Cart：total_price
        double totalPrice = cartService.checkOut(me.getId());
        record.setTotalPrice(totalPrice);

        //From Sys：order_date
        record.setOrderDate(date);

        //Driver_phone = Driver_name = "";

        //State = 1
        record.setState(1);


        int recordId = recordService.insertNewRecord(record);



        //添加到OrderDetail
        //From Restaurant：r_phone,r_name from prod from cart
        List<Cart> carts = cartService.getCartByCustomerId(me.getId());

        // 1 cart -> 1 orderDetail
        for(Cart c : carts){
            int productId = cartService.getProductIdByCartId(c.getId());
            int restaurantId = productService.getResIdByProductId(productId);
            Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

            OrderDetail od = new OrderDetail();
            od.setRestaurantName(restaurant.getRestaurantName());
            od.setRestaurantPhone(restaurant.getPhoneNum());
            od.setCount(c.getCount());
            od.setPrice(c.getPrice());
            od.setRecordId(recordId);
            od.setImg(productService.selectByProductId(productId).getImg());
            od.setProductId(productId);
            orderDetailService.addOrderDetail(od);

        }

        cartService.cleanCartByCustomerId(me.getId());

        model.addAttribute("msg","You have successfully place your order!");



        return "pages/front/checkOutSuccess";
    }

    @RequestMapping("checkEmpty")
    @ResponseBody
    public JsonResult<Void> checkEmpty(HttpSession session){
        int count = cartService
                .getCartByCustomerId(((Customer)session.getAttribute("customer"))
                        .getId())
                            .size();
        if(count == 0){
            return new JsonResult<>(-1);
        }else{
            return new JsonResult<>(1);
        }

    }
}

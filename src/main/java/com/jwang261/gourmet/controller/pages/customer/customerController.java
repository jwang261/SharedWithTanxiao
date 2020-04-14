package com.jwang261.gourmet.controller.pages.customer;

import com.jwang261.gourmet.pojo.entity.*;
import com.jwang261.gourmet.service.CustomerService;
import com.jwang261.gourmet.service.OrderDetailService;
import com.jwang261.gourmet.service.ProductService;
import com.jwang261.gourmet.service.RecordService;
import com.jwang261.gourmet.util.JsonResult;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pages/customer")
public class customerController {
    @Resource
    CustomerService customerService;

    @Autowired
    RecordService recordService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    ProductService productService;


    //CustomerOrder
    @RequestMapping("customerOrder")
    String customerOrder(Model model, HttpSession session){


        Customer me = (Customer) session.getAttribute("customer");

        Map<CustomerOrder.State,List<CustomerOrder>> map = customerService.getCustomerOrders(me.getPhoneNum());

        model.addAttribute("cancel",map.get(CustomerOrder.State.CANCELED));
        model.addAttribute("prepare",map.get(CustomerOrder.State.PREPARING));
        model.addAttribute("ontheway",map.get(CustomerOrder.State.ONTHEWAY));
        model.addAttribute("request",map.get(CustomerOrder.State.REQUESTREFUND));
        model.addAttribute("refund",map.get(CustomerOrder.State.REQUESTOK));
        model.addAttribute("place",map.get(CustomerOrder.State.PlACED));
        model.addAttribute("confirm",map.get(CustomerOrder.State.CONFIRMED));
        model.addAttribute("comment",map.get(CustomerOrder.State.COMMENT));


        //x
        return "pages/front/customer/customerOrder";
    }

    //CustomerProfile
    @RequestMapping("customerProfile")
    String customerProfile(Model model, HttpSession session){


        Customer me = (Customer) session.getAttribute("customer");

        model.addAttribute("customer",me);

       //x
       return "pages/front/customer/customerProfile";


    }

    @RequestMapping("customerEditPage")
    String customerEditPage(){
        return "pages/front/customer/customerEditPage";
    }

    @RequestMapping("goDetail")
    String goDetail(@PathVariable int recordId){

        return "pages/front/orderDetail";
    }

    @RequestMapping("cancel")
    @ResponseBody
    public JsonResult<Void> cancel(int recordId){

        recordService.changeState(recordId,0);

        return new JsonResult<>(1);
    }
    @RequestMapping("confirm")
    @ResponseBody
    public JsonResult<Void> confirm(int recordId){

        recordService.changeState(recordId,4);

        return new JsonResult<>(1);
    }
    @RequestMapping("refund")
    @ResponseBody
    public JsonResult<Void> refund(int recordId){

        recordService.changeState(recordId,-1);

        return new JsonResult<>(1);
    }

    @RequestMapping("goDetail/{recordId}")
    String goDetail(@PathVariable int recordId,  Model model){

        Record record = recordService.getRecordById(recordId);
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByRecordId(recordId);

        List<CustomerOrderDetail> cods = new ArrayList<>();
        for(OrderDetail od : orderDetails){

            String productName = productService.selectByProductId(od.getProductId()).getProductName();
            CustomerOrderDetail cod = new CustomerOrderDetail(productName,od.getPrice(),od.getCount(),
                    record.getTotalPrice(),od.getRestaurantName(),od.getRestaurantPhone());
            cods.add(cod);
        }

        model.addAttribute("cods",cods);
        return "pages/front/customer/orderDetail";
    }

    @RequestMapping("comment/{recordId}")
    public String comment(@PathVariable int recordId, Model model){


        model.addAttribute("commentId",recordId);

        return "pages/front/customer/commentPage";
    }
    @RequestMapping("submitComment/{recordId}")
    public String submitComment(@PathVariable int recordId,
                                Model model,/*@RequestParam("score") Object score,*/
                                @RequestParam("comment") String comment){

        //System.out.println(score);
        recordService.writeComment(recordId,comment);
        model.addAttribute("msg","Comment Submitted Successfully");
        return "pages/front/customer/commentResultPage";
    }
}

package com.jwang261.gourmet.controller.pages.driver;

import com.jwang261.gourmet.pojo.entity.*;
import com.jwang261.gourmet.service.DriverService;
import com.jwang261.gourmet.service.OrderDetailService;
import com.jwang261.gourmet.service.ProductService;
import com.jwang261.gourmet.service.RecordService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.awt.dnd.DropTargetDragEvent;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pages/driver")
public class driverController {

    @Autowired
    RecordService recordService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    ProductService productService;

    @Autowired
    DriverService driverService;

    @RequestMapping("selectOrder/{recordId}")
    String selectOrder(@PathVariable int recordId, Model model, HttpSession session){

        //get driver info
        Driver driver = (Driver) session.getAttribute("driver");

        recordService.updateDriverInfo(driver.getDriverName(),driver.getPhoneNum(),3,recordId);



        List<DriverRecord> driverRecords = new ArrayList<>();

        List<Record> records = recordService.listAll();


        for(Record r : records){

            DriverRecord dr = new DriverRecord(r.getId(),r.getOrderDate(),
                    r.getCustomerPhone(),r.getCustomerAddress(),r.getTotalPrice());

            driverRecords.add(dr);

        }


        model.addAttribute("homePageList",driverRecords);

        return "pages/front/driver/homePage";
    }


    @RequestMapping("goDetail/{recordId}")
    String goDetail(@PathVariable int recordId, HttpSession session, Model model){

        //get driver
        Driver driver = (Driver) session.getAttribute("driver");

        List<DriverRecordDetail> drds = new ArrayList<>();

        //1 r -> n orderD == n carts
        //get all unfinished records (contains n order details)
        List<Record> records = recordService.getUnfinishedRecords(driver.getPhoneNum());

        //each record = 3 details
        for(Record r : records){
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailByRecordId(r.getId());
            for(OrderDetail od : orderDetails){
                String productName = productService.selectByProductId(od.getProductId()).getProductName();
                DriverRecordDetail drd = new DriverRecordDetail(od.getRestaurantName(),od.getRestaurantPhone(),productName,od.getCount());
                drds.add(drd);
            }
        }

        model.addAttribute("drds",drds);
        return "pages/front/driver/orderDetail";
    }

    @RequestMapping("myOrders")
    String myOrders(Model model, HttpSession session){

        Driver driver = (Driver) session.getAttribute("driver");

        List<Record> unfinishedRecords = recordService.getUnfinishedRecords(driver.getPhoneNum());
        List<Record> finishedRecords = recordService.getFinishedRecords(driver.getPhoneNum());

        List<DriverRecord> unfinished = new ArrayList<>();
        List<DriverRecord> finished = new ArrayList<>();

        for(Record r : unfinishedRecords){

            DriverRecord dr = new DriverRecord(r.getId(),r.getOrderDate(),
                    r.getCustomerPhone(),r.getCustomerAddress(),r.getTotalPrice());

            unfinished.add(dr);

        }
        for(Record r : finishedRecords){

            DriverRecord dr = new DriverRecord(r.getId(),r.getOrderDate(),
                    r.getCustomerPhone(),r.getCustomerAddress(),r.getTotalPrice());

            finished.add(dr);

        }

        model.addAttribute("unfinished",unfinished);
        model.addAttribute("finished",finished);




        return "pages/front/driver/myOrders";
    }

    @RequestMapping("logout")
    String logout(HttpSession session, Model model){
        session.removeAttribute("driver");
        List<Product> products = productService.listProduct();
        model.addAttribute("products",products);




        return "index";
    }
}

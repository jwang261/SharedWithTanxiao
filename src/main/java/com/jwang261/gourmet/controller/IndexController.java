package com.jwang261.gourmet.controller;

import com.jwang261.gourmet.pojo.entity.Product;
import com.jwang261.gourmet.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    ProductService productService;

    List<Product> products = new ArrayList<>();

    @RequestMapping("/")
    String index(Model model){
        products = productService.listProduct();
        model.addAttribute("products",products);




        return "index";
    }


}

package com.jwang261.gourmet.controller.pages;


import com.jwang261.gourmet.pojo.entity.Product;
import com.jwang261.gourmet.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/pages")
public class SearchController {

    @Resource
    ProductService productService;

    @RequestMapping("search")
    String search(String keyWord, Model model){
        List<Product> list = productService.searchByKeyWord(keyWord);
        model.addAttribute("products",list);

        return "search";

    }

}

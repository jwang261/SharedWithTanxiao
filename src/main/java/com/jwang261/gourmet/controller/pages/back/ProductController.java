package com.jwang261.gourmet.controller.pages.back;


import com.jwang261.gourmet.pojo.entity.Product;
import com.jwang261.gourmet.pojo.entity.Restaurant;
import com.jwang261.gourmet.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.naming.spi.ResolveResult;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pages/product")
public class ProductController {

    @Resource
    ProductService productService;

    @RequestMapping("addPre")
    String addPre(){

        return "pages/back/product/product-addPre";
    }

    @RequestMapping("addProduct")
    String addProduct(Product product, HttpSession session, Model model){


        //等待测试 向下转型存在风险
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");

        boolean res = productService.addProduct(product, restaurant);

        if(res){
            model.addAttribute("addProductMsg","Adding product successfully!");
            return "pages/back/product/product-addSuccess";
        }else{
            model.addAttribute("addProductMsg","Fail!");
            return "pages/back/product/product-addFailed";
        }
    }

    @RequestMapping("deleteProduct/{productId}")
    String deleteProduct(@PathVariable int productId){
        productService.deleteProductById(productId);
        return "redirect:/pages/back/viewProducts";
    }

    @RequestMapping("editPage/{productId}")
    String editPage(@PathVariable int productId, Model model){
        Product product = productService.selectByProductId(productId);
        model.addAttribute("product",product);
        return "pages/back/productEditPage";
    }

    @RequestMapping("productDetailPage/{productId}")
    String productDetailPage(@PathVariable int productId, Model model){

        Product product = productService.selectByProductId(productId);
        model.addAttribute("product",product);
        return "pages/back/productDetailPage";
    }

    @RequestMapping("edit")
    String edit(Product product, Model model, HttpSession session){

        productService.updateProduct(product);
        model.addAttribute("msg","Update Success");
        return "pages/back/updateSuccess";
    }
}

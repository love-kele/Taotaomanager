package com.taotao.controller;

import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.rmic.RemoteClass;

@Controller
public class ItemContrller {
    @Autowired
    private ItemService service;

    @RequestMapping("/{page}")
   public String showItemmanager(@PathVariable String page){

        return page;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EUIDataGridResult getItemList(Integer page,Integer rows){
        EUIDataGridResult result = service.getItemList(page,rows);

        return result;

    }


    @RequestMapping("/")
   public String index(){

        return "index";
   }

    public void setService(ItemService service) {

        this.service = service;
    }
}

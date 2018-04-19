package com.taotao.controller;

import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.apache.http.HttpRequest;
import org.springframework.aop.MethodMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.rmic.RemoteClass;

@Controller
public class ItemContrller {
    @Autowired
    private ItemService service;

    @RequestMapping("/{page}")
    public String showItemmanager(@PathVariable String page) {

        return page;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EUIDataGridResult getItemList(Integer page, Integer rows) {
        EUIDataGridResult result = service.getItemList(page, rows);

        return result;
    }

    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem tbItem) {

        TaotaoResult result = service.createItem(tbItem);

        return result;
    }
    @RequestMapping(value="/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem tbItem) {

        TaotaoResult result = service.updateItem(tbItem);

        return  result;
    }

    @RequestMapping(value = "/rest/item/delete",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult delectItem(long[] ids) {


        TaotaoResult result = service.delectItem(ids);

        return result;
    }

    @RequestMapping(value = "/rest/item/reshelf",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult reshelfItem(long []ids){

        TaotaoResult result = service.reshelfItem(ids);

        return result;
    }

    @RequestMapping(value = "/rest/item/instock",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult instockItem(long []ids){

        TaotaoResult result = service.instockItem(ids);

        return result;
    }

    @RequestMapping("/rest/page/item-edit")
    public String showItem(){

        return  "item-edit";
    }


    @RequestMapping("/")
    public String index() {

        return "index";
    }

    public void setService(ItemService service) {

        this.service = service;
    }
}

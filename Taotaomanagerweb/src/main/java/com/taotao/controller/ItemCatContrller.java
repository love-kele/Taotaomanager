package com.taotao.controller;

import com.taotao.common.pojo.EUITreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatContrller {
    @Autowired
    private ItemCatService itemCatService;


    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List catgoryList(@RequestParam(value = "id",defaultValue = "0") long parentid){

        List<EUITreeNode> list = itemCatService.getCatList(parentid);

        return list;
    }

    public void setItemCatService(ItemCatService itemCatService) {
        this.itemCatService = itemCatService;
    }
}

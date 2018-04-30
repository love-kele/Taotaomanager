package com.taotao.controller;

import com.taotao.common.pojo.EUITreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.service.ItemContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ItemContentCatContrller {

    @Autowired
    private ItemContentCatService contentCatService;


    @RequestMapping("/list")
    @ResponseBody
    public List getConCatList(@RequestParam(value = "id" ,defaultValue = "0")long parentId){

        List<EUITreeNode> contCatList = contentCatService.getContCatList(parentId);

        return contCatList;
    }


    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult insertContCat(long parentId,String name){

        TaotaoResult re = contentCatService.createContCat(parentId, name);

     return  re;
    }


     @RequestMapping("/update")
      @ResponseBody
    public TaotaoResult updateName(long id ,String name){

         TaotaoResult result = contentCatService.updateName(id, name);
         return result;

     }

     @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContCat(Long parentId,long id){
        TaotaoResult result = contentCatService.deleteContCat(parentId, id);

        return result;
     }
}

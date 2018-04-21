package com.taotao.controller;

import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class ItemParamContrller {

    @Autowired
    private ItemParamService paramService;

    @RequestMapping("/query/itemcatid/{id}")
    @ResponseBody
    public TaotaoResult getItemParambyid(@PathVariable long id){

        TaotaoResult result = paramService.getItemParam(id);

        return result;
    }
   @RequestMapping(value = "/save/{cid}",method = RequestMethod.POST)
   @ResponseBody
   public TaotaoResult saveItemParam(@PathVariable long cid,String paramData){

        TaotaoResult result = paramService.saveItemParam(cid,paramData);

        return result;
   }

  @RequestMapping("/list")
  @ResponseBody
  public EUIDataGridResult selectItemParam(Integer page,Integer rows){

        EUIDataGridResult result =paramService.selectItemParam(page,rows);

        return result;
  }

    public void setParamService(ItemParamService paramService) {
        this.paramService = paramService;
    }
}

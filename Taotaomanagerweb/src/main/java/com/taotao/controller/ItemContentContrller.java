package com.taotao.controller;

import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ItemContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/content")
public class ItemContentContrller {

    @Autowired
    private ItemContentService contentService;


    @RequestMapping("/query/list")
    @ResponseBody
    public EUIDataGridResult showContent(Integer page, Integer rows, long categoryId) {

        EUIDataGridResult result = contentService.showContent(page, rows, categoryId);
        return result;

    }

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult createItemContent(TbContent tbContent) {

        TaotaoResult result = contentService.insertContent(tbContent);

        return result;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult updateItemContent(TbContent tbContent) {

        TaotaoResult result = contentService.updateContent(tbContent);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItemContent(long []ids) throws Exception {
        TaotaoResult result = contentService.deleteContent(ids);
        return result;
    }
}

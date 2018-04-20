package com.taotao.service;

import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(long itemid);

    EUIDataGridResult getItemList(int page,int rows);

    TaotaoResult createItem(TbItem tbItem,String desc,String params)throws Exception;

    TaotaoResult updateItem(TbItem tbItem);

    TaotaoResult delectItem(long [] ids);

    TaotaoResult instockItem(long [] ids);

    TaotaoResult reshelfItem(long [] ids);
}

package com.taotao.service;

import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(long itemid);

    EUIDataGridResult getItemList(int page,int rows);
}

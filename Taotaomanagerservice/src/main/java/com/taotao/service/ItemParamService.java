package com.taotao.service;

import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;

public interface ItemParamService {

    TaotaoResult getItemParam(long itemcatid);

    TaotaoResult saveItemParam(long id,String param);

    EUIDataGridResult selectItemParam(int page,int rows);
}

package com.taotao.service;

import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ItemContentService {
    EUIDataGridResult showContent(int page,int rows,long categoryId);

    TaotaoResult insertContent(TbContent tbContent);

    TaotaoResult updateContent(TbContent tbContent);

    TaotaoResult deleteContent(long[] ids) throws Exception;
}

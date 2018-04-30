package com.taotao.service;

import com.taotao.common.pojo.EUITreeNode;
import com.taotao.common.utils.TaotaoResult;

import java.util.List;

public interface ItemContentCatService {

    List<EUITreeNode>   getContCatList(long parentId);

    TaotaoResult createContCat(long parentId , String name);

    TaotaoResult updateName(long id ,String name);

    TaotaoResult deleteContCat(Long parentId,long id);
}

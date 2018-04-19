package com.taotao.service;

import com.taotao.common.pojo.EUITreeNode;

import java.util.List;

public interface ItemCatService {

   List<EUITreeNode> getCatList(long parentid);

}


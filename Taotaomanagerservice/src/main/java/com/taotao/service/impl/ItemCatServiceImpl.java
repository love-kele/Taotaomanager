package com.taotao.service.impl;

import com.taotao.common.pojo.EUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;


    @Override
    public List<EUITreeNode> getCatList(long parentid) {
        TbItemCatExample example = new TbItemCatExample();

         TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentid);
            List<TbItemCat> list = itemCatMapper.selectByExample(example);
           List<EUITreeNode> result = new ArrayList<EUITreeNode>();
        for (TbItemCat tb: list)
        {
             EUITreeNode node = new EUITreeNode();
             node.setId(tb.getId());
             node.setText(tb.getName());
             node.setState(tb.getIsParent()?"closed":"open");
               result.add(node);
        }


        return  result;
    }


    public void setItemCatMapper(TbItemCatMapper itemCatMapper) {
        this.itemCatMapper = itemCatMapper;
    }
}

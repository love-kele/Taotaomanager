package com.taotao.service.impl;

import com.taotao.common.pojo.EUITreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ItemContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemContentCatServiceImpl implements ItemContentCatService{

    @Autowired
    private TbContentCategoryMapper categoryMapper;

    @Override
    public List<EUITreeNode> getContCatList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = categoryMapper.selectByExample(example);
        List<EUITreeNode> resultlist = new ArrayList<>();
        for (TbContentCategory tbcontentCat:tbContentCategories)
        {
            EUITreeNode node  = new EUITreeNode();
            node.setId(tbcontentCat.getId());
            node.setText(tbcontentCat.getName());
            node.setState(tbcontentCat.getIsParent()?"closed":"open");

             resultlist.add(node);
        }
        return resultlist;
    }

    //添加内容分类
    @Override
    public TaotaoResult createContCat(long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();

        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setStatus(1);

         categoryMapper.insert(tbContentCategory);
        TbContentCategory parentCat = categoryMapper.selectByPrimaryKey(parentId);
        if(!parentCat.getIsParent()){
            parentCat.setIsParent(true);
            categoryMapper.updateByPrimaryKeySelective(parentCat);
        }

        return TaotaoResult.ok(tbContentCategory);
       // return TaotaoResult.fail();
    }

    //重命名
    @Override
    public TaotaoResult updateName(long id, String name) {
        TbContentCategory category = new TbContentCategory();
        category.setName(name);
        category.setUpdated(new Date());
     TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
       criteria.andIdEqualTo(id);
        categoryMapper.updateByExampleSelective(category,example);
        return TaotaoResult.ok();
    }


    //删除分类
    @Override
    public TaotaoResult deleteContCat(Long parentId, long id) {
       TbContentCategory tbContentCategory = new TbContentCategory();
           tbContentCategory=categoryMapper.selectByPrimaryKey(id);
       if(tbContentCategory.getIsParent()){
           TbContentCategoryExample example = new TbContentCategoryExample();
           TbContentCategoryExample.Criteria criteria = example.createCriteria();
           criteria.andParentIdEqualTo(id);

           categoryMapper.deleteByExample(example);
       }
           categoryMapper.deleteByPrimaryKey(id);

        return TaotaoResult.ok();
    }
}

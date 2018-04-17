package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 根据商品id查询商品信息
 */
@Service
public  class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemid) {

        TbItem item = itemMapper.selectByPrimaryKey(itemid);
        if(item!=null) {
            return item;
        } else
            return null;
    }

    @Override
    public EUIDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();

        //分页处理
        PageHelper.startPage(page,rows);
        List<TbItem> itemList = itemMapper.selectByExample(example);

        //创建一个返回值对象
        EUIDataGridResult result = new EUIDataGridResult();

        result.setRows(itemList);
       //取记录总条数
        PageInfo<TbItem> info = new PageInfo<TbItem>(itemList);
        result.setTotal(info.getTotal());
        return result;
    }

    public void setItemMapper(TbItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }
}

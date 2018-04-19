package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 根据商品id查询商品信息
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemid) {

        TbItem item = itemMapper.selectByPrimaryKey(itemid);
        if (item != null) {
            return item;
        } else
            return null;
    }

    @Override
    public EUIDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();

        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> itemList = itemMapper.selectByExample(example);

        //创建一个返回值对象
        EUIDataGridResult result = new EUIDataGridResult();

        result.setRows(itemList);
        //取记录总条数
        PageInfo<TbItem> info = new PageInfo<TbItem>(itemList);
        result.setTotal(info.getTotal());
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem tbItem) {

        long id = IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new java.util.Date());
        tbItem.setUpdated(new java.util.Date());
        itemMapper.insert(tbItem);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateItem(TbItem tbItem) {
        tbItem.setUpdated(new java.util.Date());

        itemMapper.updateByPrimaryKeySelective(tbItem);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult delectItem(long []ids) {
        int num=0;
        for (Long id:ids)
        {
            TbItem tbItem = itemMapper.selectByPrimaryKey(id);

            tbItem.setStatus((byte)3);
            tbItem.setUpdated(new java.util.Date());
            itemMapper.updateByPrimaryKeySelective(tbItem);
            num++;
        }
          if(num==ids.length)

        return TaotaoResult.ok();
        else
            return TaotaoResult.fail();
//        bItem tbItem = itemMapper.selectByPrimaryKey(id);
//        if(tbItem.getStatus()==3){
//            return TaotaoResult.fail();
//        }
//
//        tbItem.setStatus((byte)3);
//        tbItem.setUpdated(new java.util.Date());
//
//        itemMapper.updateByPrimaryKeySelective(tbItem);
//
//        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult instockItem(long[] ids) {
        int num=0;
        for (Long id:ids)
        {
            TbItem tbItem = itemMapper.selectByPrimaryKey(id);


            tbItem.setStatus((byte)2);
            tbItem.setUpdated(new java.util.Date());
            itemMapper.updateByPrimaryKeySelective(tbItem);
            num++;
        }
        if(num==ids.length)

            return TaotaoResult.ok();
        else
            return TaotaoResult.fail();
//        TbItem tbItem = itemMapper.selectByPrimaryKey(ids);
//        if(tbItem.getStatus()==1) {
//
//            tbItem.setStatus((byte) 2);
//            tbItem.setUpdated(new java.util.Date());
//
//            itemMapper.updateByPrimaryKeySelective(tbItem);
//
//            return TaotaoResult.ok();
//        }  else {
//            return TaotaoResult.fail();
//        }
    }

    @Override
    public TaotaoResult reshelfItem(long [] ids) {
        int num=0;
        for (Long id:ids)
        {
            TbItem tbItem = itemMapper.selectByPrimaryKey(id);


            tbItem.setStatus((byte)1);
            tbItem.setUpdated(new java.util.Date());
            itemMapper.updateByPrimaryKeySelective(tbItem);
            num++;
        }
        if(num==ids.length)

            return TaotaoResult.ok();
        else
            return TaotaoResult.fail();
//        TbItem tbItem = itemMapper.selectByPrimaryKey(ids);
//        if(tbItem.getStatus()==1){
//            return TaotaoResult.fail();
//        }
//
//        tbItem.setStatus((byte)1);
//        tbItem.setUpdated(new java.util.Date());
//
//        itemMapper.updateByPrimaryKeySelective(tbItem);
//
//        return TaotaoResult.ok();
    }

    public void setItemMapper(TbItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }
}

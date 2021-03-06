package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ItemService包含对商品的管理
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    //通过商品id取出商品
    @Override
    public TbItem getItemById(long itemid) {

        TbItem item = itemMapper.selectByPrimaryKey(itemid);
        if (item != null) {
            return item;
        } else
            return null;
    }

    //分页查询所有商品
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

    //添加一个商品
    @Override
    public TaotaoResult createItem(TbItem tbItem, String desc, String params) throws Exception {

        long id = IDUtils.genItemId();
        tbItem.setId(id);
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new java.util.Date());
        tbItem.setUpdated(new java.util.Date());
        itemMapper.insert(tbItem);
        TaotaoResult result = insertItemDesc(id, desc);
        if (result.getStatus() != 200) {

            throw new Exception();
        }
        result = insertItemParams(id, params);
        if (result.getStatus() != 200) {

            throw new Exception();
        }
        return result;
    }

    //添加商品描述
    private TaotaoResult insertItemDesc(long itemid, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemid);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());

        itemDescMapper.insert(itemDesc);

        return TaotaoResult.ok();
    }

    //添加商品规格参数
    private TaotaoResult insertItemParams(long itemid, String params) {

        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemid);
        itemParamItem.setParamData(params);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();
    }

    //更新商品
    @Override
    public TaotaoResult updateItem(TbItem tbItem, String desc, String itemParams)throws Exception {
        tbItem.setUpdated(new java.util.Date());
        itemMapper.updateByPrimaryKeySelective(tbItem);
        TaotaoResult result = updateDesc(tbItem.getId(), desc);
        if(result.getStatus()!=200){
            throw new Exception();
        }
       result= updateParams(tbItem.getId(),itemParams);
        if(result.getStatus()!=200){
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    //更新商品描述
    private TaotaoResult updateDesc(long itemid, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemid);
        itemDescMapper.updateByExampleSelective(itemDesc,example);

        return TaotaoResult.ok();
    }

    //更新商品规格参数
    private TaotaoResult updateParams(long itemid, String params) {
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setParamData(params);
        itemParamItem.setUpdated(new Date());
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemid);
        itemParamItemMapper.updateByExampleSelective(itemParamItem, example);

        return TaotaoResult.ok();
    }

    //删除商品
    @Override
    public TaotaoResult delectItem(long[] ids) {
        int num = 0;
        for (Long id : ids) {
            TbItem tbItem = itemMapper.selectByPrimaryKey(id);

            tbItem.setStatus((byte) 3);
            tbItem.setUpdated(new java.util.Date());
            itemMapper.updateByPrimaryKeySelective(tbItem);
            num++;
        }
        if (num == ids.length)

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

    //下架商品
    @Override
    public TaotaoResult instockItem(long[] ids) {
        int num = 0;
        for (Long id : ids) {
            TbItem tbItem = itemMapper.selectByPrimaryKey(id);


            tbItem.setStatus((byte) 2);
            tbItem.setUpdated(new java.util.Date());
            itemMapper.updateByPrimaryKeySelective(tbItem);
            num++;
        }
        if (num == ids.length)

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

    //上架商品
    @Override
    public TaotaoResult reshelfItem(long[] ids) {
        int num = 0;
        for (Long id : ids) {
            TbItem tbItem = itemMapper.selectByPrimaryKey(id);


            tbItem.setStatus((byte) 1);
            tbItem.setUpdated(new java.util.Date());
            itemMapper.updateByPrimaryKeySelective(tbItem);
            num++;
        }
        if (num == ids.length)

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

    //通过商品id获取商品规格参数
    @Override
    public TaotaoResult getParams(long itemid) {

        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemid);
        List<TbItemParamItem> itemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (itemParamItems != null && itemParamItems.size() > 0)

            return TaotaoResult.ok(itemParamItems.get(0));

        return TaotaoResult.ok();
    }

    //通过商品id获取商品描述
    @Override
    public TaotaoResult getDesc(long itemid) {
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemid);
        List<TbItemDesc> itemDescs = itemDescMapper.selectByExampleWithBLOBs(example);
        if (itemDescs != null && itemDescs.size() > 0) {
            return TaotaoResult.ok(itemDescs.get(0));
        }

        return TaotaoResult.ok();
    }


    public void setItemMapper(TbItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public void setItemDescMapper(TbItemDescMapper itemDescMapper) {
        this.itemDescMapper = itemDescMapper;
    }

    public void setItemParamItem(TbItemParamItemMapper itemParamItem) {
        this.itemParamItemMapper = itemParamItem;
    }
}


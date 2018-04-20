package com.taotao.service.impl;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public TaotaoResult getItemParam(long itemcatid) {

        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemcatid);
          //s使用selectByExampleWithBLOBs()才可以从数据库中取出长文本数据
        List<TbItemParam> tbItemParams = itemParamMapper.selectByExampleWithBLOBs(example);

        if(tbItemParams!=null&&tbItemParams.size()>0){
            return TaotaoResult.ok(tbItemParams.get(0));
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult saveItemParam(long id,String param) {

        TbItemParam itemParam = new TbItemParam();

        itemParam.setItemCatId(id);
        itemParam.setParamData(param);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        itemParamMapper.insert(itemParam);

        return TaotaoResult.ok();
    }

    public void setItemParamMapper(TbItemParamMapper itemParamMapper) {
        this.itemParamMapper = itemParamMapper;
    }
}

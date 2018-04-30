package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ItemContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemContentServiceImpl implements ItemContentService {
    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EUIDataGridResult showContent(int page, int rows,long categoryId) {
        TbContentExample example = new TbContentExample();
      TbContentExample.Criteria criteria = example.createCriteria();
      //  criteria.andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page,rows);
        if(categoryId!=0) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
//        if(!StringUtils.isBlank(String.valueOf(categoryId))){
//       }
        List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(example);

        PageInfo<TbContent> info = new PageInfo<>(tbContents);

        EUIDataGridResult result = new EUIDataGridResult();

       result.setTotal(info.getTotal());
       result.setRows(tbContents);

        return result;
    }

    //添加内容
    @Override
    public TaotaoResult insertContent(TbContent tbContent) {

        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());

        contentMapper.insert(tbContent);

        return TaotaoResult.ok();
    }

    //更新内容


    @Override
    public TaotaoResult updateContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());

        contentMapper.updateByPrimaryKeyWithBLOBs(tbContent);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContent(long[] ids) throws Exception {
        int count=0;
            for (Long id:ids )
            {
            contentMapper.deleteByPrimaryKey(id);
            count++;
            }
        if(ids.length==count){
                return TaotaoResult.ok();
        } else {
              throw new Exception();
            //return TaotaoResult.fail();
        }

    }
}

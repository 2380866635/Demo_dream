package com.dream.content.service.impl;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.content.service.TbContentService;
import com.dream.mapper.TbContentMapper;
import com.dream.pojo.TbContent;
import com.dream.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Override
    public EasyUiDataGridResult list(int pageNum, int pageSize, long categoryId) {
        //调用分页插件
        PageHelper.startPage(pageNum,pageSize);
        //进行查询的信息  根据广告框的id进行查询下面的内容
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        long count = tbContentMapper.countByExample(tbContentExample);
        return new EasyUiDataGridResult(count,tbContents);
    }

    @Override
    public DreamResult save(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        tbContentMapper.insert(tbContent);
        return DreamResult.ok();
    }

    @Override
    public DreamResult delete(List<Long> params) {
            TbContentExample tbContentExample = new TbContentExample();
            TbContentExample.Criteria criteria = tbContentExample.createCriteria();
            criteria.andIdIn(params);
             tbContentMapper.deleteByExample(tbContentExample);
        return DreamResult.ok();
    }

    @Override
    public DreamResult contentEdit(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContentMapper.updateByPrimaryKey(tbContent);
        return DreamResult.ok();
    }

    @Override
    public List<TbContent> selectContentByCategoryId(Long ad1_categoy_id) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(ad1_categoy_id);
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        return tbContents;
    }
}

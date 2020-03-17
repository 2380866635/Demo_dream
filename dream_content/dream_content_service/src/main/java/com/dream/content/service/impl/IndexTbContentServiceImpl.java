package com.dream.content.service.impl;

import com.dream.content.service.IndexTbContentService;
import com.dream.mapper.TbContentMapper;
import com.dream.pojo.TbContent;
import com.dream.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IndexTbContentServiceImpl  implements IndexTbContentService {

    @Autowired
    private TbContentMapper tbContentMapper;
    @Override
    public List<TbContent> selectContentByCategoryId(Long ad1_categoy_id) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(ad1_categoy_id);
        List<TbContent> tbContents = tbContentMapper.selectByExample(tbContentExample);
        return tbContents;
    }
}

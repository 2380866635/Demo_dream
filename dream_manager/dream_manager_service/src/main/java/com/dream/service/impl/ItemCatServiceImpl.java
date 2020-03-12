package com.dream.service.impl;

import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.common.pojo.EasyUiTreeNode;
import com.dream.mapper.TbItemCatMapper;
import com.dream.pojo.TbItemCat;
import com.dream.pojo.TbItemCatExample;
import com.dream.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
@Autowired
private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUiTreeNode> getItemCatListByParentId(Long parentId) {
        //设置查询树0为一级查询  默认long查询为0
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //查询一级树
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);
        //处理结果把 TbItemCat转化为EasyUiTreeNode
        List<EasyUiTreeNode> list = new ArrayList<>();
        for (TbItemCat cat:tbItemCats){
            EasyUiTreeNode easyUiTreeNode = new EasyUiTreeNode();
            easyUiTreeNode.setId(cat.getId());
            easyUiTreeNode.setText(cat.getName());
            easyUiTreeNode.setState(cat.getIsParent()?"closed":"open");
            list.add(easyUiTreeNode);
        }
        return list;
    }
}

package com.dream.content.service.impl;


import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiTreeNode;
import com.dream.content.service.TbContentCategoryService;
import com.dream.mapper.TbContentCategoryMapper;
import com.dream.pojo.TbContentCategory;
import com.dream.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    /**
     * 获取广告树
     * @param parentId =父id
     * @return  树的集合  id  内容 是否展开
     */
    @Override
    public List<EasyUiTreeNode> getContentCategoryByParentId(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories =
                tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        ArrayList<EasyUiTreeNode> nodes = new ArrayList<>();
        for (TbContentCategory category:tbContentCategories){
            EasyUiTreeNode node = new EasyUiTreeNode();
            node.setState(category.getIsParent()?"closed":"open");
            node.setText(category.getName());
            node.setId(category.getId());
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public DreamResult create(long parentId,String name) {
        TbContentCategory category = new TbContentCategory();
      //增加页面广告窗口
        category.setName(name);
        category.setParentId(parentId);
        category.setStatus(1);
        //新增的窗口肯定是没有子id的
        category.setIsParent(false);
        category.setSortOrder(1);
        category.setCreated(new Date());
        category.setUpdated(category.getCreated());
        tbContentCategoryMapper.insert(category);
        //给新增的广告窗口的父id  设置是否有子id进行设置
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setIsParent(true);
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andIdEqualTo(parentId);
        tbContentCategoryMapper.updateByExampleSelective(contentCategory,tbContentCategoryExample);
        System.out.println("返回增长的信息"+category);
        return DreamResult.ok(category);
    }
    //关闭窗口
    @Override
    public DreamResult delete(long id) {

        //将要删除的窗口关闭了
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setStatus(2);
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andIdEqualTo(id);
        tbContentCategoryMapper.updateByExampleSelective(contentCategory,tbContentCategoryExample);
        //判断是否有父id  将父id判断是否有子id了 没有了设为没有子id了
        TbContentCategory category1 = tbContentCategoryMapper.selectByPrimaryKey(id);
        TbContentCategoryExample categoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria exampleCriteria = categoryExample.createCriteria();
        exampleCriteria.andParentIdEqualTo(category1.getParentId());
        exampleCriteria.andIsParentEqualTo(true);
        List<TbContentCategory> tbContentCategories =
                tbContentCategoryMapper.selectByExample(categoryExample);
        if(tbContentCategories.size()==0){
            //如果父id下没有子id了则关闭
            TbContentCategory category = new TbContentCategory();
            category.setIsParent(false);
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria1 = example.createCriteria();
            criteria1.andIdEqualTo(category1.getParentId());
            tbContentCategoryMapper.updateByExampleSelective(category,example);
        }else if (tbContentCategories.size()>0){

        }
        return DreamResult.ok();
    }

    @Override
    public DreamResult update(long id, String name) {
        TbContentCategory category = new TbContentCategory();
        category.setName(name);
        category.setUpdated(new Date());
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        tbContentCategoryMapper.updateByExampleSelective(category,example);
        return DreamResult.ok();
    }


}

package com.dream.service;

import com.dream.common.pojo.EasyUiTreeNode;

import java.util.List;

public interface ItemCatService {
    /**
     * 获取树的机构内容
     * @param parentId
     * @return
     */
    List<EasyUiTreeNode> getItemCatListByParentId(Long parentId);

}

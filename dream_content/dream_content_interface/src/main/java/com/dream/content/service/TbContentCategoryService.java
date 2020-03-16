package com.dream.content.service;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.common.pojo.EasyUiTreeNode;

import java.util.List;

public interface TbContentCategoryService {
    /**
     * 查询所有广告树
     * @param parentId
     * @return
     */
    List<EasyUiTreeNode> getContentCategoryByParentId(Long parentId);

    /**
     * 添加广告窗口栏
     * @param
     * @return
     */
    DreamResult create(long parentId,String name);

    /**
     * 删除窗口方法  其实删除就是把这个显示窗口关闭而非真删
     *
     * @param id
     * @return
     */
    DreamResult delete(long id);

    /**
     * 重命名
     * @param id
     * @param name
     * @return
     */
    DreamResult update(long id, String name);
}

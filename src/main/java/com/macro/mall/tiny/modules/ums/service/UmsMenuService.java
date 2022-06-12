package com.macro.mall.tiny.modules.ums.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.modules.ums.dto.UmsMenuNode;
import com.macro.mall.tiny.modules.ums.model.UmsMenu;

import java.util.List;

/**
 * 后台菜单管理Service
 * Created by macro on 2020/2/2.
 */
public interface UmsMenuService extends IService<UmsMenu> {

    /**
     * 树形结构返回所有菜单列表
     */
    List<UmsMenuNode> treeList();
}

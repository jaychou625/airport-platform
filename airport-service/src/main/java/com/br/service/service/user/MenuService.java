package com.br.service.service.user;

import com.br.entity.access.Menu;
import com.br.entity.access.Role;
import com.br.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单服务
 *
 * @Author zyx
 * @Date 2019 03 31
 */
@Service("menuService")
public class MenuService {

    // 菜单 Mapper
    @Resource
    private MenuMapper menuMapper;

    /**
     * 查询所有菜单
     *
     * @return List<Menu>
     */
    public List<Menu> findAll() {
        return this.menuMapper.findAll();
    }

    /**
     * 获取所有菜单 By 角色集合
     *
     * @param roles
     * @return List<Menu>
     */
    public List<Menu> getMenusByRoleSeq(List<Role> roles) {
        if (roles != null && roles.size() > 0) {
            List<Menu> allMenus = new ArrayList<>();
            for (Role role : roles) {
                List<Menu> menus = this.menuMapper.findMenusByRoleSeq(role.getRoleSeq());
                if (menus != null && menus.size() > 0) {
                    for (Menu menu : menus) {
                        allMenus.add(menu);
                    }
                }
            }
            if (allMenus != null && allMenus.size() > 0) {
                Map<Integer, Menu> distinctMenus = new HashMap();
                for (Menu menu : allMenus) {
                    distinctMenus.put(menu.getMenuSeq(), menu);
                }
                allMenus = CollectionUtils.arrayToList(distinctMenus.values().toArray());
                return allMenus;
            }
        }
        return null;
    }
}

package com.lzt.ssm.blog.service.impl;

import com.lzt.ssm.blog.entity.Menu;
import com.lzt.ssm.blog.mapper.MenuMapper;
import com.lzt.ssm.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/11 11:12
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> listEntityByLevel(Integer level) {
        return menuMapper.listEntityByLevel(level);
    }

    @Override
    public void insertEntity(Menu menu) {
        menuMapper.insertSelective(menu);
    }

    @Override
    public void deleteEntityById(Integer id) {

        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateEntity(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Menu getEntityById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int getMaxOrder() {
        return menuMapper.getMaxOrder();
    }

    @Override
    public Menu getPreEntityByOrder(Integer condition, Integer order) {
        return menuMapper.getPreEntityByOrder(condition, order);
    }

    @Override
    public Menu getNextEntityByOrder(Integer condition, Integer order) {
        return menuMapper.getNextEntityByOrder(condition, order);
    }
}

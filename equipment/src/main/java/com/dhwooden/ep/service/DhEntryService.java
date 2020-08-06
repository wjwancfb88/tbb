package com.dhwooden.ep.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.DhEntry;
import com.baomidou.mybatisplus.service.IService;
import com.dhwooden.ep.modal.Equipment;
import com.dhwooden.ep.modal.Test;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2018-07-05
 */
public interface DhEntryService extends IService<DhEntry> {

    public Page<DhEntry> selectByPage(Page<DhEntry> page,Map eq);

    public  void updateByid(DhEntry dhEntry);

    public  void  insertTest(Test test);
	
}

package com.dhwooden.ep.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.DhEntry;
import com.dhwooden.ep.modal.DhMailbox;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2018-08-27
 */
public interface DhMailboxService extends IService<DhMailbox> {
    public Page<DhMailbox> selectByPage(Page<DhMailbox> page,Map eq);
}

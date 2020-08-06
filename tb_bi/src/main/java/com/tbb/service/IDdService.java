package com.tbb.service;

import com.tbb.entity.Dd;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
public interface IDdService extends IService<Dd> {
    int deleteAll();
}

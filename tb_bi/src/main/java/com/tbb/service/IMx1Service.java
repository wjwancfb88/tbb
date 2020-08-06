package com.tbb.service;

import com.baomidou.mybatisplus.service.IService;
import com.tbb.entity.Mx1;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
public interface IMx1Service extends IService<Mx1> {
    int deleteAll();
}

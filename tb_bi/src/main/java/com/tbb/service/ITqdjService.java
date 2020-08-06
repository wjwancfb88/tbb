package com.tbb.service;

import com.baomidou.mybatisplus.service.IService;
import com.tbb.entity.Tqdj;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
public interface ITqdjService extends IService<Tqdj> {
    int deleteAll();
}

package com.tbb.service;

import com.baomidou.mybatisplus.service.IService;
import com.tbb.entity.Wlyy;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cfb
 * @since 2020-04-02
 */
public interface IWlyyService extends IService<Wlyy> {
	int deleteAll();
}

package com.tbb.service;

import com.baomidou.mybatisplus.service.IService;
import com.tbb.entity.Cdmxmx;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
public interface ICdmxmxService extends IService<Cdmxmx> {
    int deleteAll(String createdOn);
    int insertList(List<Cdmxmx> list);
}

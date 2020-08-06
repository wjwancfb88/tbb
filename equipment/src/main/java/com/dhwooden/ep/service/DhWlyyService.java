package com.dhwooden.ep.service;

import com.baomidou.mybatisplus.service.IService;
import com.dhwooden.ep.modal.WLYY;

public interface DhWlyyService extends IService<WLYY> {
    WLYY selectWlyyById(String wlyyId);
    void deleteAll();
}

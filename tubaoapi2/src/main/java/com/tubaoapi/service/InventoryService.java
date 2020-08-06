package com.tubaoapi.service;

import com.tubaoapi.dao.InventoryDao;
import com.tubaoapi.dao.MaterialDao;
import com.tubaoapi.model.Inventory;
import com.tubaoapi.model.Material;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;
import com.tubaoapi.modules.persistence.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService extends BaseService<Inventory, String>{


	@Autowired
	private InventoryDao inventoryDao;





	@Override
	public BaseDao<Inventory, String> getDao() {
		return inventoryDao;
	}


	@Override
	public BaseSO<String> newSO() {
		return new MaterialSO();
	}
	
}

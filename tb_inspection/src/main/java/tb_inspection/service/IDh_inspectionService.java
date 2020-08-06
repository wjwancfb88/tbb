package tb_inspection.service;

import javax.servlet.http.HttpServletResponse;

import tb_inspection.entity.Dh_inspectionEntity;
import tb_inspection.entity.PageData;
import java.util.List;

public interface IDh_inspectionService {

	void add(Dh_inspectionEntity entity);
	
	void delete(Dh_inspectionEntity entity);
	
	void update(Dh_inspectionEntity entity);
	
	List<Dh_inspectionEntity> select(Dh_inspectionEntity entity);
	
	PageData<Dh_inspectionEntity> likeSelect(Dh_inspectionEntity entity);

	void batchAdd(List<Dh_inspectionEntity> list);
	
	void batchDelete(List<Dh_inspectionEntity> list);
	
	void batchUpdate(List<Dh_inspectionEntity> list);

    void exportExcel(Dh_inspectionEntity entity, HttpServletResponse response);

}

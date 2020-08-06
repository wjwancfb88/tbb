package tb_inspection.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tb_inspection.entity.Dh_inspectionEntity;

@Mapper
@Repository
public interface IDh_inspectionDao extends IBaseDao<Dh_inspectionEntity> {

}

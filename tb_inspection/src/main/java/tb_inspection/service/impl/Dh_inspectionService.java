package tb_inspection.service.impl;

import javax.servlet.http.HttpServletResponse;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tb_inspection.dao.IDh_inspectionDao;
import tb_inspection.service.IDh_inspectionService;
import tb_inspection.entity.PageData;
import tb_inspection.utils.ExcelUtil;
import tb_inspection.utils.PageUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import tb_inspection.entity.Dh_inspectionEntity;
import tb_inspection.utils.PageUtil;

@Service
public class Dh_inspectionService implements IDh_inspectionService {

	
	private IDh_inspectionDao dao;

	@Autowired
	public Dh_inspectionService(IDh_inspectionDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void add(Dh_inspectionEntity entity) {
		entity.setInspectionId(UUID.randomUUID().toString().replace("-", ""));
		dao.add(entity);
	}
	
	@Override
	public void delete(Dh_inspectionEntity entity) {
		dao.delete(entity);
	}
	
	@Override
	public void update(Dh_inspectionEntity entity) {
		dao.update(entity);
	}
	
	@Override
	public List<Dh_inspectionEntity> select(Dh_inspectionEntity entity) {
		return dao.select(entity);
	}
	
	@Override
	public PageData<Dh_inspectionEntity> likeSelect(Dh_inspectionEntity entity) {
		return PageUtil.getPageData(entity, dao);
	}

	@Override
	public void batchAdd(List<Dh_inspectionEntity> list) {
		dao.batchAdd(list);
	}

	@Override
	public void batchDelete(List<Dh_inspectionEntity> list) {
		dao.batchDelete(list);
	}

	@Override
	public void batchUpdate(List<Dh_inspectionEntity> list) {
		dao.batchUpdate(list);
	}
	
	
	@Override
	public void exportExcel(Dh_inspectionEntity entity, HttpServletResponse response) {

		/*// 获取头部信息（可以设置为动态）
		String[] headList = new String[] { "巡检时间","检查地点", "检查内容", "检查结果", "备注", "检查内容", "检查结果", "备注",
				"检查地点", "检查内容", "检查结果", "备注", "检查地点", "检查内容", "检查结果", "备注", "检查内容", "检查结果", "备注",
				"检查内容", "检查结果", "备注", "检查内容", "检查结果", "备注", "检查地点", "检查内容", "检查结果", "备注",
				"检查内容", "检查结果", "备注", "检查内容", "检查结果", "备注", "检查内容", "检查结果", "备注",
				"检查地点", "检查内容", "检查结果", "备注", "检查内容", "检查结果", "备注", "检查地点", "检查内容", "检查结果", "备注",
				"检查内容", "检查结果", "备注", "检查地点", "检查内容", "检查结果", "备注", "检查内容", "检查结果", "备注", "检查内容", "检查结果", "备注"};

		String[] headEngList = new String[]{ "creattime","sale", "sc", "scresult", "scremarks", "sp", "spresult", "spremarks", "sf", "sfa", "sfaresult", "sfaremarks", "tftc", "tftcl", "tftclresult", "tftclremarks", "tftcs", "tftcsresult", "tftcsremarks", "tftcm", "tftcmresult", "tftcmremarks", "tftcc", "tftccresult", "tftccremarks", "tfm", "tfml", "tfmlresult", "tfmlremarks", "tfms", "tfmsresult", "tfmsremarks", "tfmm", "tfmmresult", "tfmmremarks", "tfmc", "tfmcresult", "tfmcremarks", "ffm", "ffmc", "ffmcresult", "ffmcremarks", "ffmp", "ffmpresult", "ffmpremarks", "ifofm", "ifofmc", "ifofmcresult", "ifofmcremarks", "ifofmp", "ifofmpresult", "ifofmpremarks", "iffm", "iffmc", "iffmcresult", "iffmcremarks", "iffms", "iffmsresult", "iffmsremarks", "iffmm", "iffmmresult", "iffmmremarks"};

		String[] describeList = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
		
		//设置头部以及描述信息
        Map<String, String> headAndDescribeMap = new LinkedHashMap<>();
        for (int i = 0; i < headEngList.length; i++) {
            headAndDescribeMap.put(headEngList[i], describeList[i]);
        }

		ExcelUtil.exportExcel(entity, response, dao, headList, headAndDescribeMap);*/

		TemplateExportParams params = new TemplateExportParams("templates/inspection.xlsx");
		params.setHeadingStartRow(3);
		params.setHeadingRows(2);
		List<Dh_inspectionEntity> list = dao.likeSelect(entity);
		Dh_inspectionEntity dh_inspectionEntity = (Dh_inspectionEntity)list.get(0);
		if ("1".equals(dh_inspectionEntity.getFfmcresult())){
			dh_inspectionEntity.setFfmcresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getFfmcresult())){
			dh_inspectionEntity.setFfmcresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getFfmc2result())){
			dh_inspectionEntity.setFfmc2result("正常");
		}else if ("0".equals(dh_inspectionEntity.getFfmc2result())){
			dh_inspectionEntity.setFfmc2result("异常");
		}
		if ("1".equals(dh_inspectionEntity.getFfmpresult())){
			dh_inspectionEntity.setFfmpresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getFfmpresult())){
			dh_inspectionEntity.setFfmpresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getIffmcresult())){
			dh_inspectionEntity.setIffmcresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getIffmcresult())){
			dh_inspectionEntity.setIffmcresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getIffmmresult())){
			dh_inspectionEntity.setIffmmresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getIffmmresult())){
			dh_inspectionEntity.setIffmmresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getIfofmcresult())){
			dh_inspectionEntity.setIfofmcresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getIfofmcresult())){
			dh_inspectionEntity.setIfofmcresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getScresult())){
			dh_inspectionEntity.setScresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getScresult())){
			dh_inspectionEntity.setScresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getIffmsresult())){
			dh_inspectionEntity.setIffmsresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getIffmsresult())){
			dh_inspectionEntity.setIffmsresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getSfaresult())){
			dh_inspectionEntity.setSfaresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getSfaresult())){
			dh_inspectionEntity.setSfaresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getSpresult())){
			dh_inspectionEntity.setSpresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getSpresult())){
			dh_inspectionEntity.setSpresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getTfmcresult())){
			dh_inspectionEntity.setTfmcresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getTfmcresult())){
			dh_inspectionEntity.setTfmcresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getTfmlresult())){
			dh_inspectionEntity.setTfmlresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getTfmlresult())){
			dh_inspectionEntity.setTfmlresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getIfofmpresult())){
			dh_inspectionEntity.setIfofmpresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getIfofmpresult())){
			dh_inspectionEntity.setIfofmpresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getTfmmresult())){
			dh_inspectionEntity.setTfmmresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getTfmmresult())){
			dh_inspectionEntity.setTfmmresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getTfmsresult())){
			dh_inspectionEntity.setTfmsresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getTfmsresult())){
			dh_inspectionEntity.setTfmsresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getTftcsresult())){
			dh_inspectionEntity.setTftcsresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getTftcsresult())){
			dh_inspectionEntity.setTftcsresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getTftccresult())){
			dh_inspectionEntity.setTftccresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getTftccresult())){
			dh_inspectionEntity.setTftccresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getTftclresult())){
			dh_inspectionEntity.setTftclresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getTftclresult())){
			dh_inspectionEntity.setTftclresult("异常");
		}
		if ("1".equals(dh_inspectionEntity.getTftcmresult())){
			dh_inspectionEntity.setTftcmresult("正常");
		}else if ("0".equals(dh_inspectionEntity.getTftcmresult())){
			dh_inspectionEntity.setTftcmresult("异常");
		}


		Map<String, Object> map = new HashMap<String, Object>();
		map.put("creattime", dh_inspectionEntity.getCreattime());
		map.put("scresult", dh_inspectionEntity.getScresult());
		map.put("scremarks", dh_inspectionEntity.getScremarks());
		map.put("spresult", dh_inspectionEntity.getSpresult());
		map.put("spremarks", dh_inspectionEntity.getSpremarks());
		map.put("sfaresult", dh_inspectionEntity.getSfaresult());
		map.put("sfaremarks", dh_inspectionEntity.getSfaremarks());
		map.put("tfmlresult", dh_inspectionEntity.getTfmlresult());
		map.put("tfmlremarks", dh_inspectionEntity.getTfmlremarks());
		map.put("tfmsresult", dh_inspectionEntity.getTfmsresult());
		map.put("tfmsremarks", dh_inspectionEntity.getTfmsremarks());
		map.put("tfmmresult", dh_inspectionEntity.getTfmmresult());
		map.put("tfmmremarks", dh_inspectionEntity.getTfmmremarks());
		map.put("tfmcresult", dh_inspectionEntity.getTfmcresult());
		map.put("tfmcremarks", dh_inspectionEntity.getTfmcremarks());
		map.put("tftclresult", dh_inspectionEntity.getTftclresult());
		map.put("tftclremarks", dh_inspectionEntity.getTftclremarks());
		map.put("tftcsresult", dh_inspectionEntity.getTftcsresult());
		map.put("tftcsremarks", dh_inspectionEntity.getTftcsremarks());
		map.put("tftcmresult", dh_inspectionEntity.getTftcmresult());
		map.put("tftcmremarks", dh_inspectionEntity.getTftcmremarks());
		map.put("tftccresult", dh_inspectionEntity.getTftccresult());
		map.put("tftccremarks", dh_inspectionEntity.getTftccremarks());
		map.put("ffmcresult", dh_inspectionEntity.getFfmcresult());
		map.put("ffmcremarks", dh_inspectionEntity.getFfmcremarks());
		map.put("ffmc2result", dh_inspectionEntity.getFfmc2result());
		map.put("ffmc2remarks", dh_inspectionEntity.getFfmc2remarks());
		map.put("ffmpresult", dh_inspectionEntity.getFfmpresult());
		map.put("ffmpremarks", dh_inspectionEntity.getFfmpremarks());
		map.put("ifofmcresult", dh_inspectionEntity.getIfofmcresult());
		map.put("ifofmcremarks", dh_inspectionEntity.getIfofmcremarks());
		map.put("ifofmpresult", dh_inspectionEntity.getIfofmpresult());
		map.put("ifofmpremarks", dh_inspectionEntity.getIfofmpremarks());
		map.put("iffmcresult", dh_inspectionEntity.getIffmcresult());
		map.put("iffmcremarks", dh_inspectionEntity.getIffmcremarks());
		map.put("iffmsresult", dh_inspectionEntity.getIffmsresult());
		map.put("iffmsremarks", dh_inspectionEntity.getIffmsremarks());
		map.put("iffmmresult", dh_inspectionEntity.getIffmmresult());
		map.put("iffmmremarks", dh_inspectionEntity.getIffmmremarks());

		Workbook workbook = ExcelExportUtil.exportExcel(params,map);
		BufferedOutputStream out = null;
		String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "设备巡检登记表.xlsx";
		// 设置文件名
		try {
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			out = new BufferedOutputStream(response.getOutputStream());
			workbook.write(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				// 处理在磁盘上备份此工作簿的临时文件
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		Workbook workbook2 = ExcelExportUtil.exportExcel(params, Dh_inspectionEntity.class,
//				list, map);
//		File savefile = new File("D:/home/excel/");
//		if (!savefile.exists()) {
//			savefile.mkdirs();
//		}
//		try {
//
//			FileOutputStream fos = new FileOutputStream("D:/home/excel/inspection.xlsx");
//			workbook.write(fos);
//			fos.close();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
	}
}

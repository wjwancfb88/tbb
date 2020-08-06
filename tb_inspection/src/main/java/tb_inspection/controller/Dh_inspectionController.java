package tb_inspection.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tb_inspection.entity.CommonResult;
import tb_inspection.constant.ResultConstant;
import tb_inspection.service.IDh_inspectionService;

import tb_inspection.entity.Dh_inspectionEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*")
@RequestMapping("/dh_inspection")
public class Dh_inspectionController {


	private IDh_inspectionService service;

	@Autowired
	public Dh_inspectionController(IDh_inspectionService service) {
		this.service = service;
	}
	
	/**
	 * 查询
	 *
	 * @return
	 */
	@RequestMapping("/select")
	public CommonResult select(@RequestBody Dh_inspectionEntity entity) {
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG, service.select(entity));
	}

	/**
	 * 模糊查询
	 *
	 * @return
	 */
	@RequestMapping("/likeSelect")
	public CommonResult likeSelect(@RequestBody Dh_inspectionEntity entity) {
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG, service.likeSelect(entity));
	}

	/**
	 * 更新
	 *
	 * @return
	 */
	@RequestMapping("/update")
	public CommonResult update(@RequestBody Dh_inspectionEntity entity) {
		service.update(entity);
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);
	}

	/**
	 * 添加
	 *
	 * @return
	 */
	@RequestMapping("/add")
	public CommonResult add(@RequestBody Dh_inspectionEntity entity) {
		service.add(entity);
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);
	}

	/**
	 * 删除
	 *
	 * @return
	 */
	@RequestMapping("/delete")
	public CommonResult delete(@RequestBody Dh_inspectionEntity entity) {
		service.delete(entity);
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);
	}

	/**
	 * 批量增加
	 *
	 * @return
	 */
	@RequestMapping("/batchAdd")
	public CommonResult batchAdd(@RequestBody List<Dh_inspectionEntity> list) {
		service.batchAdd(list);
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);
	}

	/**
	 * 批量删除
	 *
	 * @return
	 */
	@RequestMapping("/batchDelete")
	public CommonResult batchDelete(@RequestBody List<Dh_inspectionEntity> list) {
		service.batchDelete(list);
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);
	}

	/**
	 * 批量更新
	 *
	 * @return
	 */
	@RequestMapping("/batchUpdate")
	public CommonResult batchUpdate(@RequestBody List<Dh_inspectionEntity> list) {
		service.batchUpdate(list);
		return new CommonResult(ResultConstant.SUCCCSS_CODE, ResultConstant.SUCCESS_MSG);
	}
	
	/**
	 * 导出excel
	 *
	 * @return
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(Dh_inspectionEntity entity, HttpServletResponse response) {
		service.exportExcel(entity, response);
	}

}

package com.tubaoapi.service;

import com.tubaoapi.dao.ManufactureRecBillDao;
import com.tubaoapi.dao.MaterialReqBillEntryDao;
import com.tubaoapi.model.*;
import com.tubaoapi.model.so.KDBdMultiMeasureUnitSO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.model.so.MeasureUnitSO;
import com.tubaoapi.modules.utils.DateUtils2;
import com.tubaoapi.modules.utils.RadomFour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ManufactureRecBillService {


	@Autowired
	private ManufactureRecBillDao manufactureRecBillDao;

	@Autowired
	private MaterialService materialService;

	@Autowired
	private KDBdMultiMeasureUnitService kdBdMultiMeasureUnitService;

	@Autowired
	private MeasureUnitService measureUnitService;
	@Autowired
	private ManufactureRecBillEntryService manufactureRecBillEntryService;

	public Integer  insertManufactureRecBill(List<BillFacture> list) {
		RadomFour radomFour=new RadomFour();
		String fnumber=radomFour.getFourNumBer("YG");
		ManufactureRecBill bill = new ManufactureRecBill();
		//倒冲标识
		bill.setFisbackflushsucceed(5L);
		//加工组织(MES 衣柜 新歌儿 门业)
		bill.setFprocessorgunitid("1roAAABg9lPM567U");
		//库存组织
		bill.setFstorageorgunitid("1roAAABg9lPM567U");
		//部门生产部门(EAS中选)
		bill.setFadminorgunitid("");
		//总数量
		bill.setFtotalqty(new BigDecimal(0));
		//总额
		bill.setFtotalamount(new BigDecimal(0));
		//是否生成凭证
		bill.setFfivouchered(0L);
		//总标准成本
		bill.setFtotalstandardcost(new BigDecimal(0));
		//总实际成本
		bill.setFtotalactualcost(new BigDecimal(0));
		//是否冲销
		bill.setFisreversed(0L);
		//事务类型
		bill.setFtransactiontypeid("DawAAAAPoCqwCNyn");
		//是否是初始化单据
		bill.setFisinitbill(0L);
		//业务日期(传过来)
		Date current = new Date();
		bill.setFbizdate(current);
		//月
		bill.setFmonth(Long.valueOf(DateUtils2.d2s(current, "yyyyMM")));
		//日
		bill.setFday(Long.valueOf(DateUtils2.d2s(current, "yyyyMMdd")));
		//成本中心(eas选)
		bill.setFcostcenterorgunitid(null);
		//单据状态(历史版本=-3,变更中=-2,=-1,新增=0,保存=1,提交=2,作废=3,审核=4,下达=5,冻结=6,关闭=7,完工=8,完成=90,发布=10,结案=11)
		bill.setFbasestatus(0L);
		//业务类型普通生产入库
		bill.setFbiztypeid("Nz878AEgEADgAABFwKg/GiQHQ1w=");
		//单据类型
		bill.setFbilltypeid("50957179-0105-1000-e000-0167c0a812fd463ED552");
		//年
		bill.setFyear(Long.valueOf(DateUtils2.d2s(current, "yyyy")));
		//业务期间
		bill.setFperiod(Long.valueOf(DateUtils2.d2s(current, "MM")));
		//单据编号（mes传过来）
		bill.setFnumber(fnumber);
		//备注（传过来）
		bill.setFdescription("ceshi");
		//是否生效
		bill.setFhaseffected(0L);
		//创建时间
		bill.setFcreatetime(new Date());
		//控制单元
		bill.setFcontrolunitid("1roAAAAABCbM567U");
		Integer success=manufactureRecBillDao.insertSelective(bill);
		Long seq=0L;
		for(BillFacture facturebill:list) {
			seq++;
			BigDecimal qty=facturebill.getQty();
			String fid= bill.getFid();
			String materialId=facturebill.getMid();
			//Map<String,String> map=getUnit(materialId,qty);
           /* AssistunitHelper helper=new AssistunitHelper();
            Map<String,String> map=helper.getUnit(materialId,qty)*/;
//             String assistQty=map.get("assistQty");
//            String baseCovsRate=map.get("baseCovsRate");
//            String assistunit=map.get("assistunit");
//            String baseunit=map.get("baseunit");
			String assistunit="";
			String baseunit="";
			MaterialSO materialSO=new MaterialSO();
			if(materialId!=null&&materialId!=""){
				materialSO.setId(materialId);
				List<Material>  material=materialService.findBySO(materialSO);
				if(material!=null&&material.size()>0){
					assistunit=material.get(0).getAssistUnitId();
					baseunit=material.get(0).getBaseUnitId();
				}
			}
			KDBdMultiMeasureUnitSO so = new KDBdMultiMeasureUnitSO();
			so.setMaterialId(materialId);
			so.setMeasureUnitId(baseunit);
			List<KDBdMultiMeasureUnit> l = kdBdMultiMeasureUnitService.findBySO(so);
			BigDecimal baseCovsRate=l.size()>0?l.get(0).getBaseConvsRate():null;
			BigDecimal assistQty= BigDecimal.valueOf(0);
			if(l.size()>0){
				KDBdMultiMeasureUnit multiUnit=l.get(0);
				if(multiUnit!=null &&  multiUnit.getBaseConvsRate()!=null && multiUnit.getBaseConvsRate().doubleValue()>0){
					assistQty = qty.divide(multiUnit.getBaseConvsRate(), multiUnit.getQtyPrecision(), BigDecimal.ROUND_HALF_UP);
				}
			}
			MeasureUnitSO measureUnitSO=new MeasureUnitSO();
			List<MeasureUnit> measureUnit=measureUnitService.findBySO(measureUnitSO);


			ManufactureRecBillEntry entry = new ManufactureRecBillEntry();
			//生产入库单头
			entry.setFparentid(fid);
			//接受数量
			entry.setFrecqty(new BigDecimal(0));
			//接受基本数量
			entry.setFbaserecqty(new BigDecimal(0));
			//生产订单行号
			entry.setFmanubillentryseq(0l);
			//生产订单id
			entry.setFmanubillid(facturebill.getJobID());
			//应收数量
			entry.setFreverseqty(new BigDecimal(0));
			//销售订单行号
			entry.setFsaleorderentryseq(seq);
			//单价
			entry.setFprice(new BigDecimal(0));
			//金额
			entry.setFamount(new BigDecimal(0));
			//业务日期（与单据头一致）
			entry.setFbizdate(current);
			//主制部门（eas选 和单据头一致）
			entry.setFadminorgunitid("");
			//成本中心(eas选 和单据头一致）
			entry.setFcostcenterorgunitid("");
			//库存组织
			entry.setFstorageorgunitid("1roAAABg9lPM567U");
			//財務組織
			entry.setFcompanyorgunitid("1roAAAAABZrM567U");
			//仓库
			entry.setFwarehouseid("1roAAABlG0676fiu");
			//数量（mes川归来的数量）
			entry.setFqty(qty);
			//辅助数量(根据qty转换)
			entry.setFassistqty(assistQty);
			//基本数量单位(与qty)
			entry.setFbaseqty(qty);
			//冲销数量
			entry.setFreverseqty(new BigDecimal(0));
			//退货数量
			entry.setFreturnsqty(new BigDecimal(0));
			//单位标准成本
			entry.setFunitstandardcost(new BigDecimal(0));
			//标准成本
			entry.setFstandardcost(new BigDecimal(0));
			//单位实际成本
			entry.setFunitactualcost(new BigDecimal(0));
			//实际成本
			entry.setFactualcost(new BigDecimal(0));
			//是否赠品
			entry.setFispresent(0L);
			//冲销基本数量
			entry.setFreversebaseqty(new BigDecimal(0));
			//退货基本数量
			entry.setFreturnbaseqty(new BigDecimal(0));
			//物料id(传过来)
			entry.setFmaterialid(materialId);
			//计量单位（查T_BD_MeasureUnit）
			entry.setFunitid(baseunit);
			//辅助计量单位换算系数

			entry.setFasscoefficient(baseCovsRate);

			//基本状态(与单据头一直)
			entry.setFbasestatus(0L);
			//未关联数量("等于qty)
			entry.setFassociateqty(new BigDecimal(0));
			//基本计量单位(T_BD_MeasureUnit等于FUnitID)

			entry.setFbaseunitid(baseunit);

			//辅助计量单位 去物料基础资料取
			entry.setFassistunitid(assistunit);


			//备注(传过来)
			entry.setFremark("ceshi");
			//自己生
			entry.setFseq(Long.valueOf(seq));
			manufactureRecBillEntryService.insertManufactureRecBillentry(entry);

		}
			return success;
	}

	
}

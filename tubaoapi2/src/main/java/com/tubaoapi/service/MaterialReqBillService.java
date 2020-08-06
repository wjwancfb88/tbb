package com.tubaoapi.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tubaoapi.dao.*;
import com.tubaoapi.model.*;
import com.tubaoapi.model.so.BaseSO;
import com.tubaoapi.model.so.KDBdMultiMeasureUnitSO;
import com.tubaoapi.model.so.MaterialSO;
import com.tubaoapi.modules.persistence.dao.BaseDao;
import com.tubaoapi.modules.persistence.pagination.model.Page;
import com.tubaoapi.modules.persistence.pagination.model.PageRequest;
import com.tubaoapi.modules.persistence.pagination.model.PageRowBounds;
import com.tubaoapi.modules.persistence.service.BaseService;
import com.tubaoapi.modules.utils.DateUtils2;
import com.tubaoapi.modules.utils.RadomFour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class MaterialReqBillService  extends BaseService<BillMaterial, String>{


	@Autowired
	private MaterialReqBillDao materialReqBillDao;

	@Autowired
	private MaterialReqBillEntryDao materialReqBillEntryDao;

	@Autowired
	private KDBdMultiMeasureUnitService kdBdMultiMeasureUnitDao;

	@Autowired
	private MaterialReqBillPriceInfoDao materialReqBillPriceInfoDao;

	@Autowired
	private MaterialDao materialDao;


	public String  insertMaterialReqBill(List<BillMaterial> list) {

			Date current = new Date();
			RadomFour radomFour=new RadomFour();
			String fnumber=radomFour.getFourNumBer("YG");
			MaterialReqBill bill = new MaterialReqBill();
			//采购类别
			bill.setFpurchasetype(0l);
			// 倒冲标识
			bill.setFisbackflush(0l);
			// 倒冲单据
			bill.setFbackflushbillid(null);
			// 倒冲单据类型
			bill.setFbackflushbilltypeid(null);
			//供应方财务组织
			bill.setFsupplycompanyorgunitid("1roAAAAABZrM567U");
			//供应方库存组织
			bill.setFsupplystoreorgunitid("1roAAAE8RVnM567U");
			//需求方财务组织
			bill.setFdemandcompanyorgunitid("1roAAAAABZrM567U");
			//库存组织
			bill.setFstorageorgunitid("1roAAAE8RVnM567U");
			//部门
			bill.setFadminorgunitid(null);
			//库管员
			bill.setFstockerid(null);
			//凭证
			bill.setFvoucherid(null);
			//总数量
			bill.setFtotalqty(new BigDecimal(0));
			//总金额
			bill.setFtotalamount(new BigDecimal(0));
			//是否生成凭证
			bill.setFfivouchered(0L);
			//总标准成本
			bill.setFtotalstandardcost(new BigDecimal(0));
			//总实际成本
			bill.setFtotalactualcost(new BigDecimal(0));
			//是否冲销
			bill.setFisreversed(0L);
			//事务类型(领发料出库-参与生产成本核算)
			bill.setFtransactiontypeid("DawAAAAPoCuwCNyn");
			//是否是初始化单
			bill.setFisinitbill(0l);
			//业务日期

			bill.setFbizdate(current);
			//月
			bill.setFmonth(Long.valueOf(DateUtils2.d2s(current, "yyyyMM")));
			//日
			bill.setFday(Long.valueOf(DateUtils2.d2s(current, "yyyyMMdd")));
			//年
			bill.setFyear(Long.valueOf(DateUtils2.d2s(current, "yyyy")));
			//业务期间
			bill.setFperiod(Long.valueOf(DateUtils2.d2s(current, "MM")));
			//成本中心
			bill.setFcostcenterorgunitid(null);
			//审核时间
			bill.setFaudittime(null);
			//单据状态
			bill.setFbasestatus(0L);
			//业务类型(普通领料)
			bill.setFbiztypeid("0rSFjAEeEADgAAyMwKgSQiQHQ1w=");
			//来源单据类型
			bill.setFsourcebilltypeid(null);
			//单据类型
			bill.setFbilltypeid("50957179-0105-1000-e000-0163c0a812fd463ED552");
			//修改人
			bill.setFmodifierid(null);
			//修改时间
			bill.setFmodificationtime(null);
			//单据编号(MES传值)
			bill.setFnumber(fnumber);
			//经手人
			bill.setFhandlerid(null);
			//备注(MES传值)
			bill.setFdescription("ceshi");
			//是否生效
			bill.setFhaseffected(0L);
			//审核人
			bill.setFauditorid(null);
			//原始单据id
			bill.setFsourcebillid(null);
			//控制单元(德华兔宝宝生产基地(虚))
			bill.setFcontrolunitid("1roAAAAABCbM567U");
			Integer success = materialReqBillDao.insertSelective(bill);
		for(BillMaterial mbill:list) {

			String materialId=mbill.getMid();
			String remark=mbill.getMemo();
			String  unit=mbill.getUnit();
			BigDecimal qty=mbill.getQty();
			String seq=mbill.getSN();

			String assistunit="";
			String baseunit="";
			MaterialSO materialSO=new MaterialSO();
			if(materialId!=null&&materialId!=""){
				materialSO.setId(materialId);
				List<Material>  material=materialDao.findBySO(materialSO);
				if(material.size()>0){
					assistunit=material.get(0).getAssistUnitId();
					baseunit=material.get(0).getBaseUnitId();
				}
			}

			KDBdMultiMeasureUnitSO so = new KDBdMultiMeasureUnitSO();
			so.setMaterialId(materialId);
			so.setMeasureUnitId(baseunit);
			List<KDBdMultiMeasureUnit> l = kdBdMultiMeasureUnitDao.findBySO(so);
			BigDecimal baseCovsRate=l.size()>0?l.get(0).getBaseConvsRate():null;
			BigDecimal assistQty= BigDecimal.valueOf(0);
			if(l.size()>0){
				KDBdMultiMeasureUnit multiUnit=l.get(0);
				if(multiUnit!=null &&  multiUnit.getBaseConvsRate()!=null && multiUnit.getBaseConvsRate().doubleValue()>0){
					assistQty = mbill.getQty().divide(multiUnit.getBaseConvsRate(), multiUnit.getQtyPrecision(), BigDecimal.ROUND_HALF_UP);
				}
			}
			//领料出库单分录
			MaterialReqBillEntry materialReqBillEntry = new MaterialReqBillEntry();
			//领料出库单头
			materialReqBillEntry.setFparentid(bill.getFid());
			//可退货基本数量(fqty)
			//materialReqBillEntry.setFunreturnedbaseqty("");
			//应发数量
			materialReqBillEntry.setFissueqty(new BigDecimal(0));
			//应发基本数量
			materialReqBillEntry.setFbaseissueqty(new BigDecimal(0));
			//核心单据编MA(fnumber)  单据编号(MES传值)
			materialReqBillEntry.setFcorebillnumber("ceshi");
			//核心单据类型
			materialReqBillEntry.setFcorebilltypeid("50957179-0105-1000-e000-0163c0a812fd463ED552");
			//核心单据id
			materialReqBillEntry.setFcorebillid(bill.getFid());
			//核心单据分录ID(自己的fid)

			//核心单据分录序列号seq(自己的seq)
			materialReqBillEntry.setFsourcebillentryseq(Long.valueOf(seq));
			//委外未核销数量
			materialReqBillEntry.setFsubunwriteoffqty(new BigDecimal(0));
			//委外未核销基本数量
			materialReqBillEntry.setFsubunwriteoffbaseqty(new BigDecimal(0));
			//委外已经核销数量
			materialReqBillEntry.setFsubwrittenoffqty(new BigDecimal(0));
			//委外已经核销基本数量
			materialReqBillEntry.setFsubwrittenoffqty(new BigDecimal(0));
			//分配标准
			materialReqBillEntry.setFisadmeasure(0L);
			//返工
			materialReqBillEntry.setFisrework(0L);
			materialReqBillEntry.setFcoreorderentryseq(0L);
			//已核销金额
			materialReqBillEntry.setFscwrittenoffamount(new BigDecimal(0));
			//未核销金额
			materialReqBillEntry.setFscunwrittenoffamount(new BigDecimal(0));
			//单价
			materialReqBillEntry.setFprice(new BigDecimal(0));
			//金额
			materialReqBillEntry.setFamount(new BigDecimal(0));
			//供方仓库（有问题）
			materialReqBillEntry.setFsupplywarehouseid("");
			//结算价
			materialReqBillEntry.setFsettleprice(new BigDecimal(0));
			//业务日期
			materialReqBillEntry.setFbizdate(current);
			//主制部门(与单据头一致)
			materialReqBillEntry.setFadminorgunitid("");
			//成本中心(与单据头一致)
			materialReqBillEntry.setFcostcenterorgunitid("");
			//库存组织
			materialReqBillEntry.setFstorageorgunitid("1roAAAE8RVnM567U");
			//财务组织
			materialReqBillEntry.setFcompanyorgunitid("1roAAAAABZrM567U");
			//仓库（不确定）
			materialReqBillEntry.setFwarehouseid("");
			//领料数量（不确定）
			materialReqBillEntry.setFqty(qty);
			//辅助数量（妆花）
			materialReqBillEntry.setFassistqty(assistQty);
			//基本数量单位（等于qty）
			materialReqBillEntry.setFbaseqty(qty);
			//冲销数量
			materialReqBillEntry.setFreverseqty(new BigDecimal(0));
			//退货数量
			materialReqBillEntry.setFreturnsqty(new BigDecimal(0));
			//单位标准成本
			materialReqBillEntry.setFunitstandardcost(new BigDecimal(0));
			//标准成本
			materialReqBillEntry.setFstandardcost(new BigDecimal(0));
			//实际成本
			materialReqBillEntry.setFunitactualcost(new BigDecimal(0));
			//是否赠品
			materialReqBillEntry.setFispresent(0L);
			//生产日期
			materialReqBillEntry.setFmfg(null);
			//到期日期
			materialReqBillEntry.setFexp(null);
			//冲销基本数量
			materialReqBillEntry.setFreversebaseqty(new BigDecimal(0));
			//退货基本数量
			materialReqBillEntry.setFreturnbaseqty(new BigDecimal(0));
			//物料id(mes传)
			materialReqBillEntry.setFmaterialid(materialId);
			//计量单位
			materialReqBillEntry.setFunitid(baseunit);
            //辅助计量单位换算系数(multiUnit.getBaseConvsRate())
			materialReqBillEntry.setFasscoefficient(baseCovsRate);
			//基本状态
			materialReqBillEntry.setFbasestatus(0l);
			//未关联数量
			materialReqBillEntry.setFassociateqty(new BigDecimal(0));
			//基本计量单位(物料的基本单位)
			materialReqBillEntry.setFbaseunitid(baseunit);
			//基本辅助单位(物料的辅助单位)
			materialReqBillEntry.setFassistunitid(assistunit);
			//备注（传过来）
			materialReqBillEntry.setFremark("ceshi");
			//单据分录序列号
			materialReqBillEntry.setFseq(Long.valueOf(seq));
			materialReqBillEntryDao.insertSelective(materialReqBillEntry);
			MaterialReqBillPriceInfo info = new MaterialReqBillPriceInfo();
			//领料出库单分录ID
			info.setFparentid(bill.getFid());
			//单价
			info.setFprice(new BigDecimal(0));
			//折扣方式
			info.setFdiscounttype(0l);
			//z折扣率
			info.setFdiscount(new BigDecimal(0));
			//实际单价
			info.setFrealprice(new BigDecimal(0));
			//税率
			info.setFtaxrate(new BigDecimal(0));
			//含税单价
			info.setFtaxprice(new BigDecimal(0));
			//实际含税单价FUnitID
			info.setFrealtaxprice(new BigDecimal(0));
			//领料出库分录id
			info.setFmaterialreqentryid(materialReqBillEntry.getFid());
			//物料id(传过来)
			info.setFmaterialid(materialId);
			//辅助属性（分录的unitid）
			info.setFunitid(baseunit);
			//辅助计量单位换算系数(对应分录的换算系数)
			info.setFasscoefficient(new BigDecimal(0));
			//基本状态(分录的状态)
			info.setFbasestatus(0L);
			//基本计量单位
			info.setFbaseunitid(baseunit);
			//辅助计量单位
			info.setFassistunitid(assistunit);
			//备注(分录的备注)
			info.setFremark("ceshi");
			materialReqBillPriceInfoDao.insertSelective(info);


		}
		return fnumber;
	}


	@Override
	public BaseDao<BillMaterial, String> getDao() {
		return null;
	}

	@Override
	public BaseSO<String> newSO() {
		return null;
	}
}

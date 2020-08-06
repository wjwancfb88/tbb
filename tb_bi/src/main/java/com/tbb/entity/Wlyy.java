package com.tbb.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cfb
 * @since 2020-04-02
 */
@TableName("E__WLYY2")
public class Wlyy extends Model<Wlyy> {

    private static final long serialVersionUID = 1L;

    /**
     * 网络预约id
     */
    @TableId("C__WLYYID")
	private String cWlyyid;
    /**
     * 标题
     */
	@TableField("C__BIAOTI")
	private String cBiaoti;
    /**
     * 城市
     */
	@TableField("C__CHENGSHI")
	private String cChengshi;
    /**
     * 电商部判断意向
     */
	@TableField("C__DIANSHANGBUPANDUANYIXIANG")
	private String cDianshangbupanduanyixiang;
    /**
     * 定制产品
     */
	@TableField("C__DINGZHICHANPIN")
	private String cDingzhichanpin;
    /**
     * 定制意愿
     */
	@TableField("C__DINGZHIYIYUAN")
	private String cDingzhiyiyuan;
    /**
     * 分配状态
     */
	@TableField("C__FENPEIZHUANGTAI")
	private String cFenpeizhuangtai;
    /**
     * 跟单客服备注
     */
	@TableField("C__GENDANKEFUBEIZHU")
	private String cGendankefubeizhu;
    /**
     * 跟进次数
     */
	@TableField("C__GENJINCISHU")
	private String cGenjincishu;
    /**
     * 回访客服备注
     */
	@TableField("C__HUIFANGKEFUBEIZHU")
	private String cHuifangkefubeizhu;
    /**
     * 活动名称
     */
	@TableField("C__HUODONG")
	private String cHuodong;
    /**
     * 客户
     */
	@TableField("C__KEHU")
	private String cKehu;
    /**
     * 客户等级
     */
	@TableField("C__KEHUDENGJI")
	private String cKehudengji;
    /**
     * 客户地址
     */
	@TableField("C__KEHUDIZHI")
	private String cKehudizhi;
    /**
     * 客户手机
     */
	@TableField("C__KEHUSHOUJI")
	private String cKehushouji;
    /**
     * 客户所在城市
     */
	@TableField("C__KEHUSUOZAICHENGSHI")
	private String cKehusuozaichengshi;
    /**
     * 客户姓名
     */
	@TableField("C__KEHUXINGMING")
	private String cKehuxingming;
    /**
     * 客户需求
     */
	@TableField("C__KEHUXUQIU")
	private String cKehuxuqiu;
    /**
     * 来源
     */
	@TableField("C__LAIYUAN")
	private String cLaiyuan;
    /**
     * 最近分配时间
     */
	@TableField("C__LASTASSIGNON")
	private String cLastassignon;
    /**
     * 订单判断状态
     */
	@TableField("C__LEIXING")
	private String cLeixing;
    /**
     * 省
     */
	@TableField("C__SHENG")
	private String cSheng;
    /**
     * 市
     */
	@TableField("C__SHI")
	private String cShi;
    /**
     * 推广平台
     */
	@TableField("C__TUIGUANGPINGTAI")
	private String cTuiguangpingtai;
    /**
     * 来源分类
     */
	@TableField("C__TYPE")
	private String cType;
    /**
     * 活动链接
     */
	@TableField("C__URL")
	private String cUrl;
    /**
     * 县区
     */
	@TableField("C__XIANQU")
	private String cXianqu;
    /**
     * 小区
     */
	@TableField("C__XIAOQU")
	private String cXiaoqu;
    /**
     * 意向分类
     */
	@TableField("C__YIXIANGFENLEI")
	private String cYixiangfenlei;
    /**
     * 意向经销商
     */
	@TableField("C__YIXIANGJINGXIAOSHANG")
	private String cYixiangjingxiaoshang;
    /**
     * 预算范围
     */
	@TableField("C__YUSUANFANWEI")
	private String cYusuanfanwei;
    /**
     * 状态
     */
	@TableField("C__ZHUANGTAI")
	private String cZhuangtai;
    /**
     * 装修阶段
     */
	@TableField("C__ZHUANGXIUJIEDUAN")
	private String cZhuangxiujieduan;
    /**
     * 最近处理时间
     */
	@TableField("C__ZUIJINCHULISHIJIAN")
	private String cZuijinchulishijian;
    /**
     * 最近跟进内容
     */
	@TableField("C__ZUIJINGENJINNEIRONG")
	private String cZuijingenjinneirong;
    /**
     * 创建者
     */
	@TableField("CREATED_BY")
	private String createdBy;
    /**
     * 创建时间
     */
	@TableField("CREATED_ON")
	private String createdOn;
    /**
     * 是否删除
     */
	@TableField("IS_DELETED")
	private String isDeleted;
    /**
     * 最后修改者
     */
	@TableField("MODIFIED_BY")
	private String modifiedBy;
    /**
     * 最后修改时间
     */
	@TableField("MODIFIED_ON")
	private String modifiedOn;
    /**
     * 所属部门
     */
	@TableField("OWNING_BUSINESS_UNIT")
	private String owningBusinessUnit;
    /**
     * 所属用户
     */
	@TableField("OWNING_USER")
	private String owningUser;


	public String getCWlyyid() {
		return cWlyyid;
	}

	public Wlyy setCWlyyid(String cWlyyid) {
		this.cWlyyid = cWlyyid;
		return this;
	}

	public String getCBiaoti() {
		return cBiaoti;
	}

	public Wlyy setCBiaoti(String cBiaoti) {
		this.cBiaoti = cBiaoti;
		return this;
	}

	public String getCChengshi() {
		return cChengshi;
	}

	public Wlyy setCChengshi(String cChengshi) {
		this.cChengshi = cChengshi;
		return this;
	}

	public String getCDianshangbupanduanyixiang() {
		return cDianshangbupanduanyixiang;
	}

	public Wlyy setCDianshangbupanduanyixiang(String cDianshangbupanduanyixiang) {
		this.cDianshangbupanduanyixiang = cDianshangbupanduanyixiang;
		return this;
	}

	public String getCDingzhichanpin() {
		return cDingzhichanpin;
	}

	public Wlyy setCDingzhichanpin(String cDingzhichanpin) {
		this.cDingzhichanpin = cDingzhichanpin;
		return this;
	}

	public String getCDingzhiyiyuan() {
		return cDingzhiyiyuan;
	}

	public Wlyy setCDingzhiyiyuan(String cDingzhiyiyuan) {
		this.cDingzhiyiyuan = cDingzhiyiyuan;
		return this;
	}

	public String getCFenpeizhuangtai() {
		return cFenpeizhuangtai;
	}

	public Wlyy setCFenpeizhuangtai(String cFenpeizhuangtai) {
		this.cFenpeizhuangtai = cFenpeizhuangtai;
		return this;
	}

	public String getCGendankefubeizhu() {
		return cGendankefubeizhu;
	}

	public Wlyy setCGendankefubeizhu(String cGendankefubeizhu) {
		this.cGendankefubeizhu = cGendankefubeizhu;
		return this;
	}

	public String getCGenjincishu() {
		return cGenjincishu;
	}

	public Wlyy setCGenjincishu(String cGenjincishu) {
		this.cGenjincishu = cGenjincishu;
		return this;
	}

	public String getCHuifangkefubeizhu() {
		return cHuifangkefubeizhu;
	}

	public Wlyy setCHuifangkefubeizhu(String cHuifangkefubeizhu) {
		this.cHuifangkefubeizhu = cHuifangkefubeizhu;
		return this;
	}

	public String getCHuodong() {
		return cHuodong;
	}

	public Wlyy setCHuodong(String cHuodong) {
		this.cHuodong = cHuodong;
		return this;
	}

	public String getCKehu() {
		return cKehu;
	}

	public Wlyy setCKehu(String cKehu) {
		this.cKehu = cKehu;
		return this;
	}

	public String getCKehudengji() {
		return cKehudengji;
	}

	public Wlyy setCKehudengji(String cKehudengji) {
		this.cKehudengji = cKehudengji;
		return this;
	}

	public String getCKehudizhi() {
		return cKehudizhi;
	}

	public Wlyy setCKehudizhi(String cKehudizhi) {
		this.cKehudizhi = cKehudizhi;
		return this;
	}

	public String getCKehushouji() {
		return cKehushouji;
	}

	public Wlyy setCKehushouji(String cKehushouji) {
		this.cKehushouji = cKehushouji;
		return this;
	}

	public String getCKehusuozaichengshi() {
		return cKehusuozaichengshi;
	}

	public Wlyy setCKehusuozaichengshi(String cKehusuozaichengshi) {
		this.cKehusuozaichengshi = cKehusuozaichengshi;
		return this;
	}

	public String getCKehuxingming() {
		return cKehuxingming;
	}

	public Wlyy setCKehuxingming(String cKehuxingming) {
		this.cKehuxingming = cKehuxingming;
		return this;
	}

	public String getCKehuxuqiu() {
		return cKehuxuqiu;
	}

	public Wlyy setCKehuxuqiu(String cKehuxuqiu) {
		this.cKehuxuqiu = cKehuxuqiu;
		return this;
	}

	public String getCLaiyuan() {
		return cLaiyuan;
	}

	public Wlyy setCLaiyuan(String cLaiyuan) {
		this.cLaiyuan = cLaiyuan;
		return this;
	}

	public String getCLastassignon() {
		return cLastassignon;
	}

	public Wlyy setCLastassignon(String cLastassignon) {
		this.cLastassignon = cLastassignon;
		return this;
	}

	public String getCLeixing() {
		return cLeixing;
	}

	public Wlyy setCLeixing(String cLeixing) {
		this.cLeixing = cLeixing;
		return this;
	}

	public String getCSheng() {
		return cSheng;
	}

	public Wlyy setCSheng(String cSheng) {
		this.cSheng = cSheng;
		return this;
	}

	public String getCShi() {
		return cShi;
	}

	public Wlyy setCShi(String cShi) {
		this.cShi = cShi;
		return this;
	}

	public String getCTuiguangpingtai() {
		return cTuiguangpingtai;
	}

	public Wlyy setCTuiguangpingtai(String cTuiguangpingtai) {
		this.cTuiguangpingtai = cTuiguangpingtai;
		return this;
	}

	public String getCType() {
		return cType;
	}

	public Wlyy setCType(String cType) {
		this.cType = cType;
		return this;
	}

	public String getCUrl() {
		return cUrl;
	}

	public Wlyy setCUrl(String cUrl) {
		this.cUrl = cUrl;
		return this;
	}

	public String getCXianqu() {
		return cXianqu;
	}

	public Wlyy setCXianqu(String cXianqu) {
		this.cXianqu = cXianqu;
		return this;
	}

	public String getCXiaoqu() {
		return cXiaoqu;
	}

	public Wlyy setCXiaoqu(String cXiaoqu) {
		this.cXiaoqu = cXiaoqu;
		return this;
	}

	public String getCYixiangfenlei() {
		return cYixiangfenlei;
	}

	public Wlyy setCYixiangfenlei(String cYixiangfenlei) {
		this.cYixiangfenlei = cYixiangfenlei;
		return this;
	}

	public String getCYixiangjingxiaoshang() {
		return cYixiangjingxiaoshang;
	}

	public Wlyy setCYixiangjingxiaoshang(String cYixiangjingxiaoshang) {
		this.cYixiangjingxiaoshang = cYixiangjingxiaoshang;
		return this;
	}

	public String getCYusuanfanwei() {
		return cYusuanfanwei;
	}

	public Wlyy setCYusuanfanwei(String cYusuanfanwei) {
		this.cYusuanfanwei = cYusuanfanwei;
		return this;
	}

	public String getCZhuangtai() {
		return cZhuangtai;
	}

	public Wlyy setCZhuangtai(String cZhuangtai) {
		this.cZhuangtai = cZhuangtai;
		return this;
	}

	public String getCZhuangxiujieduan() {
		return cZhuangxiujieduan;
	}

	public Wlyy setCZhuangxiujieduan(String cZhuangxiujieduan) {
		this.cZhuangxiujieduan = cZhuangxiujieduan;
		return this;
	}

	public String getCZuijinchulishijian() {
		return cZuijinchulishijian;
	}

	public Wlyy setCZuijinchulishijian(String cZuijinchulishijian) {
		this.cZuijinchulishijian = cZuijinchulishijian;
		return this;
	}

	public String getCZuijingenjinneirong() {
		return cZuijingenjinneirong;
	}

	public Wlyy setCZuijingenjinneirong(String cZuijingenjinneirong) {
		this.cZuijingenjinneirong = cZuijingenjinneirong;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Wlyy setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public Wlyy setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public Wlyy setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
		return this;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public Wlyy setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
		return this;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public Wlyy setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
		return this;
	}

	public String getOwningBusinessUnit() {
		return owningBusinessUnit;
	}

	public Wlyy setOwningBusinessUnit(String owningBusinessUnit) {
		this.owningBusinessUnit = owningBusinessUnit;
		return this;
	}

	public String getOwningUser() {
		return owningUser;
	}

	public Wlyy setOwningUser(String owningUser) {
		this.owningUser = owningUser;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.cWlyyid;
	}

	@Override
	public String toString() {
		return "Wlyy{" +
				"cWlyyid='" + cWlyyid + '\'' +
				", cBiaoti='" + cBiaoti + '\'' +
				", cChengshi='" + cChengshi + '\'' +
				", cDianshangbupanduanyixiang='" + cDianshangbupanduanyixiang + '\'' +
				", cDingzhichanpin='" + cDingzhichanpin + '\'' +
				", cDingzhiyiyuan='" + cDingzhiyiyuan + '\'' +
				", cFenpeizhuangtai='" + cFenpeizhuangtai + '\'' +
				", cGendankefubeizhu='" + cGendankefubeizhu + '\'' +
				", cGenjincishu='" + cGenjincishu + '\'' +
				", cHuifangkefubeizhu='" + cHuifangkefubeizhu + '\'' +
				", cHuodong='" + cHuodong + '\'' +
				", cKehu='" + cKehu + '\'' +
				", cKehudengji='" + cKehudengji + '\'' +
				", cKehudizhi='" + cKehudizhi + '\'' +
				", cKehushouji='" + cKehushouji + '\'' +
				", cKehusuozaichengshi='" + cKehusuozaichengshi + '\'' +
				", cKehuxingming='" + cKehuxingming + '\'' +
				", cKehuxuqiu='" + cKehuxuqiu + '\'' +
				", cLaiyuan='" + cLaiyuan + '\'' +
				", cLastassignon='" + cLastassignon + '\'' +
				", cLeixing='" + cLeixing + '\'' +
				", cSheng='" + cSheng + '\'' +
				", cShi='" + cShi + '\'' +
				", cTuiguangpingtai='" + cTuiguangpingtai + '\'' +
				", cType='" + cType + '\'' +
				", cUrl='" + cUrl + '\'' +
				", cXianqu='" + cXianqu + '\'' +
				", cXiaoqu='" + cXiaoqu + '\'' +
				", cYixiangfenlei='" + cYixiangfenlei + '\'' +
				", cYixiangjingxiaoshang='" + cYixiangjingxiaoshang + '\'' +
				", cYusuanfanwei='" + cYusuanfanwei + '\'' +
				", cZhuangtai='" + cZhuangtai + '\'' +
				", cZhuangxiujieduan='" + cZhuangxiujieduan + '\'' +
				", cZuijinchulishijian='" + cZuijinchulishijian + '\'' +
				", cZuijingenjinneirong='" + cZuijingenjinneirong + '\'' +
				", createdBy='" + createdBy + '\'' +
				", createdOn='" + createdOn + '\'' +
				", isDeleted='" + isDeleted + '\'' +
				", modifiedBy='" + modifiedBy + '\'' +
				", modifiedOn='" + modifiedOn + '\'' +
				", owningBusinessUnit='" + owningBusinessUnit + '\'' +
				", owningUser='" + owningUser + '\'' +
				'}';
	}
}

package com.tbb.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author cfb
 * @since 2020-04-03
 */
@TableName("E__TQDJ")
public class Tqdj extends Model<Tqdj> {

    private static final long serialVersionUID = 1L;

    /**
     * 所属用户
     */
	@TableField("OWNINGUSER")
	private String owninguser;
    /**
     * 所属部门
     */
	@TableField("OWNINGBUSINESSUNIT")
	private String owningbusinessunit;
    /**
     * 最后修改时间
     */
	@TableField("MODIFIEDON")
	private String modifiedon;
    /**
     * 最后修改者
     */
	@TableField("MODIFIEDBY")
	private String modifiedby;
    /**
     * 是否删除
     */
	@TableField("ISDELETED")
	private String isdeleted;
    /**
     * 创建时间
     */
	@TableField("CREATEDON")
	private String createdon;
    /**
     * 创建者
     */
	@TableField("CREATEDBY")
	private String createdby;
    /**
     * 最近处理时间
     */
	@TableField("C__ZUIJINCHULISHIJIAN")
	private String cZuijinchulishijian;
    /**
     * 装修阶段
     */
	@TableField("C__ZHUANGXIUJIEDUAN")
	private String cZhuangxiujieduan;
    /**
     * 状态
     */
	@TableField("C__ZHUANGTAI")
	private String cZhuangtai;
    /**
     * 预约门店
     */
	@TableField("C__YUYUEMENDIAN")
	private String cYuyuemendian;
    /**
     * 预算范围
     */
	@TableField("C__YUSUANFANWEI")
	private String cYusuanfanwei;
    /**
     * 小区
     */
	@TableField("C__XIAOQU")
	private String cXiaoqu;
    /**
     * 网络预约
     */
	@TableField("C__XGWLYY")
	private String cXgwlyy;
    /**
     * 对应活动
     */
	@TableField("C__XGHD")
	private String cXghd;
    /**
     * 特权订金ID
     */
    @TableId("C__TQDJID")
	private String cTqdjid;
    /**
     * 宝贝标题
     */
	@TableField("C__TITLE")
	private String cTitle;
    /**
     * 买家淘宝ID
     */
	@TableField("C__TIANMAOID")
	private String cTianmaoid;
    /**
     * O2O店铺名称
     */
	@TableField("C__STORENAME")
	private String cStorename;
    /**
     * 是否为经销商订单
     */
	@TableField("SHIFOUWEIJINGXIAOSHANGDINGDAN")
	private String shifouweijingxiaoshangdingdan;
    /**
     * 订单备注
     */
	@TableField("C__REMARKS")
	private String cRemarks;
    /**
     * 联系电话
     */
	@TableField("C__PHONE")
	private String cPhone;
    /**
     * 订单付款时间
     */
	@TableField("C__ORDERDATE")
	private String cOrderdate;
    /**
     * 买家留言
     */
	@TableField("C__MESSAGE")
	private String cMessage;
    /**
     * 买家会员名
     */
	@TableField("C__MAIJIAHUIYUANMING")
	private String cMaijiahuiyuanming;
    /**
     * 最近分配时间
     */
	@TableField("C__LASTASSIGNON")
	private String cLastassignon;
    /**
     * 来源
     */
	@TableField("C__LAIYUAN")
	private String cLaiyuan;
    /**
     * 客户所在城市
     */
	@TableField("C__KEHUSUOZAICHENGSHI")
	private String cKehusuozaichengshi;
    /**
     * 客户等级
     */
	@TableField("C__KEHUDENGJI")
	private String cKehudengji;
    /**
     * 回访客服备注
     */
	@TableField("C__HUIFANGKEFUBEIZHU")
	private String cHuifangkefubeizhu;
    /**
     * 跟单客服备注
     */
	@TableField("C__GENDANKEFUBEIZHU")
	private String cGendankefubeizhu;
    /**
     * 附件
     */
	@TableField("C__FUJIAN")
	private String cFujian;
    /**
     * 分配状态
     */
	@TableField("C__FENPEIZHUANGTAI")
	private String cFenpeizhuangtai;
    /**
     * 定制意愿
     */
	@TableField("C__DINGZHIYIYUAN")
	private String cDingzhiyiyuan;
    /**
     * 定制产品
     */
	@TableField("C__DINGZHICHANPIN")
	private String cDingzhichanpin;
    /**
     * 订单判断状态
     */
	@TableField("C__DINGDANPANDUANZHUANGTAI")
	private String cDingdanpanduanzhuangtai;
    /**
     * 天猫订单编号
     */
	@TableField("C__DINGDANHAO")
	private String cDingdanhao;
    /**
     * 收货人姓名
     */
	@TableField("C__CONSIGNEE")
	private String cConsignee;
    /**
     * 买家实际支付金额
     */
	@TableField("C__AMOUNT")
	private String cAmount;
    /**
     * 收货地址
     */
	@TableField("C__ADDRESS")
	private String cAddress;
    /**
     * 客户姓名
     */
	@TableField("C__ACCOUNTID")
	private String cAccountid;


	public String getOwninguser() {
		return owninguser;
	}

	public Tqdj setOwninguser(String owninguser) {
		this.owninguser = owninguser;
		return this;
	}

	public String getOwningbusinessunit() {
		return owningbusinessunit;
	}

	public Tqdj setOwningbusinessunit(String owningbusinessunit) {
		this.owningbusinessunit = owningbusinessunit;
		return this;
	}

	public String getModifiedon() {
		return modifiedon;
	}

	public Tqdj setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
		return this;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public Tqdj setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
		return this;
	}

	public String getIsdeleted() {
		return isdeleted;
	}

	public Tqdj setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
		return this;
	}

	public String getCreatedon() {
		return createdon;
	}

	public Tqdj setCreatedon(String createdon) {
		this.createdon = createdon;
		return this;
	}

	public String getCreatedby() {
		return createdby;
	}

	public Tqdj setCreatedby(String createdby) {
		this.createdby = createdby;
		return this;
	}

	public String getCZuijinchulishijian() {
		return cZuijinchulishijian;
	}

	public Tqdj setCZuijinchulishijian(String cZuijinchulishijian) {
		this.cZuijinchulishijian = cZuijinchulishijian;
		return this;
	}

	public String getCZhuangxiujieduan() {
		return cZhuangxiujieduan;
	}

	public Tqdj setCZhuangxiujieduan(String cZhuangxiujieduan) {
		this.cZhuangxiujieduan = cZhuangxiujieduan;
		return this;
	}

	public String getCZhuangtai() {
		return cZhuangtai;
	}

	public Tqdj setCZhuangtai(String cZhuangtai) {
		this.cZhuangtai = cZhuangtai;
		return this;
	}

	public String getCYuyuemendian() {
		return cYuyuemendian;
	}

	public Tqdj setCYuyuemendian(String cYuyuemendian) {
		this.cYuyuemendian = cYuyuemendian;
		return this;
	}

	public String getCYusuanfanwei() {
		return cYusuanfanwei;
	}

	public Tqdj setCYusuanfanwei(String cYusuanfanwei) {
		this.cYusuanfanwei = cYusuanfanwei;
		return this;
	}

	public String getCXiaoqu() {
		return cXiaoqu;
	}

	public Tqdj setCXiaoqu(String cXiaoqu) {
		this.cXiaoqu = cXiaoqu;
		return this;
	}

	public String getCXgwlyy() {
		return cXgwlyy;
	}

	public Tqdj setCXgwlyy(String cXgwlyy) {
		this.cXgwlyy = cXgwlyy;
		return this;
	}

	public String getCXghd() {
		return cXghd;
	}

	public Tqdj setCXghd(String cXghd) {
		this.cXghd = cXghd;
		return this;
	}

	public String getCTqdjid() {
		return cTqdjid;
	}

	public Tqdj setCTqdjid(String cTqdjid) {
		this.cTqdjid = cTqdjid;
		return this;
	}

	public String getCTitle() {
		return cTitle;
	}

	public Tqdj setCTitle(String cTitle) {
		this.cTitle = cTitle;
		return this;
	}

	public String getCTianmaoid() {
		return cTianmaoid;
	}

	public Tqdj setCTianmaoid(String cTianmaoid) {
		this.cTianmaoid = cTianmaoid;
		return this;
	}

	public String getCStorename() {
		return cStorename;
	}

	public Tqdj setCStorename(String cStorename) {
		this.cStorename = cStorename;
		return this;
	}

	public String getShifouweijingxiaoshangdingdan() {
		return shifouweijingxiaoshangdingdan;
	}

	public Tqdj setShifouweijingxiaoshangdingdan(String shifouweijingxiaoshangdingdan) {
		this.shifouweijingxiaoshangdingdan = shifouweijingxiaoshangdingdan;
		return this;
	}

	public String getCRemarks() {
		return cRemarks;
	}

	public Tqdj setCRemarks(String cRemarks) {
		this.cRemarks = cRemarks;
		return this;
	}

	public String getCPhone() {
		return cPhone;
	}

	public Tqdj setCPhone(String cPhone) {
		this.cPhone = cPhone;
		return this;
	}

	public String getCOrderdate() {
		return cOrderdate;
	}

	public Tqdj setCOrderdate(String cOrderdate) {
		this.cOrderdate = cOrderdate;
		return this;
	}

	public String getCMessage() {
		return cMessage;
	}

	public Tqdj setCMessage(String cMessage) {
		this.cMessage = cMessage;
		return this;
	}

	public String getCMaijiahuiyuanming() {
		return cMaijiahuiyuanming;
	}

	public Tqdj setCMaijiahuiyuanming(String cMaijiahuiyuanming) {
		this.cMaijiahuiyuanming = cMaijiahuiyuanming;
		return this;
	}

	public String getCLastassignon() {
		return cLastassignon;
	}

	public Tqdj setCLastassignon(String cLastassignon) {
		this.cLastassignon = cLastassignon;
		return this;
	}

	public String getCLaiyuan() {
		return cLaiyuan;
	}

	public Tqdj setCLaiyuan(String cLaiyuan) {
		this.cLaiyuan = cLaiyuan;
		return this;
	}

	public String getCKehusuozaichengshi() {
		return cKehusuozaichengshi;
	}

	public Tqdj setCKehusuozaichengshi(String cKehusuozaichengshi) {
		this.cKehusuozaichengshi = cKehusuozaichengshi;
		return this;
	}

	public String getCKehudengji() {
		return cKehudengji;
	}

	public Tqdj setCKehudengji(String cKehudengji) {
		this.cKehudengji = cKehudengji;
		return this;
	}

	public String getCHuifangkefubeizhu() {
		return cHuifangkefubeizhu;
	}

	public Tqdj setCHuifangkefubeizhu(String cHuifangkefubeizhu) {
		this.cHuifangkefubeizhu = cHuifangkefubeizhu;
		return this;
	}

	public String getCGendankefubeizhu() {
		return cGendankefubeizhu;
	}

	public Tqdj setCGendankefubeizhu(String cGendankefubeizhu) {
		this.cGendankefubeizhu = cGendankefubeizhu;
		return this;
	}

	public String getCFujian() {
		return cFujian;
	}

	public Tqdj setCFujian(String cFujian) {
		this.cFujian = cFujian;
		return this;
	}

	public String getCFenpeizhuangtai() {
		return cFenpeizhuangtai;
	}

	public Tqdj setCFenpeizhuangtai(String cFenpeizhuangtai) {
		this.cFenpeizhuangtai = cFenpeizhuangtai;
		return this;
	}

	public String getCDingzhiyiyuan() {
		return cDingzhiyiyuan;
	}

	public Tqdj setCDingzhiyiyuan(String cDingzhiyiyuan) {
		this.cDingzhiyiyuan = cDingzhiyiyuan;
		return this;
	}

	public String getCDingzhichanpin() {
		return cDingzhichanpin;
	}

	public Tqdj setCDingzhichanpin(String cDingzhichanpin) {
		this.cDingzhichanpin = cDingzhichanpin;
		return this;
	}

	public String getCDingdanpanduanzhuangtai() {
		return cDingdanpanduanzhuangtai;
	}

	public Tqdj setCDingdanpanduanzhuangtai(String cDingdanpanduanzhuangtai) {
		this.cDingdanpanduanzhuangtai = cDingdanpanduanzhuangtai;
		return this;
	}

	public String getCDingdanhao() {
		return cDingdanhao;
	}

	public Tqdj setCDingdanhao(String cDingdanhao) {
		this.cDingdanhao = cDingdanhao;
		return this;
	}

	public String getCConsignee() {
		return cConsignee;
	}

	public Tqdj setCConsignee(String cConsignee) {
		this.cConsignee = cConsignee;
		return this;
	}

	public String getCAmount() {
		return cAmount;
	}

	public Tqdj setCAmount(String cAmount) {
		this.cAmount = cAmount;
		return this;
	}

	public String getCAddress() {
		return cAddress;
	}

	public Tqdj setCAddress(String cAddress) {
		this.cAddress = cAddress;
		return this;
	}

	public String getCAccountid() {
		return cAccountid;
	}

	public Tqdj setCAccountid(String cAccountid) {
		this.cAccountid = cAccountid;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.cTqdjid;
	}

}

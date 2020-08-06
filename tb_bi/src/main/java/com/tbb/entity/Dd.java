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
@TableName("E__DD")
public class Dd extends Model<Dd> {

    private static final long serialVersionUID = 1L;

    /**
     * 当前审批状态
     */
	@TableField("STATE")
	private String state;
    /**
     * 最近轮选人员
     */
	@TableField("RECENTTURNUSERS")
	private String recentturnusers;
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
     * 下一审批人
     */
	@TableField("NEXTAUDITERS")
	private String nextauditers;
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
     * 最近审批时间
     */
	@TableField("LATESTAUDITTIME")
	private String latestaudittime;
    /**
     * 最近审批人
     */
	@TableField("LATESTAUDITER")
	private String latestauditer;
    /**
     * 是否删除
     */
	@TableField("ISDELETED")
	private String isdeleted;
    /**
     * 当前审核步骤
     */
	@TableField("CURRENTFLOWSTEPID")
	private String currentflowstepid;
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
     * 总面积㎡
     */
	@TableField("C__ZONGMIANJI")
	private String cZongmianji;
    /**
     * 总金额
     */
	@TableField("C__ZONGJINE")
	private String cZongjine;
    /**
     * 运输费
     */
	@TableField("C__YUNSHUFEI")
	private String cYunshufei;
    /**
     * 要求交货时间
     */
	@TableField("C__YAOQIUJIAOHUOSHIJIAN")
	private String cYaoqiujiaohuoshijian;
    /**
     * 相关易装反馈单
     */
	@TableField("C__XIANGGUANYIZHUANGFANKUIDAN")
	private String cXiangguanyizhuangfankuidan;
    /**
     * 物流信息
     */
	@TableField("C__WULIUXINXI")
	private String cWuliuxinxi;
    /**
     * 五金配件金额
     */
	@TableField("C__WUJINPEIJIANJINE")
	private String cWujinpeijianjine;
    /**
     * 五金附件
     */
	@TableField("C__WUJINFUJIAN")
	private String cWujinfujian;
    /**
     * 外定门金额
     */
	@TableField("C__WAIDINGMENJINE")
	private String cWaidingmenjine;
    /**
     * 外定门附件
     */
	@TableField("C__WAIDINGMENFUJIAN")
	private String cWaidingmenfujian;
    /**
     * 生产完工日期
     */
	@TableField("C__SHENGCHANWANGONGRIQI")
	private String cShengchanwangongriqi;
    /**
     * 其他费
     */
	@TableField("C__QITAFEI")
	private String cQitafei;
    /**
     * 排单情况
     */
	@TableField("C__PAIDANQINGKUANG")
	private String cPaidanqingkuang;
    /**
     * 客户姓名
     */
	@TableField("C__KEHUXINGMING")
	private String cKehuxingming;
    /**
     * 客户地址
     */
	@TableField("C__KEHUDIZHI")
	private String cKehudizhi;
    /**
     * 客户电话
     */
	@TableField("C__KEHUDIANHUA")
	private String cKehudianhua;
    /**
     * 关联客户
     */
	@TableField("C__KEHU")
	private String cKehu;
    /**
     * 经销商
     */
	@TableField("C__JINGXIAOSHANG")
	private String cJingxiaoshang;
    /**
     * 加工中心
     */
	@TableField("C__JIAGONGZHONGXIN")
	private String cJiagongzhongxin;
    /**
     * 柜体金额
     */
	@TableField("C__GUITIJINE")
	private String cGuitijine;
    /**
     * 柜体金额2
     */
	@TableField("C__GTJE")
	private String cGtje;
    /**
     * 订单状态
     */
	@TableField("C__DINGDANZHUANGTAI")
	private String cDingdanzhuangtai;
    /**
     * 订单类型
     */
	@TableField("C__DINGDANLEIXING")
	private String cDingdanleixing;
    /**
     * 订单号1
     */
	@TableField("C__DINGDANHAO")
	private String cDingdanhao;
    /**
     * 订单附件
     */
	@TableField("C__DINGDANFUJIAN")
	private String cDingdanfujian;
    /**
     * 订单id
     */
    @TableId("C__DDID")
	private String cDdid;
    /**
     * 订单号
     */
	@TableField("C__DDH")
	private String cDdh;
    /**
     * 拆单完成日期
     */
	@TableField("C__CHAIDANWANCHENGRIQI")
	private String cChaidanwanchengriqi;
    /**
     * 拆单申领人
     */
	@TableField("C__CHAIDANSHENLINGREN")
	private String cChaidanshenlingren;
    /**
     * 拆单附件
     */
	@TableField("C__CHAIDANFUJIAN")
	private String cChaidanfujian;
    /**
     * 备注
     */
	@TableField("C__BEIZHU")
	private String cBeizhu;
    /**
     * 备货情况
     */
	@TableField("C__BEIHUOQINGKUANG")
	private String cBeihuoqingkuang;
    /**
     * 设计师
     */
	@TableField("C__BAOZHUANGYANSE")
	private String cBaozhuangyanse;
    /**
     * 柜体附件
     */
	@TableField("C__BAOJIAFUJIAN")
	private String cBaojiafujian;
    /**
     * 安装人员
     */
	@TableField("C__ANZHUANGRENYUAN")
	private String cAnzhuangrenyuan;
    /**
     * 安装费
     */
	@TableField("C__ANZHUANGFEI")
	private String cAnzhuangfei;
    /**
     * 安装单数量
     */
	@TableField("C__ANZHUANGDANSHULIANG")
	private String cAnzhuangdanshuliang;


	public String getState() {
		return state;
	}

	public Dd setState(String state) {
		this.state = state;
		return this;
	}

	public String getRecentturnusers() {
		return recentturnusers;
	}

	public Dd setRecentturnusers(String recentturnusers) {
		this.recentturnusers = recentturnusers;
		return this;
	}

	public String getOwninguser() {
		return owninguser;
	}

	public Dd setOwninguser(String owninguser) {
		this.owninguser = owninguser;
		return this;
	}

	public String getOwningbusinessunit() {
		return owningbusinessunit;
	}

	public Dd setOwningbusinessunit(String owningbusinessunit) {
		this.owningbusinessunit = owningbusinessunit;
		return this;
	}

	public String getNextauditers() {
		return nextauditers;
	}

	public Dd setNextauditers(String nextauditers) {
		this.nextauditers = nextauditers;
		return this;
	}

	public String getModifiedon() {
		return modifiedon;
	}

	public Dd setModifiedon(String modifiedon) {
		this.modifiedon = modifiedon;
		return this;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public Dd setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
		return this;
	}

	public String getLatestaudittime() {
		return latestaudittime;
	}

	public Dd setLatestaudittime(String latestaudittime) {
		this.latestaudittime = latestaudittime;
		return this;
	}

	public String getLatestauditer() {
		return latestauditer;
	}

	public Dd setLatestauditer(String latestauditer) {
		this.latestauditer = latestauditer;
		return this;
	}

	public String getIsdeleted() {
		return isdeleted;
	}

	public Dd setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
		return this;
	}

	public String getCurrentflowstepid() {
		return currentflowstepid;
	}

	public Dd setCurrentflowstepid(String currentflowstepid) {
		this.currentflowstepid = currentflowstepid;
		return this;
	}

	public String getCreatedon() {
		return createdon;
	}

	public Dd setCreatedon(String createdon) {
		this.createdon = createdon;
		return this;
	}

	public String getCreatedby() {
		return createdby;
	}

	public Dd setCreatedby(String createdby) {
		this.createdby = createdby;
		return this;
	}

	public String getCZongmianji() {
		return cZongmianji;
	}

	public Dd setCZongmianji(String cZongmianji) {
		this.cZongmianji = cZongmianji;
		return this;
	}

	public String getCZongjine() {
		return cZongjine;
	}

	public Dd setCZongjine(String cZongjine) {
		this.cZongjine = cZongjine;
		return this;
	}

	public String getCYunshufei() {
		return cYunshufei;
	}

	public Dd setCYunshufei(String cYunshufei) {
		this.cYunshufei = cYunshufei;
		return this;
	}

	public String getCYaoqiujiaohuoshijian() {
		return cYaoqiujiaohuoshijian;
	}

	public Dd setCYaoqiujiaohuoshijian(String cYaoqiujiaohuoshijian) {
		this.cYaoqiujiaohuoshijian = cYaoqiujiaohuoshijian;
		return this;
	}

	public String getCXiangguanyizhuangfankuidan() {
		return cXiangguanyizhuangfankuidan;
	}

	public Dd setCXiangguanyizhuangfankuidan(String cXiangguanyizhuangfankuidan) {
		this.cXiangguanyizhuangfankuidan = cXiangguanyizhuangfankuidan;
		return this;
	}

	public String getCWuliuxinxi() {
		return cWuliuxinxi;
	}

	public Dd setCWuliuxinxi(String cWuliuxinxi) {
		this.cWuliuxinxi = cWuliuxinxi;
		return this;
	}

	public String getCWujinpeijianjine() {
		return cWujinpeijianjine;
	}

	public Dd setCWujinpeijianjine(String cWujinpeijianjine) {
		this.cWujinpeijianjine = cWujinpeijianjine;
		return this;
	}

	public String getCWujinfujian() {
		return cWujinfujian;
	}

	public Dd setCWujinfujian(String cWujinfujian) {
		this.cWujinfujian = cWujinfujian;
		return this;
	}

	public String getCWaidingmenjine() {
		return cWaidingmenjine;
	}

	public Dd setCWaidingmenjine(String cWaidingmenjine) {
		this.cWaidingmenjine = cWaidingmenjine;
		return this;
	}

	public String getCWaidingmenfujian() {
		return cWaidingmenfujian;
	}

	public Dd setCWaidingmenfujian(String cWaidingmenfujian) {
		this.cWaidingmenfujian = cWaidingmenfujian;
		return this;
	}

	public String getCShengchanwangongriqi() {
		return cShengchanwangongriqi;
	}

	public Dd setCShengchanwangongriqi(String cShengchanwangongriqi) {
		this.cShengchanwangongriqi = cShengchanwangongriqi;
		return this;
	}

	public String getCQitafei() {
		return cQitafei;
	}

	public Dd setCQitafei(String cQitafei) {
		this.cQitafei = cQitafei;
		return this;
	}

	public String getCPaidanqingkuang() {
		return cPaidanqingkuang;
	}

	public Dd setCPaidanqingkuang(String cPaidanqingkuang) {
		this.cPaidanqingkuang = cPaidanqingkuang;
		return this;
	}

	public String getCKehuxingming() {
		return cKehuxingming;
	}

	public Dd setCKehuxingming(String cKehuxingming) {
		this.cKehuxingming = cKehuxingming;
		return this;
	}

	public String getCKehudizhi() {
		return cKehudizhi;
	}

	public Dd setCKehudizhi(String cKehudizhi) {
		this.cKehudizhi = cKehudizhi;
		return this;
	}

	public String getCKehudianhua() {
		return cKehudianhua;
	}

	public Dd setCKehudianhua(String cKehudianhua) {
		this.cKehudianhua = cKehudianhua;
		return this;
	}

	public String getCKehu() {
		return cKehu;
	}

	public Dd setCKehu(String cKehu) {
		this.cKehu = cKehu;
		return this;
	}

	public String getCJingxiaoshang() {
		return cJingxiaoshang;
	}

	public Dd setCJingxiaoshang(String cJingxiaoshang) {
		this.cJingxiaoshang = cJingxiaoshang;
		return this;
	}

	public String getCJiagongzhongxin() {
		return cJiagongzhongxin;
	}

	public Dd setCJiagongzhongxin(String cJiagongzhongxin) {
		this.cJiagongzhongxin = cJiagongzhongxin;
		return this;
	}

	public String getCGuitijine() {
		return cGuitijine;
	}

	public Dd setCGuitijine(String cGuitijine) {
		this.cGuitijine = cGuitijine;
		return this;
	}

	public String getCGtje() {
		return cGtje;
	}

	public Dd setCGtje(String cGtje) {
		this.cGtje = cGtje;
		return this;
	}

	public String getCDingdanzhuangtai() {
		return cDingdanzhuangtai;
	}

	public Dd setCDingdanzhuangtai(String cDingdanzhuangtai) {
		this.cDingdanzhuangtai = cDingdanzhuangtai;
		return this;
	}

	public String getCDingdanleixing() {
		return cDingdanleixing;
	}

	public Dd setCDingdanleixing(String cDingdanleixing) {
		this.cDingdanleixing = cDingdanleixing;
		return this;
	}

	public String getCDingdanhao() {
		return cDingdanhao;
	}

	public Dd setCDingdanhao(String cDingdanhao) {
		this.cDingdanhao = cDingdanhao;
		return this;
	}

	public String getCDingdanfujian() {
		return cDingdanfujian;
	}

	public Dd setCDingdanfujian(String cDingdanfujian) {
		this.cDingdanfujian = cDingdanfujian;
		return this;
	}

	public String getCDdid() {
		return cDdid;
	}

	public Dd setCDdid(String cDdid) {
		this.cDdid = cDdid;
		return this;
	}

	public String getCDdh() {
		return cDdh;
	}

	public Dd setCDdh(String cDdh) {
		this.cDdh = cDdh;
		return this;
	}

	public String getCChaidanwanchengriqi() {
		return cChaidanwanchengriqi;
	}

	public Dd setCChaidanwanchengriqi(String cChaidanwanchengriqi) {
		this.cChaidanwanchengriqi = cChaidanwanchengriqi;
		return this;
	}

	public String getCChaidanshenlingren() {
		return cChaidanshenlingren;
	}

	public Dd setCChaidanshenlingren(String cChaidanshenlingren) {
		this.cChaidanshenlingren = cChaidanshenlingren;
		return this;
	}

	public String getCChaidanfujian() {
		return cChaidanfujian;
	}

	public Dd setCChaidanfujian(String cChaidanfujian) {
		this.cChaidanfujian = cChaidanfujian;
		return this;
	}

	public String getCBeizhu() {
		return cBeizhu;
	}

	public Dd setCBeizhu(String cBeizhu) {
		this.cBeizhu = cBeizhu;
		return this;
	}

	public String getCBeihuoqingkuang() {
		return cBeihuoqingkuang;
	}

	public Dd setCBeihuoqingkuang(String cBeihuoqingkuang) {
		this.cBeihuoqingkuang = cBeihuoqingkuang;
		return this;
	}

	public String getCBaozhuangyanse() {
		return cBaozhuangyanse;
	}

	public Dd setCBaozhuangyanse(String cBaozhuangyanse) {
		this.cBaozhuangyanse = cBaozhuangyanse;
		return this;
	}

	public String getCBaojiafujian() {
		return cBaojiafujian;
	}

	public Dd setCBaojiafujian(String cBaojiafujian) {
		this.cBaojiafujian = cBaojiafujian;
		return this;
	}

	public String getCAnzhuangrenyuan() {
		return cAnzhuangrenyuan;
	}

	public Dd setCAnzhuangrenyuan(String cAnzhuangrenyuan) {
		this.cAnzhuangrenyuan = cAnzhuangrenyuan;
		return this;
	}

	public String getCAnzhuangfei() {
		return cAnzhuangfei;
	}

	public Dd setCAnzhuangfei(String cAnzhuangfei) {
		this.cAnzhuangfei = cAnzhuangfei;
		return this;
	}

	public String getCAnzhuangdanshuliang() {
		return cAnzhuangdanshuliang;
	}

	public Dd setCAnzhuangdanshuliang(String cAnzhuangdanshuliang) {
		this.cAnzhuangdanshuliang = cAnzhuangdanshuliang;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.cDdid;
	}

}

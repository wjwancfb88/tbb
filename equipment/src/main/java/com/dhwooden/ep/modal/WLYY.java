package com.dhwooden.ep.modal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.sql.Timestamp;
import java.util.Date;

@TableName("e__wlyy")
public class WLYY {
    @TableField("c__biaoti")
    private String biaoti;//标题
    @TableField("c__chengshi")
    private String chengshi;//城市
    @TableField("c__dianshangbupanduanyixiang")
    private String dianshangbupanduanyixiang;//电商部判断意向
    @TableField("c__dingzhichanpin")
    private String dingzhichanpin;//定制产品
    @TableField("c__dingzhiyiyuan")
    private String dingzhiyiyuan;//定制意愿
    @TableField("c__fenpeizhuangtai")
    private String fenpeizhuangtai;//分配状态
    @TableField("c__gendankefubeizhu")
    private String gendankefubeizhu;//跟单客服备注
    @TableField("c__genjincishu")
    private String genjincishu;//跟进次数
    @TableField("c__huifangkefubeizhu")
    private String huifangkefubeizhu;//回访客服备注
    @TableField("c__huodong")
    private String huodong;//活动名称
    @TableField("c__kehu")
    private String kehu;//客户
    @TableField("c__kehudengji")
    private String kehudengji;//客户等级
    @TableField("c__kehudizhi")
    private String kehudizhi;//客户地址
    @TableField("c__kehushouji")
    private String kehushouji;//客户手机
    @TableField("c__kehusuozaichengshi")
    private String kehusuozaichengshi;//客户所在城市
    @TableField("c__kehuxingming")
    private String kehuxingming;//客户姓名
    @TableField("c__kehuxuqiu")
    private String kehuxuqiu;//客户需求
    @TableField("c__laiyuan")
    private String laiyuan;//来源
    @TableField("c__lastAssignOn")
    private String lastAssignOn;//最近分配时间
    @TableField("c__leixing")
    private String leixing;//订单判断状态
    @TableField("c__sheng")
    private String sheng;//省
    @TableField("c__shi")
    private String shi;//市
    @TableField("c__tuiguangpingtai")
    private String tuiguangpingtai;//推广平台
    @TableField("c__type")
    private String type;//来源分类
    @TableField("c__url")
    private String url;//活动链接
    @TableField("c__wlyyId")
    private String wlyyId;//网络预约id
    @TableField("c__xianqu")
    private String xianqu;//县区
    @TableField("c__xiaoqu")
    private String xiaoqu;//小区
    @TableField("c__yixiangfenlei")
    private String yixiangfenlei;//意向分类
    @TableField("c__yixiangjingxiaoshang")
    private String yixiangjingxiaoshang;//意向经销商
    @TableField("c__yusuanfanwei")
    private String yusuanfanwei;//预算范围
    @TableField("c__zhuangtai")
    private String zhuangtai;//状态
    @TableField("c__zhuangxiujieduan")
    private String zhuangxiujieduan;//装修阶段
    @TableField("c__zuijinchulishijian")
    private String zuijinchulishijian;//最近处理时间
    @TableField("c__zuijingenjinneirong")
    private String zuijingenjinneirong;//最近跟进内容
    @TableField("created_by")
    private String createdBy;//创建者
    @TableField("created_on")
    private String createdOn;//创建时间
    @TableField("is_deleted")
    private String isDeleted;//是否删除
    @TableField("modified_by")
    private String modifiedBy;//最后修改者
    @TableField("modified_on")
    private String modifiedOn;//最后修改时间
    @TableField("owning_business_unit")
    private String owningBusinessUnit;//所属部门
    @TableField("owning_user")
    private String owningUser;//所属用户

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    public String getDianshangbupanduanyixiang() {
        return dianshangbupanduanyixiang;
    }

    public void setDianshangbupanduanyixiang(String dianshangbupanduanyixiang) {
        this.dianshangbupanduanyixiang = dianshangbupanduanyixiang;
    }

    public String getDingzhichanpin() {
        return dingzhichanpin;
    }

    public void setDingzhichanpin(String dingzhichanpin) {
        this.dingzhichanpin = dingzhichanpin;
    }

    public String getDingzhiyiyuan() {
        return dingzhiyiyuan;
    }

    public void setDingzhiyiyuan(String dingzhiyiyuan) {
        this.dingzhiyiyuan = dingzhiyiyuan;
    }

    public String getFenpeizhuangtai() {
        return fenpeizhuangtai;
    }

    public void setFenpeizhuangtai(String fenpeizhuangtai) {
        this.fenpeizhuangtai = fenpeizhuangtai;
    }

    public String getGendankefubeizhu() {
        return gendankefubeizhu;
    }

    public void setGendankefubeizhu(String gendankefubeizhu) {
        this.gendankefubeizhu = gendankefubeizhu;
    }

    public String getGenjincishu() {
        return genjincishu;
    }

    public void setGenjincishu(String genjincishu) {
        this.genjincishu = genjincishu;
    }

    public String getHuifangkefubeizhu() {
        return huifangkefubeizhu;
    }

    public void setHuifangkefubeizhu(String huifangkefubeizhu) {
        this.huifangkefubeizhu = huifangkefubeizhu;
    }

    public String getHuodong() {
        return huodong;
    }

    public void setHuodong(String huodong) {
        this.huodong = huodong;
    }

    public String getKehu() {
        return kehu;
    }

    public void setKehu(String kehu) {
        this.kehu = kehu;
    }

    public String getKehudengji() {
        return kehudengji;
    }

    public void setKehudengji(String kehudengji) {
        this.kehudengji = kehudengji;
    }

    public String getKehudizhi() {
        return kehudizhi;
    }

    public void setKehudizhi(String kehudizhi) {
        this.kehudizhi = kehudizhi;
    }

    public String getKehushouji() {
        return kehushouji;
    }

    public void setKehushouji(String kehushouji) {
        this.kehushouji = kehushouji;
    }

    public String getKehusuozaichengshi() {
        return kehusuozaichengshi;
    }

    public void setKehusuozaichengshi(String kehusuozaichengshi) {
        this.kehusuozaichengshi = kehusuozaichengshi;
    }

    public String getKehuxingming() {
        return kehuxingming;
    }

    public void setKehuxingming(String kehuxingming) {
        this.kehuxingming = kehuxingming;
    }

    public String getKehuxuqiu() {
        return kehuxuqiu;
    }

    public void setKehuxuqiu(String kehuxuqiu) {
        this.kehuxuqiu = kehuxuqiu;
    }

    public String getLaiyuan() {
        return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
        this.laiyuan = laiyuan;
    }

    public String getLastAssignOn() {
        return lastAssignOn;
    }

    public void setLastAssignOn(String lastAssignOn) {
        this.lastAssignOn = lastAssignOn;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getTuiguangpingtai() {
        return tuiguangpingtai;
    }

    public void setTuiguangpingtai(String tuiguangpingtai) {
        this.tuiguangpingtai = tuiguangpingtai;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWlyyId() {
        return wlyyId;
    }

    public void setWlyyId(String wlyyId) {
        this.wlyyId = wlyyId;
    }

    public String getXianqu() {
        return xianqu;
    }

    public void setXianqu(String xianqu) {
        this.xianqu = xianqu;
    }

    public String getXiaoqu() {
        return xiaoqu;
    }

    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

    public String getYixiangfenlei() {
        return yixiangfenlei;
    }

    public void setYixiangfenlei(String yixiangfenlei) {
        this.yixiangfenlei = yixiangfenlei;
    }

    public String getYixiangjingxiaoshang() {
        return yixiangjingxiaoshang;
    }

    public void setYixiangjingxiaoshang(String yixiangjingxiaoshang) {
        this.yixiangjingxiaoshang = yixiangjingxiaoshang;
    }

    public String getYusuanfanwei() {
        return yusuanfanwei;
    }

    public void setYusuanfanwei(String yusuanfanwei) {
        this.yusuanfanwei = yusuanfanwei;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getZhuangxiujieduan() {
        return zhuangxiujieduan;
    }

    public void setZhuangxiujieduan(String zhuangxiujieduan) {
        this.zhuangxiujieduan = zhuangxiujieduan;
    }

    public String getZuijinchulishijian() {
        return zuijinchulishijian;
    }

    public void setZuijinchulishijian(String zuijinchulishijian) {
        this.zuijinchulishijian = zuijinchulishijian;
    }

    public String getZuijingenjinneirong() {
        return zuijingenjinneirong;
    }

    public void setZuijingenjinneirong(String zuijingenjinneirong) {
        this.zuijingenjinneirong = zuijingenjinneirong;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getOwningBusinessUnit() {
        return owningBusinessUnit;
    }

    public void setOwningBusinessUnit(String owningBusinessUnit) {
        this.owningBusinessUnit = owningBusinessUnit;
    }

    public String getOwningUser() {
        return owningUser;
    }

    public void setOwningUser(String owningUser) {
        this.owningUser = owningUser;
    }
}

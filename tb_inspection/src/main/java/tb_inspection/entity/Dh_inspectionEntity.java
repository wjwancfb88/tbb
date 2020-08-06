package tb_inspection.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import  java.sql.Timestamp;
import java.util.Date;


public class Dh_inspectionEntity extends CommonEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
	 *  设备巡检id
	 */
    private String inspectionId;
    /**
	 *  销售公司会议室
	 */
    private String sale;
    /**
	 *  销售公司幕布
	 */
    private String sc;
    /**
	 *  销售公司幕布结果,1:正常 0:异常
	 */
    private String scresult;
    /**
	 *  销售公司幕布备注
	 */
    private String scremarks;
    /**
	 *  销售公司投影仪
	 */
    private String sp;
    /**
	 *  销售公司投影仪结果
	 */
    private String spresult;
    /**
	 *  销售公司投影仪描述
	 */
    private String spremarks;
    /**
	 *  二楼会议室
	 */
    private String sf;
    /**
	 *  二楼一体机
	 */
    private String sfa;
    /**
	 *  二楼一体机结果
	 */
    private String sfaresult;
    /**
	 *  二楼一体机备注
	 */
    private String sfaremarks;
    /**
	 *  三楼培训中心
	 */
    private String tftc;
    /**
	 *  三楼培训中心LED大屏幕
	 */
    private String tftcl;
    /**
	 *  三楼培训中心LED大屏幕结果
	 */
    private String tftclresult;
    /**
	 *  三楼培训中心LED大屏幕备注
	 */
    private String tftclremarks;
    /**
	 *  三楼培训中心音响
	 */
    private String tftcs;
    /**
	 *  三楼培训中心音响结果
	 */
    private String tftcsresult;
    /**
	 *  三楼培训中心音响备注
	 */
    private String tftcsremarks;
    /**
	 *  三楼培训中心话筒
	 */
    private String tftcm;
    /**
	 *  三楼培训中心话筒结果
	 */
    private String tftcmresult;
    /**
	 *  三楼培训中心话筒备注
	 */
    private String tftcmremarks;
    /**
	 *  三楼培训中心音控室电脑
	 */
    private String tftcc;
    /**
	 *  三楼培训中心音控室电脑结果
	 */
    private String tftccresult;
    /**
	 *  三楼培训中心音控室电脑备注
	 */
    private String tftccremarks;
    /**
	 *  三楼会议室
	 */
    private String tfm;
    /**
	 *  三楼会议室LED大屏幕
	 */
    private String tfml;
    /**
	 *  三楼会议室LED大屏幕结果
	 */
    private String tfmlresult;
    /**
	 *  三楼会议室LED大屏幕备注
	 */
    private String tfmlremarks;
    /**
	 *  三楼会议室音响
	 */
    private String tfms;
    /**
	 *  三楼会议室音响结果
	 */
    private String tfmsresult;
    /**
	 *  三楼会议室音响备注
	 */
    private String tfmsremarks;
    /**
	 *  三楼会议室话筒
	 */
    private String tfmm;
    /**
	 *  三楼会议室话筒结果
	 */
    private String tfmmresult;
    /**
	 *  三楼会议室话筒备注
	 */
    private String tfmmremarks;
    /**
	 *  三楼会议室云控宝
	 */
    private String tfmc;
    /**
	 *  三楼会议室云控宝结果
	 */
    private String tfmcresult;
    /**
	 *  三楼会议室云控宝备注
	 */
    private String tfmcremarks;
    /**
	 *  五楼会议室
	 */
    private String ffm;
    /**
	 *  五楼会议室幕布1
	 */
    private String ffmc;
    /**
	 *  五楼会议室幕布1结果
	 */
    private String ffmcresult;
    /**
	 *  五楼会议室幕布1备注
	 */
    private String ffmcremarks;
	/**
	 *  五楼会议室幕布2
	 */
	private String ffmc2;
	/**
	 *  五楼会议室幕布2结果
	 */
	private String ffmc2result;
	/**
	 *  五楼会议室幕布2备注
	 */
	private String ffmc2remarks;
    /**
	 *  五楼会议室投影仪
	 */
    private String ffmp;
    /**
	 *  五楼会议室投影仪结果
	 */
    private String ffmpresult;
    /**
	 *  五楼会议室投影仪备注
	 */
    private String ffmpremarks;
    /**
	 *  研究院四楼会议室
	 */
    private String ifofm;
    /**
	 *  研究院四楼会议室幕布
	 */
    private String ifofmc;
    /**
	 *  研究院四楼会议室幕布结果
	 */
    private String ifofmcresult;
    /**
	 *  研究院四楼会议室幕布备注
	 */
    private String ifofmcremarks;
    /**
	 *  研究院四楼会议室投影仪
	 */
    private String ifofmp;
    /**
	 *  研究院四楼会议室投影仪结果
	 */
    private String ifofmpresult;
    /**
	 *  研究院四楼会议室投影仪备注
	 */
    private String ifofmpremarks;
    /**
	 *  研究院五楼会议室
	 */
    private String iffm;
    /**
	 *  研究院五楼会议室幕布
	 */
    private String iffmc;
    /**
	 *  研究院五楼会议室幕布结果
	 */
    private String iffmcresult;
    /**
	 *  研究院五楼会议室幕布备注
	 */
    private String iffmcremarks;
    /**
	 *  研究院五楼会议室音响
	 */
    private String iffms;
    /**
	 *  研究院五楼会议室音响结果
	 */
    private String iffmsresult;
    /**
	 *  研究院五楼会议室音响备注
	 */
    private String iffmsremarks;
    /**
	 *  研究院五楼会议室话筒
	 */
    private String iffmm;
    /**
	 *  研究院五楼会议室话筒结果
	 */
    private String iffmmresult;
    /**
	 *  研究院五楼会议室话筒备注
	 */
    private String iffmmremarks;
    /**
	 *  创建时间
	 */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private String creattime;

    /**
	 *  get 设备巡检id
	 */
    public String getInspectionId() {
		return inspectionId;
	}

    /**
	 *  set 设备巡检id
	 */
	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}
    /**
	 *  get 销售公司会议室
	 */
    public String getSale() {
		return sale;
	}

    /**
	 *  set 销售公司会议室
	 */
	public void setSale(String sale) {
		this.sale = sale;
	}
    /**
	 *  get 销售公司幕布
	 */
    public String getSc() {
		return sc;
	}

    /**
	 *  set 销售公司幕布
	 */
	public void setSc(String sc) {
		this.sc = sc;
	}
    /**
	 *  get 销售公司幕布结果,1:正常 0:异常
	 */
    public String getScresult() {
		return scresult;
	}

    /**
	 *  set 销售公司幕布结果,1:正常 0:异常
	 */
	public void setScresult(String scresult) {
		this.scresult = scresult;
	}
    /**
	 *  get 销售公司幕布备注
	 */
    public String getScremarks() {
		return scremarks;
	}

    /**
	 *  set 销售公司幕布备注
	 */
	public void setScremarks(String scremarks) {
		this.scremarks = scremarks;
	}
    /**
	 *  get 销售公司投影仪
	 */
    public String getSp() {
		return sp;
	}

    /**
	 *  set 销售公司投影仪
	 */
	public void setSp(String sp) {
		this.sp = sp;
	}
    /**
	 *  get 销售公司投影仪结果
	 */
    public String getSpresult() {
		return spresult;
	}

    /**
	 *  set 销售公司投影仪结果
	 */
	public void setSpresult(String spresult) {
		this.spresult = spresult;
	}
    /**
	 *  get 销售公司投影仪描述
	 */
    public String getSpremarks() {
		return spremarks;
	}

    /**
	 *  set 销售公司投影仪描述
	 */
	public void setSpremarks(String spremarks) {
		this.spremarks = spremarks;
	}
    /**
	 *  get 二楼会议室
	 */
    public String getSf() {
		return sf;
	}

    /**
	 *  set 二楼会议室
	 */
	public void setSf(String sf) {
		this.sf = sf;
	}
    /**
	 *  get 二楼一体机
	 */
    public String getSfa() {
		return sfa;
	}

    /**
	 *  set 二楼一体机
	 */
	public void setSfa(String sfa) {
		this.sfa = sfa;
	}
    /**
	 *  get 二楼一体机结果
	 */
    public String getSfaresult() {
		return sfaresult;
	}

    /**
	 *  set 二楼一体机结果
	 */
	public void setSfaresult(String sfaresult) {
		this.sfaresult = sfaresult;
	}
    /**
	 *  get 二楼一体机备注
	 */
    public String getSfaremarks() {
		return sfaremarks;
	}

    /**
	 *  set 二楼一体机备注
	 */
	public void setSfaremarks(String sfaremarks) {
		this.sfaremarks = sfaremarks;
	}
    /**
	 *  get 三楼培训中心
	 */
    public String getTftc() {
		return tftc;
	}

    /**
	 *  set 三楼培训中心
	 */
	public void setTftc(String tftc) {
		this.tftc = tftc;
	}
    /**
	 *  get 三楼培训中心LED大屏幕
	 */
    public String getTftcl() {
		return tftcl;
	}

    /**
	 *  set 三楼培训中心LED大屏幕
	 */
	public void setTftcl(String tftcl) {
		this.tftcl = tftcl;
	}
    /**
	 *  get 三楼培训中心LED大屏幕结果
	 */
    public String getTftclresult() {
		return tftclresult;
	}

    /**
	 *  set 三楼培训中心LED大屏幕结果
	 */
	public void setTftclresult(String tftclresult) {
		this.tftclresult = tftclresult;
	}
    /**
	 *  get 三楼培训中心LED大屏幕备注
	 */
    public String getTftclremarks() {
		return tftclremarks;
	}

    /**
	 *  set 三楼培训中心LED大屏幕备注
	 */
	public void setTftclremarks(String tftclremarks) {
		this.tftclremarks = tftclremarks;
	}
    /**
	 *  get 三楼培训中心音响
	 */
    public String getTftcs() {
		return tftcs;
	}

    /**
	 *  set 三楼培训中心音响
	 */
	public void setTftcs(String tftcs) {
		this.tftcs = tftcs;
	}
    /**
	 *  get 三楼培训中心音响结果
	 */
    public String getTftcsresult() {
		return tftcsresult;
	}

    /**
	 *  set 三楼培训中心音响结果
	 */
	public void setTftcsresult(String tftcsresult) {
		this.tftcsresult = tftcsresult;
	}
    /**
	 *  get 三楼培训中心音响备注
	 */
    public String getTftcsremarks() {
		return tftcsremarks;
	}

    /**
	 *  set 三楼培训中心音响备注
	 */
	public void setTftcsremarks(String tftcsremarks) {
		this.tftcsremarks = tftcsremarks;
	}
    /**
	 *  get 三楼培训中心话筒
	 */
    public String getTftcm() {
		return tftcm;
	}

    /**
	 *  set 三楼培训中心话筒
	 */
	public void setTftcm(String tftcm) {
		this.tftcm = tftcm;
	}
    /**
	 *  get 三楼培训中心话筒结果
	 */
    public String getTftcmresult() {
		return tftcmresult;
	}

    /**
	 *  set 三楼培训中心话筒结果
	 */
	public void setTftcmresult(String tftcmresult) {
		this.tftcmresult = tftcmresult;
	}
    /**
	 *  get 三楼培训中心话筒备注
	 */
    public String getTftcmremarks() {
		return tftcmremarks;
	}

    /**
	 *  set 三楼培训中心话筒备注
	 */
	public void setTftcmremarks(String tftcmremarks) {
		this.tftcmremarks = tftcmremarks;
	}
    /**
	 *  get 三楼培训中心音控室电脑
	 */
    public String getTftcc() {
		return tftcc;
	}

    /**
	 *  set 三楼培训中心音控室电脑
	 */
	public void setTftcc(String tftcc) {
		this.tftcc = tftcc;
	}
    /**
	 *  get 三楼培训中心音控室电脑结果
	 */
    public String getTftccresult() {
		return tftccresult;
	}

    /**
	 *  set 三楼培训中心音控室电脑结果
	 */
	public void setTftccresult(String tftccresult) {
		this.tftccresult = tftccresult;
	}
    /**
	 *  get 三楼培训中心音控室电脑备注
	 */
    public String getTftccremarks() {
		return tftccremarks;
	}

    /**
	 *  set 三楼培训中心音控室电脑备注
	 */
	public void setTftccremarks(String tftccremarks) {
		this.tftccremarks = tftccremarks;
	}
    /**
	 *  get 三楼会议室
	 */
    public String getTfm() {
		return tfm;
	}

    /**
	 *  set 三楼会议室
	 */
	public void setTfm(String tfm) {
		this.tfm = tfm;
	}
    /**
	 *  get 三楼会议室LED大屏幕
	 */
    public String getTfml() {
		return tfml;
	}

    /**
	 *  set 三楼会议室LED大屏幕
	 */
	public void setTfml(String tfml) {
		this.tfml = tfml;
	}
    /**
	 *  get 三楼会议室LED大屏幕结果
	 */
    public String getTfmlresult() {
		return tfmlresult;
	}

    /**
	 *  set 三楼会议室LED大屏幕结果
	 */
	public void setTfmlresult(String tfmlresult) {
		this.tfmlresult = tfmlresult;
	}
    /**
	 *  get 三楼会议室LED大屏幕备注
	 */
    public String getTfmlremarks() {
		return tfmlremarks;
	}

    /**
	 *  set 三楼会议室LED大屏幕备注
	 */
	public void setTfmlremarks(String tfmlremarks) {
		this.tfmlremarks = tfmlremarks;
	}
    /**
	 *  get 三楼会议室音响
	 */
    public String getTfms() {
		return tfms;
	}

    /**
	 *  set 三楼会议室音响
	 */
	public void setTfms(String tfms) {
		this.tfms = tfms;
	}
    /**
	 *  get 三楼会议室音响结果
	 */
    public String getTfmsresult() {
		return tfmsresult;
	}

    /**
	 *  set 三楼会议室音响结果
	 */
	public void setTfmsresult(String tfmsresult) {
		this.tfmsresult = tfmsresult;
	}
    /**
	 *  get 三楼会议室音响备注
	 */
    public String getTfmsremarks() {
		return tfmsremarks;
	}

    /**
	 *  set 三楼会议室音响备注
	 */
	public void setTfmsremarks(String tfmsremarks) {
		this.tfmsremarks = tfmsremarks;
	}
    /**
	 *  get 三楼会议室话筒
	 */
    public String getTfmm() {
		return tfmm;
	}

    /**
	 *  set 三楼会议室话筒
	 */
	public void setTfmm(String tfmm) {
		this.tfmm = tfmm;
	}
    /**
	 *  get 三楼会议室话筒结果
	 */
    public String getTfmmresult() {
		return tfmmresult;
	}

    /**
	 *  set 三楼会议室话筒结果
	 */
	public void setTfmmresult(String tfmmresult) {
		this.tfmmresult = tfmmresult;
	}
    /**
	 *  get 三楼会议室话筒备注
	 */
    public String getTfmmremarks() {
		return tfmmremarks;
	}

    /**
	 *  set 三楼会议室话筒备注
	 */
	public void setTfmmremarks(String tfmmremarks) {
		this.tfmmremarks = tfmmremarks;
	}
    /**
	 *  get 三楼会议室云控宝
	 */
    public String getTfmc() {
		return tfmc;
	}

    /**
	 *  set 三楼会议室云控宝
	 */
	public void setTfmc(String tfmc) {
		this.tfmc = tfmc;
	}
    /**
	 *  get 三楼会议室云控宝结果
	 */
    public String getTfmcresult() {
		return tfmcresult;
	}

    /**
	 *  set 三楼会议室云控宝结果
	 */
	public void setTfmcresult(String tfmcresult) {
		this.tfmcresult = tfmcresult;
	}
    /**
	 *  get 三楼会议室云控宝备注
	 */
    public String getTfmcremarks() {
		return tfmcremarks;
	}

    /**
	 *  set 三楼会议室云控宝备注
	 */
	public void setTfmcremarks(String tfmcremarks) {
		this.tfmcremarks = tfmcremarks;
	}
    /**
	 *  get 五楼会议室
	 */
    public String getFfm() {
		return ffm;
	}

    /**
	 *  set 五楼会议室
	 */
	public void setFfm(String ffm) {
		this.ffm = ffm;
	}
    /**
	 *  get 五楼会议室幕布1
	 */
    public String getFfmc() {
		return ffmc;
	}

    /**
	 *  set 五楼会议室幕布1
	 */
	public void setFfmc(String ffmc) {
		this.ffmc = ffmc;
	}
    /**
	 *  get 五楼会议室幕布1结果
	 */
    public String getFfmcresult() {
		return ffmcresult;
	}

    /**
	 *  set 五楼会议室幕布1结果
	 */
	public void setFfmcresult(String ffmcresult) {
		this.ffmcresult = ffmcresult;
	}
    /**
	 *  get 五楼会议室幕布1备注
	 */
    public String getFfmcremarks() {
		return ffmcremarks;
	}

    /**
	 *  set 五楼会议室幕布1备注
	 */
	public void setFfmcremarks(String ffmcremarks) {
		this.ffmcremarks = ffmcremarks;
	}
	/**
	 *  get 五楼会议室幕布2
	 */
	public String getFfmc2() {
		return ffmc2;
	}

	/**
	 *  set 五楼会议室幕布
	 */
	public void setFfmc2(String ffmc2) {
		this.ffmc2 = ffmc2;
	}
	/**
	 *  get 五楼会议室幕布2结果
	 */
	public String getFfmc2result() {
		return ffmc2result;
	}

	/**
	 *  set 五楼会议室幕布2结果
	 */
	public void setFfmc2result(String ffmc2result) {
		this.ffmc2result = ffmc2result;
	}
	/**
	 *  get 五楼会议室幕布2备注
	 */
	public String getFfmc2remarks() {
		return ffmc2remarks;
	}

	/**
	 *  set 五楼会议室幕布2备注
	 */
	public void setFfmc2remarks(String ffmc2remarks) {
		this.ffmc2remarks = ffmc2remarks;
	}
    /**
	 *  get 五楼会议室投影仪
	 */
    public String getFfmp() {
		return ffmp;
	}

    /**
	 *  set 五楼会议室投影仪
	 */
	public void setFfmp(String ffmp) {
		this.ffmp = ffmp;
	}
    /**
	 *  get 五楼会议室投影仪结果
	 */
    public String getFfmpresult() {
		return ffmpresult;
	}

    /**
	 *  set 五楼会议室投影仪结果
	 */
	public void setFfmpresult(String ffmpresult) {
		this.ffmpresult = ffmpresult;
	}
    /**
	 *  get 五楼会议室投影仪备注
	 */
    public String getFfmpremarks() {
		return ffmpremarks;
	}

    /**
	 *  set 五楼会议室投影仪备注
	 */
	public void setFfmpremarks(String ffmpremarks) {
		this.ffmpremarks = ffmpremarks;
	}
    /**
	 *  get 研究院四楼会议室
	 */
    public String getIfofm() {
		return ifofm;
	}

    /**
	 *  set 研究院四楼会议室
	 */
	public void setIfofm(String ifofm) {
		this.ifofm = ifofm;
	}
    /**
	 *  get 研究院四楼会议室幕布
	 */
    public String getIfofmc() {
		return ifofmc;
	}

    /**
	 *  set 研究院四楼会议室幕布
	 */
	public void setIfofmc(String ifofmc) {
		this.ifofmc = ifofmc;
	}
    /**
	 *  get 研究院四楼会议室幕布结果
	 */
    public String getIfofmcresult() {
		return ifofmcresult;
	}

    /**
	 *  set 研究院四楼会议室幕布结果
	 */
	public void setIfofmcresult(String ifofmcresult) {
		this.ifofmcresult = ifofmcresult;
	}
    /**
	 *  get 研究院四楼会议室幕布备注
	 */
    public String getIfofmcremarks() {
		return ifofmcremarks;
	}

    /**
	 *  set 研究院四楼会议室幕布备注
	 */
	public void setIfofmcremarks(String ifofmcremarks) {
		this.ifofmcremarks = ifofmcremarks;
	}
    /**
	 *  get 研究院四楼会议室投影仪
	 */
    public String getIfofmp() {
		return ifofmp;
	}

    /**
	 *  set 研究院四楼会议室投影仪
	 */
	public void setIfofmp(String ifofmp) {
		this.ifofmp = ifofmp;
	}
    /**
	 *  get 研究院四楼会议室投影仪结果
	 */
    public String getIfofmpresult() {
		return ifofmpresult;
	}

    /**
	 *  set 研究院四楼会议室投影仪结果
	 */
	public void setIfofmpresult(String ifofmpresult) {
		this.ifofmpresult = ifofmpresult;
	}
    /**
	 *  get 研究院四楼会议室投影仪备注
	 */
    public String getIfofmpremarks() {
		return ifofmpremarks;
	}

    /**
	 *  set 研究院四楼会议室投影仪备注
	 */
	public void setIfofmpremarks(String ifofmpremarks) {
		this.ifofmpremarks = ifofmpremarks;
	}
    /**
	 *  get 研究院五楼会议室
	 */
    public String getIffm() {
		return iffm;
	}

    /**
	 *  set 研究院五楼会议室
	 */
	public void setIffm(String iffm) {
		this.iffm = iffm;
	}
    /**
	 *  get 研究院五楼会议室幕布
	 */
    public String getIffmc() {
		return iffmc;
	}

    /**
	 *  set 研究院五楼会议室幕布
	 */
	public void setIffmc(String iffmc) {
		this.iffmc = iffmc;
	}
    /**
	 *  get 研究院五楼会议室幕布结果
	 */
    public String getIffmcresult() {
		return iffmcresult;
	}

    /**
	 *  set 研究院五楼会议室幕布结果
	 */
	public void setIffmcresult(String iffmcresult) {
		this.iffmcresult = iffmcresult;
	}
    /**
	 *  get 研究院五楼会议室幕布备注
	 */
    public String getIffmcremarks() {
		return iffmcremarks;
	}

    /**
	 *  set 研究院五楼会议室幕布备注
	 */
	public void setIffmcremarks(String iffmcremarks) {
		this.iffmcremarks = iffmcremarks;
	}
    /**
	 *  get 研究院五楼会议室音响
	 */
    public String getIffms() {
		return iffms;
	}

    /**
	 *  set 研究院五楼会议室音响
	 */
	public void setIffms(String iffms) {
		this.iffms = iffms;
	}
    /**
	 *  get 研究院五楼会议室音响结果
	 */
    public String getIffmsresult() {
		return iffmsresult;
	}

    /**
	 *  set 研究院五楼会议室音响结果
	 */
	public void setIffmsresult(String iffmsresult) {
		this.iffmsresult = iffmsresult;
	}
    /**
	 *  get 研究院五楼会议室音响备注
	 */
    public String getIffmsremarks() {
		return iffmsremarks;
	}

    /**
	 *  set 研究院五楼会议室音响备注
	 */
	public void setIffmsremarks(String iffmsremarks) {
		this.iffmsremarks = iffmsremarks;
	}
    /**
	 *  get 研究院五楼会议室话筒
	 */
    public String getIffmm() {
		return iffmm;
	}

    /**
	 *  set 研究院五楼会议室话筒
	 */
	public void setIffmm(String iffmm) {
		this.iffmm = iffmm;
	}
    /**
	 *  get 研究院五楼会议室话筒结果
	 */
    public String getIffmmresult() {
		return iffmmresult;
	}

    /**
	 *  set 研究院五楼会议室话筒结果
	 */
	public void setIffmmresult(String iffmmresult) {
		this.iffmmresult = iffmmresult;
	}
    /**
	 *  get 研究院五楼会议室话筒备注
	 */
    public String getIffmmremarks() {
		return iffmmremarks;
	}

    /**
	 *  set 研究院五楼会议室话筒备注
	 */
	public void setIffmmremarks(String iffmmremarks) {
		this.iffmmremarks = iffmmremarks;
	}
    /**
	 *  get 创建时间
	 */
    public String getCreattime() {
		return creattime;
	}

    /**
	 *  set 创建时间
	 */
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}

}

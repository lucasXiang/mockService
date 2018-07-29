package com.ideacome.mock.returnDTO;

import java.io.Serializable;

public class ResultMsg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -623056041603339427L;
	
	private int errorCode;
	
	private String errorMsg;
	
	private String tbsn;//汽车之家流水号，下单接口返回，支付接口回传
	
	private String bsPoint;//商业险佣金点数
	
	private String bzPoint;//交强险佣金点数
	
	private String viPoint;//商业险应收点数
	
	private String tiPoint;//交强险应收点数
	
	private String vpPoint;//商业险应付点数(去通道费)
	
	private String tpPoint;//交强险应付点数(去通道费)
	
	private String ccPoint;//通道费费率

	public int getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}

	public String getErrorMsg()
	{
		return errorMsg;
	}

	public void setErrorMSG(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getTbsn() {
		return tbsn;
	}

	public void setTbsn(String tbsn) {
		this.tbsn = tbsn;
	}

    public String getBsPoint() {
        return bsPoint;
    }

    public void setBsPoint(String bsPoint) {
        this.bsPoint = bsPoint;
    }

    public String getBzPoint() {
        return bzPoint;
    }

    public void setBzPoint(String bzPoint) {
        this.bzPoint = bzPoint;
    }

    public String getViPoint() {
        return viPoint;
    }

    public void setViPoint(String viPoint) {
        this.viPoint = viPoint;
    }

    public String getTiPoint() {
        return tiPoint;
    }

    public void setTiPoint(String tiPoint) {
        this.tiPoint = tiPoint;
    }

    public String getVpPoint() {
        return vpPoint;
    }

    public void setVpPoint(String vpPoint) {
        this.vpPoint = vpPoint;
    }

    public String getTpPoint() {
        return tpPoint;
    }

    public void setTpPoint(String tpPoint) {
        this.tpPoint = tpPoint;
    }

    public String getCcPoint() {
        return ccPoint;
    }

    public void setCcPoint(String ccPoint) {
        this.ccPoint = ccPoint;
    }
    
    public static ResultMsg newInstance(int code,String errorMsg){
    	ResultMsg rm = new ResultMsg();
    	rm.setErrorCode(code);
    	rm.setErrorMsg(errorMsg);
    	return rm;
    }

}

package com.ideacome.mock.MemCache;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Description 配置信息
 * @author 展昭
 * @date 创建时间：2015年7月16日 下午2:59:10
 * @version 1.0
 */
public class PushConfig {

	private static final ResourceBundle PARTERNERS_RB = ResourceBundle.getBundle("config.Partners", Locale.getDefault(),
			PushConfig.class.getClassLoader());

	/** 第三方公钥 */
	public static final String PARTNER_PUBLIC_KEY = PARTERNERS_RB.getString("PARTNER_PUBLIC_KEY").trim();

	/** 第三方私钥 */
	public static final String PARTNER_PRIVATE_KEY = PARTERNERS_RB.getString("PARTNER_PRIVATE_KEY").trim();

	/** ONS AccessKey */
	public static final String ACCESS_KEY = PARTERNERS_RB.getString("ACCESS_KEY").trim();

	/** ONS SecretKey */
	public static final String SECRET_KEY = PARTERNERS_RB.getString("SECRET_KEY").trim();
	/** ONS 开关 */
	public static final String ONS_SWITCH_STATUS = PARTERNERS_RB.getString("ONS_SWITCH_STATUS").trim();

	/** 加密算法 **/
	public static final String ALGORITHM = "MD5WithRSA";

	/** 订阅者ID **/
	public static final String CONSUMERID = PARTERNERS_RB.getString("CUSTOMER_ID").trim();

	/** 消息主题 **/
	public static final String TOPIC = PARTERNERS_RB.getString("TOPIC").trim();
	/** 推送失败记录的时间间隔 **/
	public static final int PUSH_VALID_INTERVAL = Integer
			.parseInt(PARTERNERS_RB.getString("push_valid_interval").trim());
	/** 根据推送结果标志查询需要重复推送的数据 **/
	public static final String PUSH_REPEAT_STATUS = PARTERNERS_RB.getString("push_repeat_status").trim();

	/** 线程池开启总数 **/
	public static final String THREAD_OPEN_TOTAL_COUNT = PARTERNERS_RB.getString("THREAD_OPEN_TOTAL_COUNT").trim();
	/** 单次查询记录条数 **/
	public static final String ORDER_DATA_QUERY_ONCE = PARTERNERS_RB.getString("ORDER_DATA_QUERY_ONCE").trim();
	
	/** 灿谷获取tokenURL **/
    public static final String CGXM_TOKEN_URL = PARTERNERS_RB.getString("CGXM_TOKEN_URL").trim();
    /** 灿谷获取tokenURL **/
    public static final String CGXM_TOKEN_APPID = PARTERNERS_RB.getString("CGXM_TOKEN_APPID").trim();
    /** 灿谷获取tokenURL **/
    public static final String CGXM_TOKEN_APPSECRET = PARTERNERS_RB.getString("CGXM_TOKEN_APPSECRET").trim();
    /** 支付订单页URL **/
    public static final String FEIXIA_PAY_ORDER_URL = PARTERNERS_RB.getString("FEIXIA_PAY_ORDER_URL").trim();



	/* 渠道推送参数 */
	private static Map<String, Object> pushConfigMap = new HashMap<String, Object>();

	public static Map<String, Object> getPushConfigMap() {
		return pushConfigMap;
	}

	public static void setPushConfigMap(Map<String, Object> pushConfigMap) {
		PushConfig.pushConfigMap = pushConfigMap;
	}

	
}

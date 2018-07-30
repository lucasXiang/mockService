package com.ideacome.mock.controller;

import com.alibaba.fastjson.JSONObject;
import com.ideacome.mock.MemCache.CacheOCS;
import com.ideacome.mock.cache.CacheOS;
import com.ideacome.mock.model.ChannelPushConfig;
import com.ideacome.mock.returnDTO.ResultMsg;
import com.ideacome.mock.service.ChannelPushConfigService;
import org.apache.log4j.Logger;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mock")
public class ConsumerController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ChannelPushConfigService channelPushConfigService;
	
	@RequestMapping("/callBackParam")
	public ResultMsg callBackParam(HttpServletRequest req){
		String str = "";
		try {
			if(req.getMethod().equals(RequestMethod.POST.name())){
				BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String next = "";  
	            while((next=br.readLine())!=null){  
	                sb.append(next);
	            }
				str = sb.toString();
				if(str.contains("data")){
					str = "{'data':"+new String(Base64.decode(JSONObject.parseObject(str).getString("data")),"utf-8")+"}";
				}else{
					str = new String(Base64.decode(str),"utf-8");
				}
			}else if(req.getMethod().equals(RequestMethod.GET.name())){
				Map<String,String[]> map = (Map<String,String[]>)req.getParameterMap();
				str = JSONObject.toJSONString(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.info("收到参数："+str);
		logger.info("remoteHost:"+req.getRemoteHost());
		logger.info("remoteAddr:"+req.getRemoteAddr());
		
		CacheOS.put(req.getRemoteHost()+"-Method:"+req.getMethod()+"-"+UUID.randomUUID().toString().substring(0, 8), new Date(), str);
		
		return ResultMsg.newInstance(10000, "成功接收参数！");
	}
	@RequestMapping("/testCache")
	public ResultMsg testCache(String key,HttpServletRequest req){
		String[] keys = key.split(",");
		
		System.out.println("缓存的值："+JSONObject.toJSONString(CacheOCS.cacheGet(keys[0], keys[1])));
		return ResultMsg.newInstance(10000, "缓存的值:"+JSONObject.toJSONString(CacheOCS.cacheGet(keys[0], keys[1])));
	}
	
	@RequestMapping("/updateOneChannelPushConf")
	public ResultMsg updateOneChannelPushConf(ChannelPushConfig config){
		System.out.println(JSONObject.toJSONString(config));
		channelPushConfigService.updateOneChannelPushInfoSelective(config);
		return ResultMsg.newInstance(200, "更新成功！");
	}
	
	@RequestMapping("/addOneChannelPushConf")
	public ResultMsg addOneChannelPushConf(ChannelPushConfig config){
		
		channelPushConfigService.addOneChannelPushConfSelective(config);
		return ResultMsg.newInstance(200, "更新成功！");
	}
}

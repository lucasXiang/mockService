package com.ideacome.mock.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.ideacome.mock.cache.CacheOS;
import com.ideacome.mock.cache.MetaData;
import com.ideacome.mock.model.ChannelPushConfig;
import com.ideacome.mock.service.ChannelPushConfigService;

@Controller
public class IndexController {
	@Autowired
	private ChannelPushConfigService channelPushConfigService;
	
	@RequestMapping("/index")
    public String index(Model model){
		Map<String, MetaData> mapList = CacheOS.getMapList();
		
		model.addAttribute("value", JSONObject.toJSONString(mapList).replaceAll("},", "}<br>"));
        return "test";
    }
	
	@RequestMapping("/getChannelPushConfigInfo")
	public String getChannelPushConfigInfo(String channelNo,HttpServletRequest req,Model model ){
		List<ChannelPushConfig> channelPushConfigs = channelPushConfigService.getAllChannelPushConfig(channelNo);
		model.addAttribute("channelPushConfigs", channelPushConfigs);
		model.addAttribute("channelNo", channelNo);
		return "channelPushConfigList";
	}
	@RequestMapping("/toAddPage")
	public String toAddPage(){
		
		return "addOneChannelPushConf";
	}
	@RequestMapping("/getFileOperateHtml")
	public String getFileOperateHtml() {
		
		return "fileTest";
	}
}

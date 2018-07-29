package com.ideacome.mock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideacome.mock.mapper.ChannelPushConfigMapper;
import com.ideacome.mock.model.ChannelPushConfig;
import com.ideacome.mock.model.ChannelPushConfigExample;
import com.ideacome.mock.service.ChannelPushConfigService;

@Service
public class ChannelPushConfigServiceImpl implements ChannelPushConfigService{
	
	@Autowired
	private ChannelPushConfigMapper channelPushConfigMapper;
	
	public void testServiceExists(){
		System.out.println(channelPushConfigMapper);
		
		ChannelPushConfig cpc = channelPushConfigMapper.selectByPrimaryKey(2);
		System.out.println(cpc.getChannelNo());
	}
	
	public List<ChannelPushConfig> getAllChannelPushConfig(String channelNo){
		ChannelPushConfigExample example = new ChannelPushConfigExample();
		if(channelNo != null && !"".equals(channelNo)){
			example.createCriteria().andChannelNoEqualTo(channelNo);
		}
		return getChannelPushInfoByEg(example);
	}
	
	private List<ChannelPushConfig> getChannelPushInfoByEg(ChannelPushConfigExample example){
		return channelPushConfigMapper.selectByExample(example);
	}
	
	public void updateOneChannelPushInfoSelective(ChannelPushConfig record){
		channelPushConfigMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void addOneChannelPushConfSelective(ChannelPushConfig record) {
		channelPushConfigMapper.insertSelective(record);
		
	}

}

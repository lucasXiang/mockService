package com.ideacome.mock.service;

import java.util.List;

import com.ideacome.mock.model.ChannelPushConfig;

public interface ChannelPushConfigService {
	void testServiceExists();
	List<ChannelPushConfig> getAllChannelPushConfig(String channelNo);
	void updateOneChannelPushInfoSelective(ChannelPushConfig record);
	void addOneChannelPushConfSelective(ChannelPushConfig record);
}

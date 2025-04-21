package com.mobinogi.rank;

import org.junit.jupiter.api.Test;

import com.mobinogi.mainService.RankCollectService;
class RankCollectServiceTest {

	RankCollectService rankCollectService = new RankCollectService();
	
	@Test
	public void testRankCollect() throws Exception {
		rankCollectService.rankCollect(1, 20, 2, 1285686831, null);
		
	}
}

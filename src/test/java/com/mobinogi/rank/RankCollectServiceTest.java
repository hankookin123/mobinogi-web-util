package com.mobinogi.rank;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mobinogi.controller.UpdateScheduler;
import com.mobinogi.dao.RankDao;
import com.mobinogi.dto.rank.RankingListDto;
import com.mobinogi.etc.ClassCode;
import com.mobinogi.mainService.RankCollectService;
import com.mobinogi.mainService.SearchLoofService;
class RankCollectServiceTest{

	
	@Test
	public void testRankCollect() throws Exception {
		RankCollectService rankCollectService = new RankCollectService();
		List<RankingListDto> rankingList = new ArrayList<>();
		// (전투력랭킹(생활,매력), 페이지번호(20개 순위), 서버, 직업, 검색어)
		rankCollectService.rankCollect(1, 50, 1, ClassCode.대검전사, null, rankingList);
		for (RankingListDto dto : rankingList) {
			System.out.printf("%s, %s, %s, %s, %s\n",
					dto.getRank(),
					ClassCode.코드_직업맵.get(dto.getClazz()),
					dto.getCharName(),
					dto.getPower(),
					dto.getServer()
					);
			
		}
		
	}
	
	@Test
	public void testSearchLoofServiceMock() {
		RankDao mokDao = mock(RankDao.class);
		SearchLoofService sls = new SearchLoofService(mokDao);
		try {
			sls.dbUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchLoofService() {
		RankDao dao = new RankDao() {
			
			@Override
			public void deianInsert(List<RankingListDto> list) {
				// TODO Auto-generated method stub
				
			}
		};
		SearchLoofService sls = new SearchLoofService(dao);
		try {
			sls.dbUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

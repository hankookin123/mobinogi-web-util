package com.mobinogi.mainService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mobinogi.dao.RankDao;
import com.mobinogi.dto.rank.RankingListDto;
import com.mobinogi.etc.ClassCode;

// DB에 등록하는곳
@Service
public class SearchLoofService {
	
	private RankCollectService rankCollectService = new RankCollectService();
	private RankDao dao;
	
	// 이렇게 객체를 new로 안만들고 파라미터로 받아오는 것을 의존성 주입이라하고,
	// 테스트 용이성,결합도 감소 같은 설명들은 rankdao랑 연결시킨 것이 아니라 '받아서 사용한다'라고 코드를 짜서다.
	public SearchLoofService(RankDao dao) {
		this.dao = dao;
	}
	
	public void dbUpdate() throws Exception {
		
		Random random =new Random();
		
		LocalDateTime now1 = LocalDateTime.now();
		System.out.println("데이터수집 시작 : " + now1);
		
		// 데이안서버(서버번호: 1)의 데이터만 수집.
		int total_search = 0;
		for (Integer code : ClassCode.직업_코드맵.values()) {
			List<RankingListDto> rankingList = new ArrayList<>();
			for (int i = 0; i < 50; i++) {
				total_search++;
				try {
					rankCollectService.rankCollect(1, i, 1, code, null, rankingList);
					Thread.sleep(random.nextInt(500)+2500);
					System.out.println("총 횟수 : " + total_search + "  /  " 
							+ ClassCode.코드_직업맵.get(code) + " 페이지 : " + i);
				} catch (InterruptedException  e) {
					// 인터럽 복구 해줘야 한다?
					Thread.currentThread().interrupt();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			dao.deianInsert(rankingList);
		}		
		
		LocalDateTime now2 = LocalDateTime.now();
		System.out.println("데이터수집 시작 : " + now1);
		System.out.println("데이터수집 종료 : " + now2);
	}
	
}

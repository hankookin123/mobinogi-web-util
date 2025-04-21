package com.mobinogi.mainService;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.mobinogi.dto.rank.RankingListDto;

@Service
public class RankCollectService {

	public List<RankingListDto> rankCollect(int t, int pageno, int s, int c, String search) throws Exception {
		
		// url에 파라미터 넣고 post 형식으로 요청보냄. get요청 보내면 s,c(서버,직업) 값을 못넣음.
		String url = "https://mabinogimobile.nexon.com/Ranking/List/rankdata";
		MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
		formData.add("t", String.valueOf(t));
		formData.add("pageno", String.valueOf(pageno));
		formData.add("s", String.valueOf(s));
		formData.add("c", String.valueOf(c));
		formData.add("search", search == null ? "" : search);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("X-Requested-With", "XMLHttpRequest");
		headers.add("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
		// 쿠키 안보내면 인증에서 막히는지 에러페이지를 반환받음
		headers.add("Cookie",
				"PCID=17294151823800250661324; mmtoken=Xz-GDxIro3bLndfgci1Tjek0_u9ioXtG6pHGXcL"
						+ "bQ9kNRuDfP8TND6rQoHezqYKBb5hm_pyiOwc_kDF1mV1f6ikR1kFjl8CxfutuugKRTk"
						+ "I1:0UkKj_0q9AYqDObv5vJKJFXPgIqfw8vNoKVE1LANZqgE2XB0jplW8iELoovw_NSE"
						+ "YLx4c4LDx8ePOIu_R0hLX9C_SrU9-kkyMzWnhTD8U7w1;" + " _ga=GA1.3.1318128691.1729415183");

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
		// 응답받는 html 코드 확인 System.out.println("응답 본문: " + response.getBody());
		Document doc = Jsoup.parse(response.getBody());
		Elements rankItems = doc.select("ul.list > li.item");

		if (rankItems.isEmpty()) {
			System.out.println("랭킹 데이터가 없습니다. 파라미터를 다시 확인하세요.");
		}

		// dto에 데이터 추가
		List<RankingListDto> rankingList = new ArrayList<>();
		for (Element item : rankItems) {
			  String rankText = item.select("dl:has(dt:matches(\\d+위)) dt").text(); 
			  String rank = rankText.replaceAll("[^\\d]", ""); 
			  String server = item.select("dl:has(dt:matches(서버명)) dd").text(); 
			  String charName = item.select("dl:has(dt:matches(캐릭터명)) dd").attr("data-charactername"); 
			  String clazz = item.select("dl:has(dt:matches(클래스)) dd").text(); 
			  String power = item.select("dl:has(dt:matches(전투력)) dd").text();
			  
			  RankingListDto dto = new RankingListDto(rank, server, charName, clazz, power);
			  rankingList.add(dto);
//			  System.out.printf("%s, %s,  %s, %s, %s\n", rank, server, charName, clazz,
//			  power);
			 
		}
		for (RankingListDto dto : rankingList) {
			System.out.printf("%s, %s, %s, %s, %s\n",dto.getRank(), dto.getServer(), dto.getCharName(),
					dto.getClazz(), dto.getPower());
		}
		
		return rankingList;
	}
}

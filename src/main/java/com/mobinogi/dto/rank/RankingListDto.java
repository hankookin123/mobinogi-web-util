package com.mobinogi.dto.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RankingListDto {
	
	private String rank;
	private String server;
	private String charName;
	private String clazz;
	private String power;
}

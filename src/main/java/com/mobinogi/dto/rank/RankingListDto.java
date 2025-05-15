package com.mobinogi.dto.rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RankingListDto {
	
	private int rank;
	private int server;
	private String charName;
	private int clazz;
	private int power;
}

package com.mobinogi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mobinogi.dto.rank.RankingListDto;

@Mapper
public interface RankDao {
	
	public List<RankingListDto> rankInsert(
			@Param("rank") String rank,
			@Param("server") String server,
			@Param("charName") String charName,
			@Param("clazz") String clazz,
			@Param("power") String power);
}

package com.mobinogi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mobinogi.dto.rank.RankingListDto;

@Mapper
public interface RankDao {
	
	public void deianInsert(@Param("list") List<RankingListDto> list);
}

package com.mobinogi.rank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobinogi.controller.UpdateScheduler;
import com.mobinogi.dao.RankDao;
import com.mobinogi.mainService.SearchLoofService;

@SpringBootTest
public class UpdateSchedulerTest {

	@Autowired
    private RankDao rankDao;

    @Autowired
    private SearchLoofService searchLoofService;

    @Autowired
    private UpdateScheduler updateScheduler;
    
    @Test
    public void testDbupdate() throws Exception {
    	updateScheduler.dbUpdateSchedule();
    	// ec2 커널링으로 db 접속하려는데 권한이 없다는 오류 지속적으로 발생.
    }
    
    @Test
    public void SearchLoofServiceTest() throws Exception {
    	searchLoofService.dbUpdate();
    }
}

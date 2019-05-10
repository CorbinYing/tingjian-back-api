package org.corbin.client;

import org.corbin.client.repository.CollectInfoRepository;
import org.corbin.client.repository.SingerInfoRepository;
import org.corbin.client.repository.SongInfoRepository;
import org.corbin.common.entity.SingerInfo;
import org.corbin.common.entity.SongInfo;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientApplicationTests {
    @Autowired
    private CollectInfoRepository collectInfoRepository;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private SingerInfoRepository singerInfoRepository;


public void t2est(@NotNull Integer i){}

/*    @Test
    public void ef() {
      List<CollectInfo>list= collectInfoRepository.findtest();

    }*/


@Test
    public void dbh(){

    List <SongInfo> songInfoList=songInfoRepository.findAll();

    for (SongInfo songInfo:songInfoList){

        if (singerInfoRepository.findBySingerId(songInfo.getSingerId())==null){
            SingerInfo singerInfo=new SingerInfo();
            singerInfo.setSingerId(songInfo.getSingerId());
            singerInfoRepository.save(singerInfo);
        }


    }



}

}


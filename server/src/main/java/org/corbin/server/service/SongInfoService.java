package org.corbin.server.service;

import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.SongInfo;
import org.corbin.server.repository.SongInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SongInfoService extends BaseService {
    @Autowired
    private SongInfoRepository songInfoRepository;



    /**
     * 获取最新30天歌曲信息
     * @return
     */
    public List<SongInfo> getLast30DaysSongList() {
        Date date = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000);
        return songInfoRepository.findAllBySongShelfTimeAfterOrderByCreateTime(date);
    }


}

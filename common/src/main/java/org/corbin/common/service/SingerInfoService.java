package org.corbin.common.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.SingerInfo;
import org.corbin.common.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class SingerInfoService extends BaseService <SingerInfo,Long>{
    @Autowired
    private CollectInfoRepository collectInfoRepository;
    @Autowired
    private CommentInfoRepository commentInfoRepository;
    @Autowired
    private SingerInfoRepository singerInfoRepository;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;


    public SingerInfo findBySingerName(String singerName) {
        if (singerName == null) {
            log.info("singerName 为空");
            return null;
        }
        return singerInfoRepository.findBySingerName(singerName);
    }


    public SingerInfo insertSinger(@NonNull SingerInfo singerInfo) {
       return singerInfoRepository.saveAndFlush(singerInfo);
    }


    /**
     * 获取最喜欢的3位歌手的信息
     * @param userId
     * @return
     */
    List<SingerInfo> findMost3LoveSinger(@NonNull Long userId) {
        List<SingerInfo> singerInfoList = singerInfoRepository.findAllCollectSongInWithRepeat(userId);
        if (singerInfoList == null) {
            log.info("未找到收藏歌曲的歌手，用户id" + userId);
            return null;
        }
        //歌手名，出现次数
        Map<String, Integer> singerNameMap = Maps.newHashMap();
        for (SingerInfo singerInfo : singerInfoList) {
            if (singerNameMap.containsKey(singerInfo.getSingerName())) {
                singerNameMap.put(singerInfo.getSingerName(),
                        singerNameMap.get(singerNameMap.get(singerInfo.getSingerName()) + 1));
            } else {
                singerNameMap.put(singerInfo.getSingerName(), 1);
            }
        }

        //
        List<Map.Entry<String, Integer>> collectSingerMapList = new ArrayList<Map.Entry<String, Integer>>(singerNameMap.entrySet());
        //通过Collections.sort(List I,Comparator c)方法进行排序
        Collections.sort(collectSingerMapList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        //频数最多的在前
        Collections.reverse(collectSingerMapList);
        System.err.println(collectSingerMapList);

        //获取最喜爱的三位歌手
        List<SingerInfo> mostLove3SingerList = Lists.newArrayList();
        for (int i = 0; i < collectSingerMapList.size(); i++) {
            SingerInfo singerInfo = singerInfoRepository.findBySingerName(collectSingerMapList.get(0).getKey());
            if (singerInfo != null) {
                mostLove3SingerList.add(singerInfo);
            }

            if (mostLove3SingerList.size() > 3) {
                break;
            }
        }

        return mostLove3SingerList;
    }

}

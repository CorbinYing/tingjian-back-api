package org.corbin.client.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentInfoService {
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
}

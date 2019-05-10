package org.corbin.server.service;

import org.corbin.common.base.service.BaseService;
import org.corbin.server.repository.SongStatisticsDayLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongStatisticsDayLogService extends BaseService {

    @Autowired
    private SongStatisticsDayLogRepository songStatisticsDayLogRepository;



}

package com.healist.jobhunter.dao;

import com.healist.jobhunter.entity.jobInfo;

/**
 * Created by Healist on 2016/11/6.
 */
public interface JobInfoDao {
    public int saveJobInfo(jobInfo jobInfo);
    public int checkUrl(String url);
}

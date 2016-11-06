package com.healist.jobhunter.service;

import com.healist.jobhunter.dao.JobInfoDao;
import com.healist.jobhunter.dbhelper.DBHelper;
import com.healist.jobhunter.entity.jobInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Healist on 2016/11/6.
 */
public class JobInfoDaoImpl implements JobInfoDao {

    DBHelper dbHelper;

    public JobInfoDaoImpl() {
        dbHelper = new DBHelper();
    }

    public int saveJobInfo(jobInfo jobInfo) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO jobInfo(`title`, `date`, `where`, `url`) VALUES(?, ?, ?, ?)");
        List<String> values = new ArrayList<String>();
        values.add(jobInfo.getTitle());
        values.add(jobInfo.getDate());
        values.add(jobInfo.getWhere());
        values.add(jobInfo.getUrl());
        int result = dbHelper.executeUpdate(sql.toString(), values);
        return result;
    }

    public int checkUrl(String url) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM jobInfo WHERE url = ?");
        List<String> values = new ArrayList<String>();
        values.add(url);
        int result = dbHelper.executeQuery(sql.toString(), values);
        return result;
    }
}

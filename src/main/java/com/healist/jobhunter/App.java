package com.healist.jobhunter;

import com.healist.jobhunter.dao.JobInfoDao;
import com.healist.jobhunter.entity.jobInfo;
import com.healist.jobhunter.service.JobInfoDaoImpl;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setTimeOut(30000).setSleepTime(100);
    private JobInfoDao jobInfoDao = new JobInfoDaoImpl();

    public void process(Page page) {

        if(page.getUrl().regex("http://gdjy\\.hfut\\.edu\\.cn/JobIn/JobMeetingInside\\.jsp\\?uid=[0-9]+").match()){

            String title = page.getHtml()
                    .xpath("//table/tbody/tr/td[2]/table/tbody/tr[2]/td[@align='center']/table/tbody/tr[2]/td[2]/text()").get();
            String date = page.getHtml()
                    .xpath("//table/tbody/tr/td[2]/table/tbody/tr[2]/td[@align='center']/table/tbody/tr[5]/td[2]/tidyText()").get();
            String where = page.getHtml()
                    .xpath("//table/tbody/tr/td[2]/table/tbody/tr[2]/td[@align='center']/table/tbody/tr[6]/td[2]/text()").get();
            if(title == null) {
                page.setSkip(true);
            }

            jobInfo jobInfo = new jobInfo();
            jobInfo.setTitle(title);
            jobInfo.setDate(date);
            jobInfo.setWhere(where);
            jobInfo.setUrl(page.getUrl().toString());
            int saveFlag = jobInfoDao.saveJobInfo(jobInfo);
            if(saveFlag > 0) {
                System.out.println("成功插入数据了");
            }
        } else {
            List<String> links = page.getHtml().xpath("//table/tbody/tr[9]/td[@colspan='8']").links().all();
            for (String link:links) {
                int check = jobInfoDao.checkUrl(link);
                if(check > 0) {
                    System.out.println("已经爬取过该url：" + link);
                } else {
                    page.addTargetRequest(link);
                }
            }
            //page.addTargetRequests(page.getHtml().xpath("//table/tbody/tr[9]/td[@colspan='8']").links().all());
            //System.out.println(page.getHtml().xpath("//table/tbody/tr[9]/td[@colspan='8']").links().all());
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main( String[] args )
    {
        Spider.create(new App())
                .addUrl("http://gdjy.hfut.edu.cn/").thread(1).run();
    }

}

package com.eastpro.batch.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;  
import org.springframework.stereotype.Component;  






import com.eastpro.batch.dao.TaskDao;
import com.eastpro.util.HtmlMail;

  
@Component  
public class CheckTimeSheetTask {  
	@Autowired
    private TaskDao taskDao;
	
    @Scheduled(cron="0 0 10 * * ?") 
    public void taskCycle(){  
        System.out.println("test spring mvc task for xh = " + new Date().toLocaleString());
		String SMTP = "";
	
		try {
			SMTP ="192.168.222.1";
			HtmlMail htmlMail = new HtmlMail();			
			String to ="xue.hua@eabsystems.com;peng.chong@eabsystems.com";
		    String from ="admin@eabsystems.com";
			
			htmlMail.setSubject("TIME SHEET");		
			
		
			boolean result = false;
			
			Map<String, String> map = null;
			

			htmlMail.setFrom(from);
			htmlMail.setFromName("ADMIN");
			htmlMail.setTo(to);
		
			System.out.println("=====Send mail======");
			
			String htmlText = "大冲。。。2016、06、06 你的timesheet没填好。赶紧填上。。。 ";
		
			String content = "";
			
			htmlText = "<table  width='98%' align='left' border='0' cellspacing='0' cellpadding='0'>"+
				"<tr><td align='left'> <font style=\"font-family:'Arial';font-size:12px;\" >"+ htmlText + "</font>" ;
		
			htmlText += "</td></tr></table>";
			htmlMail.setHtmlText(htmlText);
		

	
			

			htmlMail.setHost(SMTP);
		//	htmlMail.setUsername(mail_user);
		//	htmlMail.setPassword(mail_password);
			htmlMail.setContentType("text/html;charset=UTF-8");
			htmlMail.setReplyTo(from);

		
			htmlMail.setSubject("TIME SHEET");
			// 20071219-GY-S
		
			try {
				map = new HashMap<String, String>();
				result = htmlMail.sendEmail();			
				String rs = "-1";
				if (result) {
					rs = "1";
				}
				map.put("result", rs);
				map.put("size", htmlMail.getMailSize());
				System.out.println("mailAgent.getMailSize() = " + htmlMail.getMailSize());
			} catch (Exception e) {
				e.printStackTrace();
				map.put("failReason", e.getMessage());
			}
			// return mailAgent.send();
		
			// 20071219-GY-E
		
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
			throw new RuntimeException(e);
		}   
        
    }  
} 

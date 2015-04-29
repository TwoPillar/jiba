package com.twopillar.jiba.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/** * 获取指定日期是星期几
	  * 参数为null时表示获取当前日期是星期几
	  * @param date
	  * @return
	*/
	public static int getWeekOfDate(Date date) {      
	    int[] weekOfDays = {7, 1, 2, 3, 4, 5, 6};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(date);      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
}

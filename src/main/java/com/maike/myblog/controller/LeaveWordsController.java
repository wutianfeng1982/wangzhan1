package com.maike.myblog.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.maike.myblog.entity.LeaveWords;
import com.maike.myblog.service.ILeaveWordsService;
import com.maike.myblog.utils.GenerateIdUtil;
import com.maike.myblog.utils.Result;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author geekcjj
 * @since 2019-10-18
 */
@RestController
@RequestMapping("/leaveWords")
public class LeaveWordsController extends BaseController{
	@RequestMapping(value="/blog/listAllLeaveWords",method= {RequestMethod.POST,RequestMethod.GET})
	public Result blogListAllLW(@RequestParam(value="pageNum",required=false) String pageNum ,@RequestParam(value="pageSize",required=false) String pageSize) {
		if(pageNum==null||pageSize==null) {
			pageNum="1";
			pageSize="12";
		}
		PageInfo<LeaveWords> pageInfo=leaveWordsService.blogListAllLW(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return Result.success(pageInfo);
	}
	@RequestMapping(value="/blog/userLeaveWords",method= {RequestMethod.POST,RequestMethod.GET})
	public Result userLeaveWords(@RequestParam Map<String,Object> listMap) {
		LeaveWords leaveWords =new LeaveWords();
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		LocalDateTime localDateTime = LocalDateTime.now();
//		LocalDate.now();
//		LocalTime.now();
//		localDateTime3.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//		LocalDateTime localDateTime4 = localDateTime3.minus(23,ChronoUnit.MONTHS);
//		localDateTime4.atZone(ZoneId.systemDefault());
//		localDateTime4 = localDateTime4.withHour(3);
//		localDateTime4 = localDateTime4.withYear(2016);
//		localDateTime4 = localDateTime4.with(ChronoField.MONTH_OF_YEAR,3);
		leaveWords.setNickName((String) listMap.get("name"));
		leaveWords.setEmail((String) listMap.get("email"));
		leaveWords.setWebsite((String) listMap.get("myWebSite"));
		leaveWords.setContent((String) listMap.get("editordata-web"));
		leaveWords.setId(GenerateIdUtil.createCount());
		leaveWords.setCreateTime(localDateTime);
		int flag=leaveWordsService.insertblogUserLW(leaveWords);
		if(flag>0) {
			return Result.success(true);
		}
		return Result.error("留言失败!");
	}
}

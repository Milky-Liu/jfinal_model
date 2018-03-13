package com.test.util;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class SearchDataUtil {
	public static final int DEFAULT_PAGE = 1;
	public static final int DEFAULT_PAGE_SIZE = 5;
	
/**
 * 
 * 依靠SQL来做的分页查询，	
 * @param model 
 * @param selectSql 选择哪些字段的sql 
 * @param fromSql from以后的sql
 * @param c 控制器
 * @param params 参数替换值
 * @return 分页对象
 */
	public static Page PaginateQueryBySql(Model model,String selectSql,String fromSql,Controller c,Object...params){
		Page page=  model.paginate(c.getParaToInt("pageNum",DEFAULT_PAGE), c.getParaToInt("numPerPage",DEFAULT_PAGE_SIZE), selectSql, fromSql,params);
		c.keepModel(model.getClass());
		c.setAttr("pager", page);
		return page;
	};
	
	/**
	 * 根据sql来分页查询
	 * @param pageSize 每页显示数量
	 * @param model 是基于哪个model做的查询
	 * @param selectSql 要选哪些表的哪些字段
	 * @param fromSql 从from之后的sql语句
	 * @param c 控制器
	 * @param params 查询参数替换
	 * @return Page分页对象
	 */
	public static Page PaginateQueryBySql(int pageSize,Model model,String selectSql,String fromSql,Controller c,Object...params){
		Page page=  model.paginate(c.getParaToInt("pageNum",DEFAULT_PAGE), c.getParaToInt("numPerPage",pageSize), selectSql, fromSql,params);
		
		c.keepModel(model.getClass());
		c.setAttr("pager", page);
		return page;
	};
	
}

package com.test.model;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.test.util.SearchDataUtil;
 
@TableBind(tableName="Users")
public class SysCuser extends Model<SysCuser> {

	private static final long serialVersionUID = 1L;
	public static final SysCuser dao = new SysCuser();


	public List<SysCuser> getList(Controller c) {
		Page page = SearchDataUtil.PaginateQueryBySql(new SysCuser(), "select u.*", "from Users u", c);
		return page.getList();
	}
}

package com.test.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.test.model.SysCuser;

@ControllerBind(controllerKey = "/cuser", viewPath = "/")
public class CuserController extends Controller {

	public void index() {
		List<SysCuser> list = SysCuser.dao.getList(this);
		setAttr("cusers", list);
		renderJsp("list1.jsp");
	}

}

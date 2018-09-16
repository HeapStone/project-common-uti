package ${Paramss.packageNameStr}.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.guochun.psmc.authentication.login.model.User;
import priv.guochun.psmc.authentication.user.model.TabPerson;
import priv.guochun.psmc.system.framework.controller.MyController;
import priv.guochun.psmc.system.framework.page.MyPage;
import priv.guochun.psmc.system.util.JsonUtil;
import ${Paramss.importDominPackageStr};
import ${Paramss.importServicePackageStr}Service;

@Controller
@RequestMapping("${Paramss.controllerUrl}")
public class  ${Paramss.controllerName} extends MyController{

	@Autowired
	private ${Paramss.serviceName} ${Paramss.serviceArgName};
     /**
	 * 跳转到${Paramss.beanName}列表页面
	 * @throws IOException
	 */
	@RequestMapping(params="method=to${Paramss.beanName}List")
	public String to${Paramss.beanName}List(){
		return "${Paramss.beanName}/${Paramss.beanArgName}List";
	}
	/**
	 * ${Paramss.beanName} 分页查询
	 * @param myPage 分页对象
	 * @throws IOException
	 */
	@RequestMapping(params="method=find${Paramss.beanName}List")
	@ResponseBody
	public void find${Paramss.beanName}List(MyPage myPage) throws IOException{
		myPage = ${Paramss.serviceArgName}.${Paramss.findPageMethodName}(myPage);
		super.responseJson(JsonUtil.convertToJSONObject(myPage), this.response());
	}

	/**
	 * 新增 ${Paramss.beanName} 信息
	 * @param ${Paramss.beanArgName} ${Paramss.beanName} 信息
	 * @throws IOException
	 */
	@RequestMapping(params="method=save${Paramss.beanName}")
	public void save${Paramss.beanName}(${Paramss.beanName} ${Paramss.beanArgName}) throws IOException{
        ${Paramss.serviceArgName}.${Paramss.addMethodName}(${Paramss.beanArgName});
		super.responseJson(true, "新增成功!", this.response());
	}

	/**
	 * 删除${Paramss.beanName} 信息
	 * @param ids 要删除的ids
	 * @throws IOException
	 */
	@RequestMapping(params="method=delete${Paramss.beanName}")
	public void delete${Paramss.beanName}(String ids) throws IOException{
        ${Paramss.serviceArgName}.${Paramss.deleteMethodName}(ids);
		super.responseJson(true, "删除成功!", this.response());
	}
	/**
	 * 修改 ${Paramss.beanName} 信息
	 * @param ${Paramss.beanArgName} ${Paramss.beanName} 信息
	 * @throws IOException
	 */
	@RequestMapping(params="method=save${Paramss.beanName}")
	public void update${Paramss.beanName}(${Paramss.beanName} ${Paramss.beanArgName}) throws IOException{
        ${Paramss.serviceArgName}.${Paramss.updateMethodName}(${Paramss.beanArgName});
		super.responseJson(true, "修改成功!", this.response());
	}

	/**
	 * 跳转到${Paramss.beanName}编辑页面
	 * @param id ${Paramss.beanName}主键
	 * @param model
	 * @return
	 */
	@RequestMapping(params="method=toDeptEdit")
	public String to${Paramss.beanName}Edit(String id，Model model){
		if(StringUtils.isNotBlank(id)){
			Map<String, Object> ${Paramss.beanArgName} =  ${Paramss.serviceArgName}.${Paramss.findPrimarkeyMethodName}(id);
			model.addAttribute("${Paramss.beanArgName}", ${Paramss.beanArgName});
		}
		return "backstage/${Paramss.beanName}/${Paramss.beanArgName}Edit";
	}


	/**
	 * ${Paramss.beanName}详情查询
	 * @param id ${Paramss.beanName}主键
	 * @param model
	 * @return
	 */
	@RequestMapping(params="method=toDeptEdit")
	public String find${Paramss.beanName}Detail(String id){
		if(StringUtils.isNotBlank(id)){
			Map<String, Object> ${Paramss.beanArgName} =  ${Paramss.serviceArgName}.${Paramss.findPrimarkeyMethodName}(id);
		}
		super.responseJson(JsonUtil.convertToJSONObject(${Paramss.beanArgName}), this.response());
	}
}

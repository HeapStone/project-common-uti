package ${freemMarkParams.packageNameStr};
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.guochun.psmc.system.framework.controller.MyController;
import priv.guochun.psmc.system.framework.page.MyPage;
import priv.guochun.psmc.system.util.JsonUtil;
import ${freemMarkParams.importDominPackageStr};
import ${freemMarkParams.importServicePackageStr}.${freemMarkParams.serviceName};

@Controller
@RequestMapping("${freemMarkParams.controllerUrl}")
public class  ${freemMarkParams.fileName} extends MyController{

	@Autowired
	private ${freemMarkParams.serviceName} ${freemMarkParams.serviceArgName};

     /**
	 * 跳转到${freemMarkParams.fileName}列表页面
	 * @throws IOException
	 */
	@RequestMapping(params="method=to${freemMarkParams.fileName}List")
	public String to${freemMarkParams.fileName}List(){
		return "${freemMarkParams.beanArgName}/${freemMarkParams.beanArgName}List";
	}

	/**
	 * ${freemMarkParams.fileName} 分页查询
	 * @param myPage 分页对象
	 * @throws IOException
	 */
	@RequestMapping(params="method=find${freemMarkParams.fileName}List")
	@ResponseBody
	public void find${freemMarkParams.fileName}List(MyPage myPage) throws IOException{
		myPage = ${freemMarkParams.serviceArgName}.${freemMarkParams.findPageMethodName}(myPage);
		super.responseJson(JsonUtil.convertToJSONObject(myPage), this.response());
	}

	/**
	 * 新增 ${freemMarkParams.fileName} 信息
	 * @param ${freemMarkParams.beanArgName} ${freemMarkParams.fileName} 信息
	 * @throws IOException
	 */
	@RequestMapping(params="method=save${freemMarkParams.fileName}")
	public void save${freemMarkParams.fileName}(${freemMarkParams.beanName} ${freemMarkParams.beanArgName}) throws IOException{
		if(null== ${freemMarkParams.beanArgName}){
			super.responseJson(false, "新增失败!", this.response());
		}
        ${freemMarkParams.serviceArgName}.${freemMarkParams.addMethodName}(${freemMarkParams.beanArgName});
		super.responseJson(true, "新增成功!", this.response());
	}

	/**
	 * 删除${freemMarkParams.fileName} 信息
	 * @param ids 要删除的ids
	 * @throws IOException
	 */
	@RequestMapping(params="method=delete${freemMarkParams.fileName}")
	public void delete${freemMarkParams.fileName}(String ids) throws IOException{
         if(StringUtils.isBlank(ids)){
			super.responseJson(true, "删除失败!", this.response());
         }
        ${freemMarkParams.serviceArgName}.${freemMarkParams.deleteMethodName}(ids);
		super.responseJson(true, "删除成功!", this.response());
	}

	/**
	 * 修改 ${freemMarkParams.fileName} 信息
	 * @param ${freemMarkParams.beanArgName} ${freemMarkParams.fileName} 信息
	 * @throws IOException
	 */
	@RequestMapping(params="method=update${freemMarkParams.fileName}")
	public void update${freemMarkParams.fileName}(${freemMarkParams.beanName} ${freemMarkParams.beanArgName}) throws IOException{
		if(null== ${freemMarkParams.beanArgName}){
			super.responseJson(false, "更新失败!", this.response());
		}
        ${freemMarkParams.serviceArgName}.${freemMarkParams.updateMethodName}(${freemMarkParams.beanArgName});
		super.responseJson(true, "修改成功!", this.response());
	}

	/**
	 * 跳转到${freemMarkParams.fileName}编辑页面
	 * @param id ${freemMarkParams.fileName}主键
	 * @param model
	 * @return
	 */
	@RequestMapping(params="method=to${freemMarkParams.fileName}Edit")
	public String to${freemMarkParams.fileName}Edit(String id,Model model)throws IOException{
         if(StringUtils.isBlank(id)){
			super.responseJson(true, "编辑失败!", this.response());
         }
		if(StringUtils.isNotBlank(id)){
			Map<String, Object> ${freemMarkParams.beanArgName} =  ${freemMarkParams.serviceArgName}.${freemMarkParams.findPrimarkeyMethodName}(id);
			model.addAttribute("${freemMarkParams.beanArgName}", ${freemMarkParams.beanArgName});
		}
		return "backstage/${freemMarkParams.beanArgName}/${freemMarkParams.beanArgName}Edit";
	}

	/**
	 * ${freemMarkParams.fileName}详情查询
	 * @param id ${freemMarkParams.fileName}主键
	 * @return
	 */
	@RequestMapping(params="method=to${freemMarkParams.fileName}Detail")
	public void find${freemMarkParams.fileName}Detail(String id)throws IOException{
 		if(StringUtils.isBlank(id)){
			super.responseJson(true, "获取详情失败!", this.response());
         }
        Map<String, Object> ${freemMarkParams.beanArgName} = null;
		if(StringUtils.isNotBlank(id)){
		${freemMarkParams.beanArgName} =  ${freemMarkParams.serviceArgName}.${freemMarkParams.findPrimarkeyMethodName}(id);
		}
		super.responseJson(JsonUtil.convertToJSONObject(${freemMarkParams.beanArgName}), this.response());
	}
    
}

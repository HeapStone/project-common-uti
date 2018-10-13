package ${Paramss.packageNameStr};
<#--导包 -->
import java.util.Map;
import priv.guochun.psmc.system.framework.page.MyPage;
import ${Paramss.importDominPackageStr};
/**
** 注意这是spring注入配置文件，请复制到注入配置文件后删除
**	<bean id="${Paramss.serviceName ?uncap_first}" class="${Paramss.packageNameStr}.impl.${Paramss.serviceName}Impl" />
**
**/
<#if Paramss.beanContent!="">
    /*
     *${Paramss.beanContent}
     * Created by System
     */
</#if>
public interface ${Paramss.serviceName}{
   /**
	 * 分页查询
	 * @param page 分页对象
	 * @return
	 */
    MyPage ${Paramss.findByPageMethodName}(MyPage page);
     /**
	 * 根据主键查询
	 * @param page 分页对象
	 * @return
	 */
     Map<String,Object> ${Paramss.findByPrimarKeyMethodName}(String uuid);
     /**
	 * 根据主键批量删除
	 * @param ids 主键列表
	 * @return
	 */
     void ${Paramss.deleteByPrimarKeyMethodName}(String ids);
     /**
	 * 新增
	 * @param ${Paramss.domainArgName} 主键列表
	 * @return
	 */
     void ${Paramss.addMethodName}(${Paramss.domainName} ${Paramss.domainArgName});
     /**
	 * 修改
	 * @param ids 主键列表
	 * @return
	 */
      void ${Paramss.updateMethodName}(${Paramss.domainName}  ${Paramss.domainArgName});
}
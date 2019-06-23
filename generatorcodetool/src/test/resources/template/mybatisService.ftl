package ${freemMarkParams.packageNameStr};
<#--导包 -->
import java.util.Map;
import priv.guochun.psmc.system.framework.page.MyPage;
import ${freemMarkParams.importDominPackageStr};
/**
** 注意这是spring注入配置文件，请复制到注入配置文件后删除
**	<bean id="${freemMarkParams.serviceName ?uncap_first}" class="${freemMarkParams.packageNameStr}.impl.${freemMarkParams.serviceName}Impl" />
**
**/
<#if freemMarkParams.beanContent!="">
    /*
     *${freemMarkParams.beanContent}
     * Created by System
     */
</#if>
public interface ${freemMarkParams.serviceName}{
   /**
	 * 分页查询
	 * @param page 分页对象
	 * @return
	 */
    MyPage ${freemMarkParams.findByPageMethodName}(MyPage page);
     /**
	 * 根据主键查询
	 * @param uuid 主键
	 * @return
	 */
     Map<String,Object> ${freemMarkParams.findByPrimarKeyMethodName}(String uuid);
     /**
	 * 根据主键批量删除
	 * @param ids 主键列表
	 * @return
	 */
     void ${freemMarkParams.deleteByPrimarKeyMethodName}(String ids);
     /**
	 * 新增
	 * @param ${freemMarkParams.domainArgName} 实体类
	 * @return
	 */
     void ${freemMarkParams.addMethodName}(${freemMarkParams.domainName} ${freemMarkParams.domainArgName});
     /**
	 * 修改
	 * @param ${freemMarkParams.domainArgName} 实体类
	 * @return
	 */
      void ${freemMarkParams.updateMethodName}(${freemMarkParams.domainName}  ${freemMarkParams.domainArgName});
}
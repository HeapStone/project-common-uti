package ${freemMarkParams.packageNameStr}.impl;
<#--导包 -->
import java.util.Map;
import java.util.HashMap;
import priv.guochun.psmc.system.framework.page.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import priv.guochun.psmc.website.backstage.common.BaseDao;
import priv.guochun.psmc.system.util.UUIDGenerator;
import ${freemMarkParams.importDominPackageStr};
import ${freemMarkParams.importServicePackageStr}Service;
<#if freemMarkParams.beanContent!="">
    /*
     *${freemMarkParams.beanContent}
     * Created by System
     */
</#if>
public class ${freemMarkParams.serviceName}Impl implements ${freemMarkParams.serviceName}{
    public static final String   daoSelecListSqlID = "${freemMarkParams.daoSelecListSqlID}";
    public static final String   daoSelecPrimarySqlID= "${freemMarkParams.daoSelecPrimarySqlID}";
    public static final String   daoDeletePrimarySqlID = "${freemMarkParams.daoDeletePrimarySqlID}";
    public static final String   daoInsertSqlID = "${freemMarkParams.daoInsertSqlID}";
    public static final String   daoUpdateSqlID = "${freemMarkParams.daoUpdateSqlID}";
    @Autowired
    private BaseDao baseDao;
     @Override
     public MyPage ${freemMarkParams.findByPageMethodName}(MyPage myPage){
        Map<String,Object> condition = new HashMap<String,Object>();
        if(myPage.getQueryParams()!=null && myPage.getQueryParams().size()>0){
            condition.putAll(myPage.getQueryParams());
        }
        return baseDao.getMyPage(myPage, daoSelecListSqlID, condition);
      }
     @Override
     public Map<String,Object> ${freemMarkParams.findByPrimarKeyMethodName}(String uuid){
       Map<String,Object> condition = new HashMap<String,Object>();
        condition.put( "${freemMarkParams.tabkePkBeanName}", uuid);
        return (Map<String, Object>)baseDao.queryForObject(daoSelecPrimarySqlID,condition);
     }
      @Override
     public void ${freemMarkParams.deleteByPrimarKeyMethodName}(String ids){
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("ids", ids.split(","));
        baseDao.delete(daoDeletePrimarySqlID, condition);
     }
     @Override
     public void ${freemMarkParams.addMethodName}(${freemMarkParams.domainName} ${freemMarkParams.domainArgName} ){
        ${freemMarkParams.domainArgName}.set${freemMarkParams.tabkePkBeanName ?cap_first}(UUIDGenerator.createUUID());
        baseDao.insert(daoInsertSqlID, ${freemMarkParams.domainArgName});
     }
     @Override
     public void ${freemMarkParams.updateMethodName}(${freemMarkParams.domainName} ${freemMarkParams.domainArgName}){
       baseDao.update(daoUpdateSqlID,${freemMarkParams.domainArgName});
     }
}
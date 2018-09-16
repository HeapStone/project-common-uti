package ${Paramss.packageNameStr}.impl;
<#--导包 -->
import java.util.Map;
import priv.guochun.psmc.system.framework.page.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import priv.guochun.psmc.website.backstage.common.BaseDao;
import ${Paramss.importDominPackageStr};
import ${Paramss.importServicePackageStr}Service;
<#if Paramss.beanContent!="">
    /*
     *${Paramss.beanContent}
     * Created by System
     */
</#if>
public interface ${Paramss.serviceName}Impl implements ${Paramss.serviceName}{
    public static final String   daoSelecListSqlID = ${Paramss.daoSelecListSqlID};
    public static final String   daoSelecPrimarySqlID= ${Paramss.daoSelecPrimarySqlID};
    public static final String   daoDeletePrimarySqlID = ${Paramss.daoDeletePrimarySqlID};
    public static final String   daoInsertSqlID = ${Paramss.daoInsertSqlID};
    public static final String   daoUpdateSqlID = ${Paramss.daoUpdateSqlID};
    @Autowired
    private BaseDao baseDao;
     @Override
     public MyPage ${Paramss.findByPageMethodName}(MyPage page){
        Map<String,Object> condition = new HashMap<String,Object>();
        if(myPage.getQueryParams()!=null && myPage.getQueryParams().size()>0){
            condition.putAll(myPage.getQueryParams());
        }
        return baseDao.getMyPage(myPage, daoSelecListSqlID, condition);
      }
     @Override
     public Map<String,Object> ${Paramss.findByPrimarKeyMethodName}(String uuid){
       Map<String,Object> condition = new HashMap<String,Object>();
        condition.put( ${Paramss.tabkePkBeanName}, uuid);
        return (Map<String, Object>)baseDao.queryForObject(daoSelecPrimarySqlID,condition);
     }
      @Override
     public void ${Paramss.deleteByPrimarKeyMethodName}(String ids){
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("ids", ids.split(","));
        baseDao.delete(deleteByPrimaryKeys, condition);
     }
     @Override
     public void ${Paramss.addMethodName}(${Paramss.domainName} ${Paramss.domainArgName} ){
        baseDao.insert(daoInsertSqlID, ${Paramss.domainArgName});
     }
     @Override
     public void ${Paramss.updateMethodName}(${Paramss.domainName} ${Paramss.domainArgName}){
       baseDao.update(daoUpdateSqlID,${Paramss.domainArgName});
     }
}
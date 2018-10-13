package generator.tool.factory;

import generator.tool.codedata.*;
import generator.tool.codefile.AbstractCodeFile;
import generator.tool.constants.CommonConstants;
import generator.tool.model.config.BaseCodeConfig;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.CommonConfig;

import java.util.List;

/**
 * 根据架构获取生成文件的数据类型
 */
public class CodeFileDataFactory {

    /**
     * Default constructor
     */
    public CodeFileDataFactory() {
    }

    /**
     * 根据框架信息获取代码文件数据
     * @return 对应代码文件的数据
     */
    public AbstractCodeData getCodeFileData( String type) {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        CommonConfig tempCommonCfg = codeFileCfg.getCommonConfig();
        BaseCodeConfig controllerCfg = tempCommonCfg.getControllerCode();
        BaseCodeConfig daoDaoCodeCfg = tempCommonCfg.getDaoDaoCode();
        BaseCodeConfig domainCfg = tempCommonCfg.getDaominCode();
        BaseCodeConfig serviceCfg = tempCommonCfg.getServiceCode();
        BaseCodeConfig viewCfg = tempCommonCfg.getViewCode();
        BaseCodeConfig mapperCfg = tempCommonCfg.getMapperFile();
        //mybatis实体
        if(CommonConstants.CODE_TYPE_MYBATIS.equals(domainCfg.getCodeType())&&type.equals(CommonConstants.DOMIAN)){
            return  new MybatisDomainData();
        }
        //hibernate实体
        if(CommonConstants.CODE_TYPE_HIBERNATE.equals(domainCfg.getCodeType())&&type.equals(CommonConstants.DOMIAN)){
            return  new HibernateDomainData();
        }
        //mybatis配置文件
        if(CommonConstants.CODE_TYPE_MYBATIS.equals(mapperCfg.getCodeType())&&type.equals(CommonConstants.MAPPER)){
           return  new MybatisMapperData();
        }
        //hibernate配置文件
        if(CommonConstants.CODE_TYPE_HIBERNATE.equals(mapperCfg.getCodeType())&&type.equals(CommonConstants.MAPPER)){
            return  new HibernateMapperData();
        }
        //mybatisDao
        if(CommonConstants.CODE_TYPE_MYBATIS.equals(daoDaoCodeCfg.getCodeType())&&type.equals(CommonConstants.DAO)){
            return  new MybatisDaoCodeData();
        }
        //hibernateDao
        if(CommonConstants.CODE_TYPE_HIBERNATE.equals(daoDaoCodeCfg.getCodeType())&&type.equals(CommonConstants.DAO)){
            return  new HibernateDaoCodeData();
        }

        //Service
        if(CommonConstants.CODE_TYPE_SERVICE.equals(serviceCfg.getCodeType())&&type.equals(CommonConstants.SERVICE)){
            return  new SerivceCodeData();
        }
        //SpringMVC Controller
        if(CommonConstants.CODE_TYPE_SPRINMVC.equals(controllerCfg.getCodeType())&&type.equals(CommonConstants.CONTROLLER)){
            return  new SpringMvcControllerData();
        }
        //Struts2 控制器
        if(CommonConstants.CODE_TYPE_STRUTS_2.equals(controllerCfg.getCodeType())&&type.equals(CommonConstants.CONTROLLER)){
           return  new Structs2ControllerData();
        }
        //JSP 页面
        if(CommonConstants.CODE_TYPE_JSP.equals(viewCfg.getCodeType())&&type.equals(CommonConstants.VIEW)){
            return  new JspData();
        }
        //HTML页面
        if(CommonConstants.CODE_TYPE_HTML.equals(viewCfg.getCodeType())&&type.equals(CommonConstants.VIEW)){
            return  new HTMLData();
        }

     return null;
    }


}
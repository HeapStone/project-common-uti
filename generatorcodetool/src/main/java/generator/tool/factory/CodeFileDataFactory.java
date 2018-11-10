package generator.tool.factory;

import generator.tool.codedata.*;
import generator.tool.constants.CommonConstants;
import generator.tool.model.config.BaseCodeConfig;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.CommonConfig;

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
        try {
            //实体类文件
            if (type.equals(CommonConstants.DOMIAN)) {
                Class classObj =  Class.forName(domainCfg.getCodeDataClassName());
                return (AbstractCodeData)classObj.newInstance();
            }
            //映射配置文件
            if (type.equals(CommonConstants.MAPPER)) {
                Class classObj =  Class.forName(mapperCfg.getCodeDataClassName());
                return (AbstractCodeData)classObj.newInstance();
            }
            //Dao文件
            if (type.equals(CommonConstants.DAO)) {
                Class classObj =  Class.forName(daoDaoCodeCfg.getCodeDataClassName());
                return (AbstractCodeData)classObj.newInstance();
            }

            //Service文件
            if (type.equals(CommonConstants.SERVICE)) {
                Class classObj =  Class.forName(serviceCfg.getCodeDataClassName());
                return (AbstractCodeData)classObj.newInstance();
            }
            //Controller文件
            if (type.equals(CommonConstants.CONTROLLER)) {
                Class classObj =  Class.forName(controllerCfg.getCodeDataClassName());
                return (AbstractCodeData)classObj.newInstance();
            }
            //HTML页面
            if (type.equals(CommonConstants.VIEW)) {
                Class classObj =  Class.forName(viewCfg.getCodeDataClassName());
                return (AbstractCodeData)classObj.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     return null;
    }


}
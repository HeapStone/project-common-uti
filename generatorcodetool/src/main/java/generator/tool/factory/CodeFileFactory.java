package generator.tool.factory;

import generator.tool.codefile.*;
import generator.tool.constants.CommonConstants;
import generator.tool.model.config.BaseCodeConfig;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.CommonConfig;
import generator.tool.model.config.ServiceCodeConfig;

import java.util.List;

/**
 * 根据配置文件类型获取要生成文件的类型
 */
public class CodeFileFactory {

    /**
     * Default constructor
     */
    public CodeFileFactory() {
    }

    /**
     * 根据配置信息获取要生成的文件对象
     * @return 要生成的代码工具对象
     */
    public void getCodeFile( List<AbstractCodeFile> abstractCodeFiles) {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        CommonConfig tempCommonCfg = codeFileCfg.getCommonConfig();
        AbstractCodeFile abstractCodeFile = null;
        BaseCodeConfig controllerCfg = tempCommonCfg.getControllerCode();
        BaseCodeConfig daoDaoCodeCfg = tempCommonCfg.getDaoDaoCode();
        BaseCodeConfig domainCfg = tempCommonCfg.getDaominCode();
        BaseCodeConfig serviceCfg = tempCommonCfg.getServiceCode();
        BaseCodeConfig viewCfg = tempCommonCfg.getViewCode();
        BaseCodeConfig mapperCfg = tempCommonCfg.getMapperFile();
        //生成controller 文件
       if(domainCfg.getIsGenerator()){
            //生成实体类
            abstractCodeFile = new DoaminCodeFile(tempCommonCfg.getProjectPath(),
                    domainCfg.getFileSuffx(),domainCfg.getFileName(),
                    domainCfg.getFileTemplateName(),domainCfg.getPakageName(),
                    domainCfg.getFileName());
            abstractCodeFiles.add(abstractCodeFile);
        }
        if(mapperCfg.getIsGenerator()){
            //生成配置文件
            abstractCodeFile = new OrmRelationMapperCodeFile(tempCommonCfg.getProjectPath()+mapperCfg.getFilePath(),
                    mapperCfg.getFileSuffx(),mapperCfg.getFileName(),
                    mapperCfg.getFileTemplateName(),mapperCfg.getPakageName(),
                    mapperCfg.getFileName());
            abstractCodeFiles.add(abstractCodeFile);
        }
        if(daoDaoCodeCfg.getIsGenerator()){
            //生成dao文件
            abstractCodeFile = new DaoCodeFile(tempCommonCfg.getProjectPath(),
                    daoDaoCodeCfg.getFileSuffx(),daoDaoCodeCfg.getFileName(),
                    daoDaoCodeCfg.getFileTemplateName(),daoDaoCodeCfg.getPakageName(),
                    daoDaoCodeCfg.getFileName());
            abstractCodeFiles.add(abstractCodeFile);
        }
        if(serviceCfg.getIsGenerator()){
            //生成service
            abstractCodeFile = new ServiceCodefFile(tempCommonCfg.getProjectPath(),
                    serviceCfg.getFileSuffx(),serviceCfg.getFileName(),
                    serviceCfg.getFileTemplateName(),serviceCfg.getPakageName(),
                    serviceCfg.getFileName(), ((ServiceCodeConfig) serviceCfg).getFileImplTemplateName());
            abstractCodeFiles.add(abstractCodeFile);
        }
        if(controllerCfg.getIsGenerator()){
            abstractCodeFile = new ControllerCodeFile(tempCommonCfg.getProjectPath(),
                    controllerCfg.getFileSuffx(),controllerCfg.getFileName(),
                    controllerCfg.getFileTemplateName(),controllerCfg.getPakageName(),
                    controllerCfg.getFileName());
            abstractCodeFiles.add(abstractCodeFile);
        }
        if(viewCfg.getIsGenerator()){
            //生成页面
            abstractCodeFile = new ViewCodeFile(tempCommonCfg.getProjectPath(),
                    viewCfg.getFileSuffx(),viewCfg.getFileName(),
                    viewCfg.getFileTemplateName(),viewCfg.getPakageName(),
                    viewCfg.getFileName());
            abstractCodeFiles.add(abstractCodeFile);
        }
    }

}
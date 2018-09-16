package generator.tool.codefile;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.CodeFileDataFactory;
import generator.tool.factory.SystemContext;
import generator.tool.model.AbstractMapperConfig;
import generator.tool.model.BeanModel;
import generator.tool.model.MybatisMapperConfig;
import generator.tool.model.TableBean;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.CommonConfig;
import generator.tool.util.FreemarkUtil;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 映射文件代码工具
 */
public class OrmRelationMapperCodeFile extends AbstractCodeFile {
     List<AbstractMapperConfig> mybatisMapperConfigs= new ArrayList<>();
    /**
     * Default constructor
     */
    public OrmRelationMapperCodeFile(String codeFilePath,
                                     String codeFileSuffx, String codeFileName,
                                     String codeTemplateFileName, String packageName, String fileName) {
        super(codeFilePath, codeFileSuffx, codeFileName,codeTemplateFileName,packageName,fileName);
        this.codeFilePath = codeFilePath;
        this.codeFileSuffx = codeFileSuffx;
        this.codeFileName = codeFileName;
        this.codeTemplateFileName = codeTemplateFileName;
        this.packageName = packageName;
        this.fileName = fileName;
        inIntCodeFileData();
    }

    @Override
    public void generatorCodeFile() {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        CommonConfig tempCommonCfg = codeFileCfg.getCommonConfig();
        for(AbstractMapperConfig mybatisMapperConfig:mybatisMapperConfigs){
            //代码最终路径
            String outPath = this.codeFilePath;
            File destFile = new File(outPath);
            boolean  pathHave  = false;
            if (!destFile.exists()) {
                pathHave = destFile.mkdirs();
            }else{
                pathHave = true;
            }
            if(pathHave){
                String templatePath = tempCommonCfg.getTemplatepath();
                FreemarkUtil ftlu = FreemarkUtil.getInstance(CommonConstants.FREEMARK_VERSION,templatePath);
                Map<String,Object> dataModel = new HashMap<>();
                dataModel.put("mapperConfig", mybatisMapperConfig);

                ftlu.fprintTemplate(dataModel,  this.codeTemplateFileName , outPath,mybatisMapperConfig.getFileName());
            }
        }
    }

    @Override
    public void inIntCodeFileData() {
        CodeFileDataFactory codeFileDataFactory = new CodeFileDataFactory();
        this.abstractCodeDatas =codeFileDataFactory.getCodeFileData(CommonConstants.MAPPER);
        this.mybatisMapperConfigs = (List<AbstractMapperConfig>)abstractCodeDatas.inIntCodeFileData((List<TableBean>)SystemContext.get(CommonConstants.TABLE_BEANS));
    }

}
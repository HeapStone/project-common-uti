package generator.tool.codefile;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.CodeFileDataFactory;
import generator.tool.factory.SystemContext;
import generator.tool.model.BeanModel;
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
 * 实体代码生成工具
 */
public class DoaminCodeFile extends AbstractCodeFile {
  private  List<BeanModel> beanModels = new ArrayList<>();
    /**
     * Default constructor
     */
    public DoaminCodeFile(String codeFilePath,
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
        for(BeanModel beanModel:beanModels){
            String outPath = this.codeFilePath;
            if(StringUtils.isBlank(outPath)){throw new RuntimeException("生成路径不存在，不能生成实体类"); }
            if(StringUtils.isBlank(beanModel.getPackageNameStr())){throw new RuntimeException("指定包名为空不能生成实体类"); }
            //将包名转换成文件路径
            String packageFilePath = beanModel.getPackageNameStr().replace(".", "\\");
            //代码最终路径
            outPath = outPath + packageFilePath +"\\";
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
                dataModel.put("Paramss", beanModel);
                ftlu.fprintTemplate(dataModel,  this.codeTemplateFileName , outPath, beanModel.getBeanName()+this.codeFileSuffx);
        }
        }
    }

    @Override
    public void inIntCodeFileData() {
        CodeFileDataFactory codeFileDataFactory = new CodeFileDataFactory();
        this.abstractCodeDatas =codeFileDataFactory.getCodeFileData(CommonConstants.DOMIAN);
        this.beanModels = (List<BeanModel>)abstractCodeDatas.inIntCodeFileData((List<TableBean>)SystemContext.get(CommonConstants.TABLE_BEANS));
    }


}
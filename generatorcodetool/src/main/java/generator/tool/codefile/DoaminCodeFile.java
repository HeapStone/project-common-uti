package generator.tool.codefile;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.CodeFileDataFactory;
import generator.tool.factory.SystemContext;
import generator.tool.model.codedata.AbstractCodeDataModel;
import generator.tool.model.codedata.DomainCodeDataModel;
import generator.tool.model.TableBean;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.CommonConfig;
import generator.tool.util.CommonUtil;
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
  private  List<AbstractCodeDataModel> beanModels = new ArrayList<>();
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
       CommonUtil. generatorCodeFile(beanModels,codeFilePath,codeFileSuffx,codeTemplateFileName,false,null);
    }

    @Override
    public void inIntCodeFileData() {
        CodeFileDataFactory codeFileDataFactory = new CodeFileDataFactory();
        this.abstractCodeDatas =codeFileDataFactory.getCodeFileData(CommonConstants.DOMIAN);
        this.beanModels = (List<AbstractCodeDataModel>)abstractCodeDatas.inIntCodeFileData((List<TableBean>)SystemContext.get(CommonConstants.TABLE_BEANS));
    }


}
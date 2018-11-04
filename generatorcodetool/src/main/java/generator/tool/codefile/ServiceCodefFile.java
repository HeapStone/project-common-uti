package generator.tool.codefile;

import generator.tool.codedata.AbstractCodeData;
import generator.tool.constants.CommonConstants;
import generator.tool.factory.CodeFileDataFactory;
import generator.tool.factory.SystemContext;
import generator.tool.model.codedata.AbstractCodeDataModel;
import generator.tool.model.codedata.ServiceCodeDataModel;
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
 * 业务层代码工具对象
 */
public class ServiceCodefFile extends AbstractCodeFile{
    List<AbstractCodeDataModel> abstractCodeDataModels =new ArrayList<>();
    private String fileImplTemplateName;

    public String getFileImplTemplateName() {
        return fileImplTemplateName;
    }

    public void setFileImplTemplateName(String fileImplTemplateName) {
        this.fileImplTemplateName = fileImplTemplateName;
    }
    /**
     * Default constructor
     */
    public ServiceCodefFile(String codeFilePath,
                            String codeFileSuffx, String codeFileName,
                            String codeTemplateFileName, String packageName, String fileName,String fileImplTemplateName) {
        super(codeFilePath, codeFileSuffx, codeFileName,codeTemplateFileName,packageName,fileName);
        this.codeFilePath = codeFilePath;
        this.codeFileSuffx = codeFileSuffx;
        this.codeFileName = codeFileName;
        this.codeTemplateFileName = codeTemplateFileName;
        this.packageName = packageName;
        this.fileName = fileName;
        this.fileImplTemplateName = fileImplTemplateName;
        inIntCodeFileData();
    }

    @Override
    public void generatorCodeFile() {
        CommonUtil. generatorCodeFile(abstractCodeDataModels,codeFilePath,codeFileSuffx,codeTemplateFileName,true,fileImplTemplateName);
    }

    @Override
    public void inIntCodeFileData() {
        CodeFileDataFactory codeFileDataFactory = new CodeFileDataFactory();
        this.abstractCodeDatas =codeFileDataFactory.getCodeFileData(CommonConstants.SERVICE);
        this.abstractCodeDataModels = (List<AbstractCodeDataModel>)abstractCodeDatas.inIntCodeFileData((List<TableBean>)SystemContext.get(CommonConstants.TABLE_BEANS));
    }


}
package generator.tool.codefile;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.CodeFileDataFactory;
import generator.tool.factory.SystemContext;
import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;
import generator.tool.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制层代码生成工具
 */
public class ControllerCodeFile extends AbstractCodeFile {
    List<AbstractCodeDataModel> abstractCodeDataModels =new ArrayList<>();
    public ControllerCodeFile(String codeFilePath,
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
    private String fileImplTemplateName;

    public String getFileImplTemplateName() {
        return fileImplTemplateName;
    }

    public void setFileImplTemplateName(String fileImplTemplateName) {
        this.fileImplTemplateName = fileImplTemplateName;
    }
    /**
     * 默认生成代码文件的方法
     */
    public  void generatorCodeFile(){
        CommonUtil. generatorCodeFile(abstractCodeDataModels,codeFilePath,codeFileSuffx,codeTemplateFileName,false,null);
    }

    @Override
    public void inIntCodeFileData() {
        CodeFileDataFactory codeFileDataFactory = new CodeFileDataFactory();
        this.abstractCodeDatas =codeFileDataFactory.getCodeFileData(CommonConstants.CONTROLLER);
        this.abstractCodeDataModels = (List<AbstractCodeDataModel>)abstractCodeDatas.inIntCodeFileData((List<TableBean>)SystemContext.get(CommonConstants.TABLE_BEANS));
    }

}
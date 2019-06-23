package generator.tool.codefile;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.CodeFileDataFactory;
import generator.tool.factory.SystemContext;
import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;
import generator.tool.util.CommonUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制层代码生成工具
 */
@Data
public class ControllerCodeFile extends AbstractCodeFile {
    List<AbstractCodeDataModel> abstractCodeDataModels =new ArrayList<>();
    private String fileImplTemplateName;
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

    /**
     * 默认生成代码文件的方法
     */
    public  void generatorCodeFile(){
        CommonUtil. generatorCodeFile(abstractCodeDataModels,codeFilePath,codeFileSuffx,codeTemplateFileName,false,null);
    }

    @Override
    public void inIntCodeFileData() {
        CodeFileDataFactory codeFileDataFactory = new CodeFileDataFactory();
        this.abstractCodeDatas = codeFileDataFactory.getCodeFileData(CommonConstants.CONTROLLER);
        this.abstractCodeDataModels = abstractCodeDatas.inIntCodeFileData((List<TableBean>)SystemContext.get(CommonConstants.TABLE_BEANS));
    }

}
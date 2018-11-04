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
 * 映射文件代码工具
 */
public class OrmRelationMapperCodeFile extends AbstractCodeFile{
     List<AbstractCodeDataModel> abstractCodeDataModels= new ArrayList<>();
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
        CommonUtil. generatorCodeFile(abstractCodeDataModels,codeFilePath,codeFileSuffx,codeTemplateFileName,false,null);
    }

    @Override
    public void inIntCodeFileData() {
        CodeFileDataFactory codeFileDataFactory = new CodeFileDataFactory();
        this.abstractCodeDatas =codeFileDataFactory.getCodeFileData(CommonConstants.MAPPER);
        this.abstractCodeDataModels = (List<AbstractCodeDataModel>)abstractCodeDatas.inIntCodeFileData((List<TableBean>)SystemContext.get(CommonConstants.TABLE_BEANS));
    }

}
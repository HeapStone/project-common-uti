package generator.tool.codefile;

import generator.tool.codedata.SerivceCodeData;
import generator.tool.constants.CommonConstants;
import generator.tool.factory.CodeFileDataFactory;
import generator.tool.factory.SystemContext;
import generator.tool.model.ServiceCodeDataModel;
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
 * 业务层代码工具对象
 */
public class ServiceCodefFile extends AbstractCodeFile {
    List<ServiceCodeDataModel> serviceCodeDataModels =new ArrayList<>();
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
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        CommonConfig tempCommonCfg = codeFileCfg.getCommonConfig();
        for(ServiceCodeDataModel serviceCodeDataModel:serviceCodeDataModels){
            String outPath = this.codeFilePath;
            if(StringUtils.isBlank(outPath)){throw new RuntimeException("生成路径不存在，不能生成实体类"); }
            if(StringUtils.isBlank(serviceCodeDataModel.getPackageNameStr())){throw new RuntimeException("指定包名为空不能生成service"); }
            //将包名转换成文件路径
            String packageFilePath = serviceCodeDataModel.getPackageNameStr().replace(".", "\\");
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
                dataModel.put("Paramss", serviceCodeDataModel);
                //生成service
                ftlu.fprintTemplate(dataModel,  this.codeTemplateFileName , outPath, serviceCodeDataModel.getServiceName()+this.codeFileSuffx);
               //生成实现类
                outPath = outPath + CommonConstants.packageImplName;
                File destFileimpl = new File(outPath);
                if (!destFileimpl.exists()) {
                    destFileimpl.mkdirs();
                }
                ftlu.fprintTemplate(dataModel,  fileImplTemplateName , outPath, serviceCodeDataModel.getServiceName()+"Impl"+this.codeFileSuffx);
            }
        }
    }

    @Override
    public void inIntCodeFileData() {
        CodeFileDataFactory codeFileDataFactory = new CodeFileDataFactory();
        this.abstractCodeDatas =codeFileDataFactory.getCodeFileData(CommonConstants.SERVICE);
        this.serviceCodeDataModels = (List<ServiceCodeDataModel>)abstractCodeDatas.inIntCodeFileData((List<TableBean>)SystemContext.get(CommonConstants.TABLE_BEANS));
    }


}
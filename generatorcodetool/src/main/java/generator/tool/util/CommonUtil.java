package generator.tool.util;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.codedata.AbstractCodeDataModel;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.CommonConfig;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/11/4
 */
public class CommonUtil {
    public static void generatorCodeFile(List<AbstractCodeDataModel> codeDataModels,String codeFilePath ,
                                         String codeFileSuffx,String codeTemplateFileName,boolean withImpl,String fileImplTemplateName){
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        CommonConfig tempCommonCfg = codeFileCfg.getCommonConfig();
        for(AbstractCodeDataModel codeDataModel:codeDataModels){
            String outPath = codeFilePath;
            if(StringUtils.isBlank(outPath)){throw new RuntimeException("生成路径不存在，不能生成实文件"); }

            if(StringUtils.isNotBlank((codeDataModel.getPackageNameStr()))){
                //将包名转换成文件路径
                String packageFilePath = codeDataModel.getPackageNameStr().replace(".", "\\");
                //代码最终路径
                outPath = outPath + packageFilePath +"\\";
            }
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
                dataModel.put(CommonConstants.freemMarkParams, codeDataModel);
                ftlu.fprintTemplate(dataModel,codeTemplateFileName , outPath, codeDataModel.getFileName()+codeFileSuffx);
                if(withImpl&&StringUtils.isNotEmpty(fileImplTemplateName)){
                    //生成实现类
                    outPath = outPath + CommonConstants.packageImplName;
                    File destFileimpl = new File(outPath);
                    if (!destFileimpl.exists()) {
                        destFileimpl.mkdirs();
                    }
                    ftlu.fprintTemplate(dataModel,  fileImplTemplateName , outPath, codeDataModel.getFileName()+"Impl"+codeFileSuffx);
                }
            }
        }
    }

}

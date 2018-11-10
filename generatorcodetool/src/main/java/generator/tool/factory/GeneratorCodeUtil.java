package generator.tool.factory;

import generator.tool.codefile.AbstractCodeFile;
import generator.tool.constants.CommonConstants;
import generator.tool.model.ProjectCodePropertiesModel;
import generator.tool.model.TableBean;
import generator.tool.model.config.*;
import generator.tool.util.*;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 生成代码工具
 * 主要功能：
 * 1、系统初始化
 * 2、获取要生成代码文件的类型
 * 3、根据代码对象信息生成代码
 */
public class GeneratorCodeUtil {
    private final static Logger logger = LoggerFactory.getLogger(GeneratorCodeUtil.class.getName());

    /**
     * Default constructor
     */
    public GeneratorCodeUtil() {
        inIntCofing();
    }

    /**
     * 代码生成工具集合
     */
    private List<AbstractCodeFile> abstractCodeFiles;

    /**
     * 初始系统
     */
    private void inIntCofing() {
        logger.info("------------------------系统初始化-----------------------------");
        //获取配置信息
        Document document = ReadXmlUtils.getXMLByFilePath(CommonConstants.CODE_FILE_CONFIG_PATH);
        String configStr = XmlConverUtil.xmltoJson(ReadXmlUtils.documentToString(document, CommonConstants.CHAR_SET)).replace("@", "");
        CodeFileCfg codeFileCfg = (CodeFileCfg) JsonHelper.toJavaBean(CodeFileCfg.class, configStr);
        checkCodeFileConfig(codeFileCfg);
        //将配置信息存储
        logger.info("------------------------获取配置信息信息-----------------------------");
        SystemContext.set(CommonConstants.CODE_FILE_CONFIG, codeFileCfg);
        //初始化数据库表信息
        TableUtil tableUtil = TableUtil.getTableBeanUtilInstance();
        SystemContext.set(CommonConstants.TABLE_BEANS, tableUtil.tableBeans);
        //把要生成代码的表名放到缓存里
        getTableBenasByConfig();
        //初始化生成项目公共属性 key:表名，value 其他属性
        Map<String, ProjectCodePropertiesModel> projectCodePropertiesModels = new HashMap<>();
        SystemContext.set(CommonConstants.PROJECT_CODE_PROPERTIES, projectCodePropertiesModels);
        logger.info("------------------------系统初始化结束-----------------------------");

    }

    /**
     * 生成代码文件
     */
    public void genterCodeFile() {
        this.setCodefiles();

        for (AbstractCodeFile abstractCodeFile : abstractCodeFiles) {
            abstractCodeFile.generatorCodeFile();
        }
    }

    /**
     * 设置要生成的diam文件
     */
    private void setCodefiles() {
        CodeFileFactory codeFileFactory = new CodeFileFactory();
        abstractCodeFiles = new ArrayList<>();
        codeFileFactory.getCodeFile(abstractCodeFiles);
    }

    /**
     * 校验配置信息
     *
     * @param codeFileCfg
     */
    private void checkCodeFileConfig(CodeFileCfg codeFileCfg) {
        //配置信息不能为空
        if (null == codeFileCfg) {
            logger.error("配置文件加载错误，找不到对应的配置信息！");
            throw new RuntimeException("配置文件加载错误，找不到对应的配置文件！");
        }
        //公共配置不能为空
        CommonConfig temCommonConfig = codeFileCfg.getCommonConfig();
        if (null == temCommonConfig) {
            logger.error("配置信息错误，未找到公共配置信息！");
            throw new RuntimeException("配置信息错误，未找到公共配置信息！");
        }
        //公共配置下的其他信息是否为空
        Map<String, Object> comCfgMap = ReflectUtils.getBeanProperty(temCommonConfig);
        //检查每个文件的配置信息是否合法
        checkProperty(comCfgMap,"commonConfig");
    }

    /**
     * 校验公共配置的每一个配置是否正确
     *
     * @param comCfgMap
     */
    private void checkProperty(Map<String, Object> comCfgMap,String cfgName) {
        if (null == comCfgMap || comCfgMap.isEmpty()) {
            logger.error("配置信息错误，未找到公共配置信息["+cfgName+"]！");
            throw new RuntimeException("配置信息错误，未找到公共配置信息["+cfgName+"]！");
        }
        Set<String> keys = comCfgMap.keySet();
        for (String key : keys) {
            if (key.equals("filePath")&&!cfgName.equals("viewCode")){
                continue;
            }
            if (key.equals("pakageName")&&(cfgName.equals("viewCode")||cfgName.equals("mapperFileConfig"))){
                continue;
            }
            if (key.equals("fileName")){
                continue;
            }
            Object tempObj = comCfgMap.get(key);
            if (tempObj instanceof String) {
                if (StringUtils.isBlank(tempObj.toString())) {
                    logger.error("配置信息错误，未找到配置信息["+cfgName+"]的["+key+"]属性!");
                    throw new RuntimeException("配置信息错误，未找到配置信息["+cfgName+"]的[" + key + "]属性！");
                }
            } else if (null == tempObj) {
                logger.error("配置信息错误，未找到公共配置信息["+cfgName+"]的[" + key + "]属性！");
                throw new RuntimeException("配置信息错误，未找到配置信息["+cfgName+"]的[" + key + "]属性！");
            } else if (tempObj instanceof DomainCodeConfig){
                //公共配置下的其他信息是否为空
                Map<String, Object> tempcfg = ReflectUtils.getBeanPropertyByClassName(tempObj, BaseCodeConfig.class);
                //检查每个文件的配置信息是否合法
                checkProperty(tempcfg,"daominCode");
            }
            else if (tempObj instanceof MapperFileConfig ){
                //公共配置下的其他信息是否为空
                Map<String, Object> tempcfg = ReflectUtils.getBeanPropertyByClassName(tempObj, BaseCodeConfig.class);
                //检查每个文件的配置信息是否合法
                checkProperty(tempcfg,"mapperFileConfig");
            }
            else if(tempObj instanceof ControllerCodeConfig){
                //公共配置下的其他信息是否为空
                Map<String, Object> tempcfg = ReflectUtils.getBeanPropertyByClassName(tempObj, BaseCodeConfig.class);
                //检查每个文件的配置信息是否合法
                checkProperty(tempcfg,"controllerCode");
            }
            else if(tempObj instanceof ViewCodeConfig){
                //公共配置下的其他信息是否为空
                Map<String, Object> tempcfg = ReflectUtils.getBeanPropertyByClassName(tempObj, BaseCodeConfig.class);
                //检查每个文件的配置信息是否合法
                checkProperty(tempcfg,"viewCode");
            }
            else if(tempObj instanceof ServiceCodeConfig){
                //公共配置下的其他信息是否为空
                Map<String, Object> tempcfg = ReflectUtils.getBeanPropertyByClassName(tempObj, BaseCodeConfig.class);
                //检查每个文件的配置信息是否合法
                checkProperty(tempcfg,"serviceCode");
            }
            else if(tempObj instanceof DaoCodeConfig){
                //公共配置下的其他信息是否为空
                Map<String, Object> tempcfg = ReflectUtils.getBeanPropertyByClassName(tempObj, BaseCodeConfig.class);
                //检查每个文件的配置信息是否合法
                checkProperty(tempcfg,"daoDaoCode");
            }
            }
    }

    /**
     * 根据配置初始化表名
     */
    private void getTableBenasByConfig() {
        CodeFileCfg codeFileCfg = SystemContext.get(CommonConstants.CODE_FILE_CONFIG, CodeFileCfg.class);
        List<TableBean> tableBeans = (List<TableBean>) SystemContext.get(CommonConstants.TABLE_BEANS);
        //获取单表配置信息
        List<TableConfig> tableConfigs = codeFileCfg.getTables();
        //判断一下如果单表配置信息为空则为所有表生成代码，否则只生成配置表
        if (tableConfigs != null) {
            //选出要生成代码的表
            List<TableBean> tableBeansAll = new ArrayList<>();
            List<TableBean> tableBeansTemp = new ArrayList<>();
            tableBeansTemp.addAll(tableBeans);
            for (TableConfig taleCof : tableConfigs) {
                tableBeans = tableBeansTemp.stream().filter(table -> table.getTableName().equals(taleCof.getTableName())).collect(Collectors.toList());
                tableBeansAll.addAll(tableBeans);
            }
            //把根据配置要生成的表放到缓存中
            SystemContext.set(CommonConstants.TABLE_BEANS, tableBeansAll);
        }
    }
}
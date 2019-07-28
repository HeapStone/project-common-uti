package generator.tool.constants;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: 系统常量类 </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2018/7/29
 */
public class CommonConstants {
    //数据库所有表信息键
    public static  final String  TABLE_BEANS ="TABLE_BEANS";
    //配置文件路径
    public static final String CODE_FILE_CONFIG_PATH="/codeFile-cfg.xml";
    //文件编码
    public static final String CHAR_SET="UTF-8";
    //配置信息key
    public static  final String  CODE_FILE_CONFIG ="CODE_FILE_CONFIG";
    public static  final String  CODE_TYPE_MYBATIS ="mybatis";
    public static  final String  CODE_TYPE_HIBERNATE ="hibernate";
    public static  final String  CODE_TYPE_SPRINMVC ="springMvc";
    public static  final String  CODE_TYPE_STRUTS_2="struts2";
    public static  final String  CODE_TYPE_JSP ="jsp";
    public static  final String  CODE_TYPE_HTML ="HTML";
    public static  final String  CODE_TYPE_SERVICE ="service";
    public static  final String  DOMIAN ="DOMIAN";
    public static  final String  SERVICE ="SERVICE";
    public static  final String  VIEW ="VIEW";
    public static  final String  CONTROLLER ="CONTROLLER";
    public static  final String  DAO ="DAO";
    public static  final String  MAPPER ="MAPPER";
    public static final String FREEMARK_VERSION = "2.3.23";
    public static final String BASE_RESULT_MAP_IDPRE = "BaseResultMap";
    public static final String SELECT_PRE = "selectBy";
    public static final String SELECT_LIST_ED= "List";
    public static final String PRIMARYKEY = "PrimaryKey";
    public static final String SELECT_PRI_SUFIX = "selectBy";
    public static final String INSER_SQL = "insert";
    public static final String UPDATE_SQL = "update";
    public static final String DELETE_SQL = "deleteBy";
    public static final String PROJECT_CODE_PROPERTIES = "projectCodePropertiesModels";
    //实体类相关属性
    public static final String  modelBeanPorperties = "modelBeanPorperties";
    //控制类属性
    public static final String  controllerPorperties = "controllerPorperties";
    //服务类属性
    public static final String  servicePorperties = "servicePorperties";
    // dao属性
    public static final String  daoPorperties = "daoPorperties";
    //映射类属性
    public static final String  mapperPorperties = "mapperPorperties";
    //视图页面属性
    public static final String   viewPorperties = "viewPorperties";
    public static final String   beanName = "beanName";
    public static final String   beanNamePackageStr = "beanNamePackageStr";
    public static final String   beanProperties = "beanProperties";
    public static final String   daoSelecListSqlID = "daoSelecListSqlID";
    public static final String   daoSelecPrimarySqlID= "daoSelecPrimarySqlID";
    public static final String   daoDeletePrimarySqlID = "daoDeletePrimarySqlID";
    public static final String   daoInsertSqlID = "daoInsertSqlID";
    public static final String   daoUpdateSqlID = "daoUpdateSqlID";
    public static final String   tablePkBenanName = "tablePkBenanName";
    public static final String packageImplName = "impl\\";
    public static final String serviceName = "serviceName";
    public static final String addMethodName = "addMethodName";
    public static final String deleteMethodName = "deleteMethodName";
    public static final String updateMethodName = "updateMethodNametableColumn";
    public static final String findPrimarkeyMethodName = "findPrimarkeyMethodName";
    public static final String findPageMethodName = "findPageMethodName";
    public static final String servicePackageName = "servicePackageName";
    //系统标题
    public static final String mainFreamTitle = "代码生成工具V1.0";
    //系统页面大小
    public static final int mainFreamWidth = 1200;
    //页面高度
    public static final int mainFreamHight = 400;
    //代码配置路径
    public static final String cofigLabel = "配置文件路径：";
    //代码配置路径
    public static final String generatorBtn = "生成代码";

    //代码配置路径
    public static final String choseBtn = "选择配置文件";





}

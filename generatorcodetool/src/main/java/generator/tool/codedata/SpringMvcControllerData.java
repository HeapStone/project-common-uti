package generator.tool.codedata;

import generator.tool.constants.CommonConstants;
import generator.tool.factory.SystemContext;
import generator.tool.model.codedata.CotrollerCodeDataModel;
import generator.tool.model.ProjectCodePropertiesModel;
import generator.tool.model.TableBean;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.ControllerCodeConfig;
import generator.tool.util.ColumnToPropertyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SpringMvc控制层数据
 */
public class SpringMvcControllerData implements AbstractCodeData<List<CotrollerCodeDataModel> > {
    List<CotrollerCodeDataModel> cotrollerCodeDatas =new ArrayList<>();
    /**
     * Default constructor
     */
    public SpringMvcControllerData() {
    }
    @Override
    public List<CotrollerCodeDataModel>  inIntCodeFileData(List<TableBean> tableBeans) {
        for(TableBean tableBean: tableBeans){
            String tableName = tableBean.getTableName();
            CotrollerCodeDataModel cotrollerCodeData = new CotrollerCodeDataModel(tableName);
            cotrollerCodeDatas.add(cotrollerCodeData);
        }
        return cotrollerCodeDatas;
    }
}
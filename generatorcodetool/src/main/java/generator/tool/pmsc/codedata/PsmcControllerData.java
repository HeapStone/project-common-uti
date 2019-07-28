package generator.tool.pmsc.codedata;

import generator.tool.codedata.AbstractCodeData;
import generator.tool.pmsc.mode.PsmcCotrollerCodeDataModel;
import generator.tool.model.TableBean;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringMvc控制层数据
 */
public class PsmcControllerData implements AbstractCodeData<List<PsmcCotrollerCodeDataModel> > {
    List<PsmcCotrollerCodeDataModel> cotrollerCodeDatas =new ArrayList<>();
    /**
     * Default constructor
     */
    public PsmcControllerData() {
    }
    @Override
    public List<PsmcCotrollerCodeDataModel>  inIntCodeFileData(List<TableBean> tableBeans) {
        for(TableBean tableBean: tableBeans){
            String tableName = tableBean.getTableName();
            PsmcCotrollerCodeDataModel cotrollerCodeData = new PsmcCotrollerCodeDataModel(tableName);
            cotrollerCodeDatas.add(cotrollerCodeData);
        }
        return cotrollerCodeDatas;
    }
}
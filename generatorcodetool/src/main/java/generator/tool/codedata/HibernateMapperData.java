package generator.tool.codedata;

import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;

import java.util.List;

/**
 * Hibernate映射文件数据
 */
public class HibernateMapperData implements AbstractCodeData< List<AbstractCodeDataModel>> {

    /**
     * Default constructor
     */
    public HibernateMapperData() {
    }
    @Override
    public  List<AbstractCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
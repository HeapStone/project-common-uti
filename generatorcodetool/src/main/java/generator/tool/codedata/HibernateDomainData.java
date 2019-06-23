package generator.tool.codedata;

import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;

import java.util.List;

/**
 * Hibernate实体类数据
 */
public class HibernateDomainData implements AbstractCodeData < List<AbstractCodeDataModel>> {

    /**
     * Default constructor
     */
    public HibernateDomainData() {
    }
    @Override
    public  List<AbstractCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
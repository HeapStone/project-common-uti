package generator.tool.codedata;

import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;

import java.util.List;

/**
 * dao代码模型数据
 */
public class HibernateDaoCodeData implements AbstractCodeData<List<AbstractCodeDataModel>> {

    /**
     * Default constructor
     */
    public HibernateDaoCodeData() {
    }

    @Override
    public List<AbstractCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
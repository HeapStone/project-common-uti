package generator.tool.pmsc.codedata;

import generator.tool.codedata.AbstractCodeData;
import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;

import java.util.List;

/**
 * dao代码模型数据
 */
public class PsmcDaoCodeData implements AbstractCodeData<List<AbstractCodeDataModel>> {

    /**
     * Default constructor
     */
    public PsmcDaoCodeData() {
    }

    @Override
    public List<AbstractCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
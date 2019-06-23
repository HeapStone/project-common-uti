package generator.tool.codedata;

import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;

import java.util.List;

/**
 * HTML页面数据
 */
public class HTMLData implements AbstractCodeData < List<AbstractCodeDataModel>> {

    /**
     * Default constructor
     */
    public HTMLData() {
    }
    @Override
    public  List<AbstractCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
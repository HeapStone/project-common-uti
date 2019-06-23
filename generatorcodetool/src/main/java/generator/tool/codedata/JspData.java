package generator.tool.codedata;

import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;

import java.util.List;

/**
 * JSP页面数据
 */
public class JspData implements AbstractCodeData < List<AbstractCodeDataModel>> {

    /**
     * Default constructor
     */
    public JspData() {
    }
    @Override
    public  List<AbstractCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }

}
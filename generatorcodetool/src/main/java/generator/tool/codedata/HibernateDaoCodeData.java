package generator.tool.codedata;

import generator.tool.model.TableBean;

import java.util.List;

/**
 * dao代码模型数据
 */
public class HibernateDaoCodeData implements AbstractCodeData {

    /**
     * Default constructor
     */
    public HibernateDaoCodeData() {
    }

    @Override
    public Object inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
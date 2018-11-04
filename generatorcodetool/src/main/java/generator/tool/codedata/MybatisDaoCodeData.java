package generator.tool.codedata;

import generator.tool.model.TableBean;

import java.util.List;

/**
 * dao代码模型数据
 */
public class MybatisDaoCodeData implements AbstractCodeData {

    /**
     * Default constructor
     */
    public MybatisDaoCodeData() {
    }

    @Override
    public Object inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
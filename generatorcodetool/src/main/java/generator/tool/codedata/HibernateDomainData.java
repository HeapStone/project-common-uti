package generator.tool.codedata;

import generator.tool.model.TableBean;

import java.util.List;

/**
 * Hibernate实体类数据
 */
public class HibernateDomainData implements AbstractCodeData {

    /**
     * Default constructor
     */
    public HibernateDomainData() {
    }
    @Override
    public Object inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
package generator.tool.codedata;

import generator.tool.model.BeanModel;
import generator.tool.model.TableBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码文件的数据超类
 */
public abstract class AbstractCodeData  {
    /**
     * Default constructor
     */
    public AbstractCodeData() {
    }

    /**
     * 初始化化代码文件数据
     * @param  tableBeans 数据库表信息
     */
    public abstract Object inIntCodeFileData(List<TableBean> tableBeans);

}
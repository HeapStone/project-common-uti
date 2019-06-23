package generator.tool.codedata;

import generator.tool.model.TableBean;

import java.util.List;

/**
 * 代码文件的数据超类
 */
public interface AbstractCodeData <T extends List>  {

    /**
     * 初始化化代码文件数据
     * @param  tableBeans 数据库表信息
     */
    T inIntCodeFileData(List<TableBean> tableBeans);

}
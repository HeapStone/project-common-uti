package generator.tool.codedata;

import generator.tool.model.TableBean;
import generator.tool.model.codedata.AbstractCodeDataModel;

import java.util.List;

/**
 * Structs2.0控制层数据
 */
public class Structs2ControllerData implements AbstractCodeData < List<AbstractCodeDataModel>> {

    /**
     * Default constructor
     */
    public Structs2ControllerData() {
    }
    @Override
    public  List<AbstractCodeDataModel> inIntCodeFileData(List<TableBean> tableBeans) {
        return null;
    }
}
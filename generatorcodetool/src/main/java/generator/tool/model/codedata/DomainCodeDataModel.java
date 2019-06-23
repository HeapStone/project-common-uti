package generator.tool.model.codedata;

import generator.tool.model.TableBean;
import generator.tool.model.TableColumn;
import generator.tool.model.config.CodeFileCfg;
import generator.tool.model.config.DomainCodeConfig;
import generator.tool.util.ColumnToPropertyUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2018年1月21日
 */
@Data
public class DomainCodeDataModel  extends AbstractCodeDataModel{
	/**
	 * 导包
	 */
	private List<String> importStrs;

	/**
	 * 实体类注释
	 */
	private String beanContent;
	/**
	 * 属性名和属性类型
	 */
	private List<DomainPropertiesModel> columns;
	public DomainCodeDataModel(CodeFileCfg codeFileCfg,TableBean table , List<TableColumn> tableColumns,String beanName) {
		DomainCodeConfig domainCfg = codeFileCfg.getCommonConfig().getDaominCode();
		List<DomainPropertiesModel> beanProperties = intJavaProperByTableColumnName(tableColumns);
		String packageNameStr = domainCfg.getPakageName();
		this.setFileName(beanName);
		this.setPackageNameStr(packageNameStr);
		this.setColumns(beanProperties);
		this.setBeanContent(table.getTableContent());
		this.setImportStrs(ColumnToPropertyUtil.getImportStrByBeanProperType(beanProperties));
	}
	/**
	 * <p>Description:根据列名初始化javabean的实体属性<p>
	 * @param tableColumns 表属性列
	 * @return 实体类属性列表
	 * @author wanglei 2018年1月21日
	 */
	private  List<DomainPropertiesModel> intJavaProperByTableColumnName(List<TableColumn> tableColumns){
		List<DomainPropertiesModel> beanPropertieses = new ArrayList<>();
		if(null!=tableColumns && tableColumns.size()>0){
			for(TableColumn tableColumn: tableColumns){
				DomainPropertiesModel beanProperties = new DomainPropertiesModel();
				String columnName = ColumnToPropertyUtil.camelName(tableColumn.getColumnName());
				beanProperties.setPropertName(ColumnToPropertyUtil.camelName(columnName));
				beanProperties.setPropertComment(tableColumn.getColumnRmark());
				beanProperties.setPropertType(ColumnToPropertyUtil.getBenaPropertiesTypeByTableColumn(tableColumn.getColumnType()));
				beanProperties.setPropertNameUpCase(StringUtils.capitalize(columnName));
				beanProperties.setJdbcType(ColumnToPropertyUtil.getJdbcTypeByTableColumn(tableColumn.getColumnType()));
				beanPropertieses.add(beanProperties);
			}
		}
		return beanPropertieses;
	}
}

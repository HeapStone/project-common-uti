package generator.tool.model.codedata;

import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2018年1月21日
 */
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

	public List<String> getImportStrs() {
		return importStrs;
	}
	public void setImportStrs(List<String> importStrs) {
		this.importStrs = importStrs;
	}

	
	public List<DomainPropertiesModel> getColumns() {
		return columns;
	}
	public void setColumns(List<DomainPropertiesModel> columns) {
		this.columns = columns;
	}
	
	public String getBeanContent() {
		return beanContent;
	}
	public void setBeanContent(String beanContent) {
		this.beanContent = beanContent;
	}
	@Override
	public String toString() {
		return "DomainCodeDataModel [importStrs="
				+ importStrs +  ", columns="
				+ columns +"columns="
						+ columns + "]";
	}
	public DomainCodeDataModel() {
		super();
	}
	
}

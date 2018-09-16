package generator.tool.model;

import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2018年1月21日
 */
public class BeanModel {
	/**
	 *包名 也是实体类的唯一标示
	 */
	private String packageNameStr;
	/**
	 * 导包
	 */
	private List<String> importStrs;
	
	/**
	 * 类名
	 */
	private String beanName;
	/**
	 * 实体类注释
	 */
	private String beanContent;
	/**
	 * 属性名和属性类型
	 */
	private List<BeanProperties> columns;
	public String getPackageNameStr() {
		return packageNameStr;
	}
	public void setPackageNameStr(String packageNameStr) {
		this.packageNameStr = packageNameStr;
	}
	public List<String> getImportStrs() {
		return importStrs;
	}
	public void setImportStrs(List<String> importStrs) {
		this.importStrs = importStrs;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	public List<BeanProperties> getColumns() {
		return columns;
	}
	public void setColumns(List<BeanProperties> columns) {
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
		return "BeanModel [packageNameStr=" + packageNameStr + ", importStrs="
				+ importStrs + ", beanName=" + beanName + ", columns="
				+ columns +"columns="
						+ columns + "]";
	}
	public BeanModel() {
		super();
	}
	
}

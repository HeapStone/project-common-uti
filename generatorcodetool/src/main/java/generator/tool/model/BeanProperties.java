package generator.tool.model;


/**
 * <p>Title:生成实体类的实体类对应关系描述 </p>
 * <p>Description: 生成实体类的实体类对应关系描述</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2018年1月21日
 */
public class BeanProperties {
	/**
	 * 属性类型
	 */
	private String propertType;
	/**
	 * 属性名称
	 */
	private String propertName;
	/**
	 * 属性注释
	 */
	private String propertComment;
	/**
	 * 属性名首字母大写
	 */
	private String propertNameUpCase;
	private String jdbcType;
	public String getPropertType() {
		return propertType;
	}
	public void setPropertType(String propertType) {
		this.propertType = propertType;
	}
	public String getPropertName() {
		return propertName;
	}
	public void setPropertName(String propertName) {
		this.propertName = propertName;
	}
	public String getPropertComment() {
		return propertComment;
	}
	public void setPropertComment(String propertComment) {
		this.propertComment = propertComment;
	}
	
	public String getPropertNameUpCase() {
		return propertNameUpCase;
	}
	public void setPropertNameUpCase(String propertNameUpCase) {
		this.propertNameUpCase = propertNameUpCase;
	}
	@Override
	public String toString() {
		return "BeanProperties [propertType=" + propertType + ", propertName="
				+ propertName + ", propertComment=" + propertComment + "]";
	}
	public BeanProperties() {
		super();
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
}

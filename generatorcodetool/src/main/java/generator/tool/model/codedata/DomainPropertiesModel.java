package generator.tool.model.codedata;


import lombok.Data;

/**
 * <p>Title:生成实体类的实体类对应关系描述 </p>
 * <p>Description: 生成实体类的实体类对应关系描述</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2018年1月21日
 */
@Data
public class DomainPropertiesModel  extends AbstractCodeDataModel{
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
}

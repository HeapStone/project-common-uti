package generator.tool.model;

import lombok.Data;

/**
 * <p>Title:数据库表属性实体类 </p>
 * <p>Description: 数据库表属性实体类</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2018年1月21日
 */
@Data
public class TableColumn {
	/**
	 * 属性名
	 */
	private String ColumnName;
	/**
	 * 属性类型
	 */
	private String ColumnType;
	/**
	 * 属性大小
	 */
	private int ColumnSize;
	/**
	 * 属性注释
	 */
	private String ColumnRmark;
	/**
	 * Decimal 长度
	 */
	private int ColumnDecimalDigits;
	/**
	 * 是否为空
	 */
	private int ColumnIsnull;
	public TableColumn() {
		super();
	}
	
}

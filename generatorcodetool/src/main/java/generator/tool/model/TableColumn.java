package generator.tool.model;

/**
 * <p>Title:数据库表属性实体类 </p>
 * <p>Description: 数据库表属性实体类</p>
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history:
 * Created by wanglei 2018年1月21日
 */
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
	public String getColumnName() {
		return ColumnName;
	}
	public void setColumnName(String columnName) {
		ColumnName = columnName;
	}
	public String getColumnType() {
		return ColumnType;
	}
	public void setColumnType(String columnType) {
		ColumnType = columnType;
	}
	public int getColumnSize() {
		return ColumnSize;
	}
	public void setColumnSize(int columnSize) {
		ColumnSize = columnSize;
	}
	public String getColumnRmark() {
		return ColumnRmark;
	}
	public void setColumnRmark(String columnRmark) {
		ColumnRmark = columnRmark;
	}
	public int getColumnDecimalDigits() {
		return ColumnDecimalDigits;
	}
	public void setColumnDecimalDigits(int columnDecimalDigits) {
		ColumnDecimalDigits = columnDecimalDigits;
	}
	
	public int getColumnIsnull() {
		return ColumnIsnull;
	}
	public void setColumnIsnull(int columnIsnull) {
		ColumnIsnull = columnIsnull;
	}
	
	@Override
	public String toString() {
		return "TableColumn [ColumnName=" + ColumnName + ", ColumnType="
				+ ColumnType + ", ColumnSize=" + ColumnSize + ", ColumnRmark="
				+ ColumnRmark + ", ColumnDecimalDigits=" + ColumnDecimalDigits
				+ ", ColumnIsnull=" + ColumnIsnull + "]";
	}
	public TableColumn() {
		super();
	}
	
}

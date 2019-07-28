package ${freemMarkParams.packageNameStr};
import java.io.Serializable;
<#--导入依赖包-->
<#list freemMarkParams.importStrs as importStr>
import ${importStr};
</#list>

<#if freemMarkParams.beanContent!="">
/*
 *${freemMarkParams.beanContent}
 *
 */
</#if>
public class ${freemMarkParams.fileName} implements Serializable{
    /**
	 * serialVersionUID
	 */
 private static final long serialVersionUID = 1L;

<#--属性信息 -->
<#list freemMarkParams.columns as _column>
    <#if _column.propertComment!="">
     // ${_column.propertComment}
    </#if>
    private ${_column.propertType} ${_column.propertName};

</#list>
<#--get set 方法-->
<#list freemMarkParams.columns as _column>

    public ${_column.propertType} get${_column.propertNameUpCase}(){
        return ${_column.propertName};
    }
    
    public void set${_column.propertNameUpCase}(${_column.propertType} ${_column.propertName}s){
        this.${_column.propertName} = ${_column.propertName}s;
    }
</#list>
<#--构造函数-->

    public ${freemMarkParams.fileName}(){
     	super();
	}
<#--toString 方法-->
	@Override
	public String toString() {
		return "${freemMarkParams.fileName} ["+""
<#list freemMarkParams.columns as _column>
		 + "${_column.propertName}="+${_column.propertName}+","
</#list>
		  + "]";
	}

}
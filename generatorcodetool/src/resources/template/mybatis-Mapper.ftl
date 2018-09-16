<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperConfig.cfgClassName}">
     <!--映射关系-->
    <resultMap id="${mapperConfig.cfgResultMapID}" type=${mapperConfig.cfgResultType}">
      <#list mapperConfig.cfgPropertys as cfp>
         <#if mapperConfig.cfgTablePk??>
            <#if cfp.columnName == mapperConfig.cfgTablePk>
             <id   column="${cfp.columnName}" jdbcType="${cfp.jdbcType}" property="${cfp.propertyName}" />
            </#if>
         </#if>
         <#if cfp.columnName != mapperConfig.cfgTablePk>
            <result column="${cfp.columnName}" jdbcType="${cfp.jdbcType}" property="${cfp.propertyName}" />
         </#if>
        </#list>
    </resultMap>
    <!--基础列-->

    <sql id="baseColumnName">
       ${mapperConfig.baseColumn}
    </sql>

      <!--列表查询-->
    <select id="${mapperConfig.selectListSqlID}" parameterType="hashmap" resultType="java.util.Map">
       SELECT
        <include refid="baseColumnName" />
        FROM
          ${mapperConfig.cfgTableName}
        <where>
            1 = 1
         <#list mapperConfig.cfgPropertys as cfp>
            <if test="${cfp.propertyName} != null && ${cfp.propertyName}!= ''">
              ${cfp.columnName} = ${r"#{"}${cfp.propertyName},${cfp.jdbcType}}
            </if>
        </#list>
        </where>
    </select>

      <!--根据主键查询-->
    <select id="${mapperConfig.selectByPrimaryKeySqlID}" parameterType="hashmap" resultType="java.util.Map">
       SELECT
        <include refid="baseColumnName" />
        FROM
          ${mapperConfig.cfgTableName}
        where ${mapperConfig.cfgTablePk} = #${mapperConfig.beanPkColmn}
    </select>

    <!--根据主键删除-->
    <delete id="${mapperConfig.deleteByPrimaryKeySqlID}" parameterType="hashmap">
         DELETE FROM  ${mapperConfig.cfgTableName}
        where ${mapperConfig.cfgTablePk} in
        <foreach item="item" index="index" collection="ids" open="(" separator="," close=")">
            ${r"#{item}"}
        </foreach>
    </delete>

    <!--插入-->
    <insert id="${mapperConfig.insertSqlID}" parameterType="${mapperConfig.cfgResultType}">
        INSERT INTO ${mapperConfig.cfgTableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
           <#list mapperConfig.cfgPropertys as cfp>
                <if test="${cfp.propertyName} != null">
                  ${cfp.columnName}
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
          <#list mapperConfig.cfgPropertys as cfp>
            <if test="${cfp.propertyName} != null">
                ${r"#{"}${cfp.propertyName},${cfp.jdbcType}}
             </if>
          </#list>
        </trim>
    </insert>

    <!--更新-->
    <update id="${mapperConfig.updateSqlID}" parameterType="${mapperConfig.cfgResultType}">
        UPDATE  ${mapperConfig.cfgTableName}
        <set suffixOverrides=",">
            <#list mapperConfig.cfgPropertys as cfp>
                <if test="${cfp.propertyName} != null">
                ${cfp.columnName} = ${r"#{"}${cfp.propertyName},${cfp.jdbcType}}
                </if>
            </#list>
        </set>
          where ${mapperConfig.cfgTablePk} = #${mapperConfig.beanPkColmn}
    </update>

</mapper>
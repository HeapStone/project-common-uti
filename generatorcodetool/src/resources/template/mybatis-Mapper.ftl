<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${freemMarkParams.cfgClassName}">
     <!--映射关系-->
    <resultMap id="${freemMarkParams.cfgResultMapID}" type="${freemMarkParams.cfgResultType}">
      <#list freemMarkParams.cfgPropertys as cfp>
         <#if freemMarkParams.cfgTablePk??>
            <#if cfp.columnName == freemMarkParams.cfgTablePk>
             <id   column="${cfp.columnName}" jdbcType="${cfp.jdbcType}" property="${cfp.propertyName}" />
            </#if>
         </#if>
         <#if cfp.columnName != freemMarkParams.cfgTablePk>
            <result column="${cfp.columnName}" jdbcType="${cfp.jdbcType}" property="${cfp.propertyName}" />
         </#if>
        </#list>
    </resultMap>
    <!--基础列-->

    <sql id="baseColumnName">
       ${freemMarkParams.baseColumn}
    </sql>

      <!--列表查询-->
    <select id="${freemMarkParams.selectListSqlID}" parameterType="hashmap" resultType="java.util.Map">
       SELECT
        <include refid="baseColumnName" />
        FROM
          ${freemMarkParams.cfgTableName}
        <where>
            1 = 1
         <#list freemMarkParams.cfgPropertys as cfp>
            <if test="${cfp.propertyName} != null and ${cfp.propertyName}!= ''">
              ${cfp.columnName} = ${r"#{"}${cfp.propertyName},${cfp.jdbcType}}
            </if>
        </#list>
        </where>
    </select>

      <!--根据主键查询-->
    <select id="${freemMarkParams.selectByPrimaryKeySqlID}" parameterType="hashmap" resultType="java.util.Map">
       SELECT
        <include refid="baseColumnName" />
        FROM
          ${freemMarkParams.cfgTableName}
        where ${freemMarkParams.cfgTablePk} = ${r"#{"}${freemMarkParams.beanPkColmn}}
    </select>

    <!--根据主键删除-->
    <delete id="${freemMarkParams.deleteByPrimaryKeySqlID}" parameterType="hashmap">
         DELETE FROM  ${freemMarkParams.cfgTableName}
        where ${freemMarkParams.cfgTablePk} in
        <foreach item="item" index="index" collection="ids" open="(" separator="," close=")">
            ${r"#{item}"}
        </foreach>
    </delete>

    <!--插入-->
    <insert id="${freemMarkParams.insertSqlID}" parameterType="${freemMarkParams.cfgResultType}">
        INSERT INTO ${freemMarkParams.cfgTableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
           <#list freemMarkParams.cfgPropertys as cfp>
                <if test="${cfp.propertyName} != null">
                  ${cfp.columnName},
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
          <#list freemMarkParams.cfgPropertys as cfp>
            <if test="${cfp.propertyName} != null">
                ${r"#{"}${cfp.propertyName},jdbcType=${cfp.jdbcType}},
             </if>
          </#list>
        </trim>
    </insert>

    <!--更新-->
    <update id="${freemMarkParams.updateSqlID}" parameterType="${freemMarkParams.cfgResultType}">
        UPDATE  ${freemMarkParams.cfgTableName}
        <trim prefix="set" suffixOverrides=",">
            <#list freemMarkParams.cfgPropertys as cfp>
              <#if cfp.columnName != freemMarkParams.cfgTablePk>
                    <if test="${cfp.propertyName} != null">
                    ${cfp.columnName} = ${r"#{"}${cfp.propertyName},jdbcType=${cfp.jdbcType}},
                    </if>
              </#if>
            </#list>
        </trim>
          where ${freemMarkParams.cfgTablePk} = ${r"#{"}${freemMarkParams.beanPkColmn}}
    </update>

</mapper>
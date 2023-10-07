package mao.activiti_global_variable.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * Project name(项目名称)：activiti-global-variable
 * Package(包名): mao.activiti_global_variable.entity
 * Class(类名): Evection
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/7
 * Time(创建时间)： 16:01
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Evection implements Serializable
{
    /**
     * 主键id
     */
    private Long id;
    /**
     * 出差申请单名称
     */
    private String evectionName;
    /**
     * 出差天数
     */
    private Double num;
    /**
     * 预计开始时间
     */
    private Date beginDate;
    /**
     * 预计结束时间
     */
    private Date endDate;
    /**
     * 目的地
     */
    private String destination;
    /**
     * 出差事由
     */
    private String reson;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEvectionName()
    {
        return evectionName;
    }

    public void setEvectionName(String evectionName)
    {
        this.evectionName = evectionName;
    }

    public Date getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    public String getReson()
    {
        return reson;
    }

    public void setReson(String reson)
    {
        this.reson = reson;
    }

    public Double getNum()
    {
        return num;
    }

    public void setNum(Double num)
    {
        this.num = num;
    }

    @Override
    public String toString()
    {
        return new StringJoiner(", ", Evection.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("evectionName='" + evectionName + "'")
                .add("num=" + num)
                .add("beginDate=" + beginDate)
                .add("endDate=" + endDate)
                .add("destination='" + destination + "'")
                .add("reson='" + reson + "'")
                .toString();
    }
}

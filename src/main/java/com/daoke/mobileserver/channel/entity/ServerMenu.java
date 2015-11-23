package com.daoke.mobileserver.channel.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * 服务频道
 * Created by chenmaomao on 5/26 0026.
 */
public class ServerMenu implements Serializable {

    private static final long serialVersionUID = -2020071925103972041L;

    private Integer id; //主键
    private Integer serverChannelID; //服务频道ID
    private String name; //名称
    private String url; //跳转url
    private Integer parentID; //父菜单id
    @JsonIgnore
    private Integer isValid; //是否有效 1:是 0:否
    private Long createTime; //创建时间
    @JsonIgnore
    private Long updateTime; //修改时间
    private List<ServerMenu> childMenuList;

    public ServerMenu() {
    }

    public ServerMenu(Integer id, Integer serverChannelID, String name, String url, Integer parentID, Integer isValid, Long createTime, Long updateTime) {
        this.id = id;
        this.serverChannelID = serverChannelID;
        this.name = name;
        this.url = url;
        this.parentID = parentID;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServerChannelID() {
        return serverChannelID;
    }

    public void setServerChannelID(Integer serverChannelID) {
        this.serverChannelID = serverChannelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<ServerMenu> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<ServerMenu> childMenuList) {
        this.childMenuList = childMenuList;
    }
}

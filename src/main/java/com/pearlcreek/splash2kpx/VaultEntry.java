/*
 * Copyright (c) 2014 - 2015. Pearl Creek Research, LLC. All rights reserved.
 * This notice may not be removed.
 */
package com.pearlcreek.splash2kpx;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * User: vlott
 * Date: Dec 12, 2014
 * Time: 14:32:52
 */
public class VaultEntry {

    private String title;
    private String username;
    private String password;
    private String url;
    private String comment;
    private String icon;
    private String creation;
    private String lastaccess;
    private String lastmod;
    private String expire;

    public VaultEntry(String title) {
        this.title = title;
        this.expire = "Never";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public void setLastaccess(String lastaccess) {
        this.lastaccess = lastaccess;
    }

    public void setLastmod(String lastmod) {
        this.lastmod = lastmod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VaultEntry that = (VaultEntry) o;

        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (creation != null ? !creation.equals(that.creation) : that.creation != null) return false;
        if (expire != null ? !expire.equals(that.expire) : that.expire != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (lastaccess != null ? !lastaccess.equals(that.lastaccess) : that.lastaccess != null) return false;
        if (lastmod != null ? !lastmod.equals(that.lastmod) : that.lastmod != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (!title.equals(that.title)) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (creation != null ? creation.hashCode() : 0);
        result = 31 * result + (lastaccess != null ? lastaccess.hashCode() : 0);
        result = 31 * result + (lastmod != null ? lastmod.hashCode() : 0);
        result = 31 * result + (expire != null ? expire.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        StringBuilder b = new StringBuilder();
        b.append("  <entry>").append(System.getProperty("line.separator"));
        b.append("   <title>").append(StringEscapeUtils.escapeXml(title)).append("</title>").append(System.getProperty("line.separator"));
        b.append("   <username>").append(StringEscapeUtils.escapeXml(username)).append("</username>").append(System.getProperty("line.separator"));
        b.append("   <password>").append(StringEscapeUtils.escapeXml(password)).append("</password>").append(System.getProperty("line.separator"));
        b.append("   <url>").append(StringEscapeUtils.escapeXml(url)).append("</url>").append(System.getProperty("line.separator"));
        b.append("   <comment>").append(StringEscapeUtils.escapeXml(comment)).append("</comment>").append(System.getProperty("line.separator"));
        b.append("   <icon>").append(icon).append("</icon>").append(System.getProperty("line.separator"));
        b.append("   <creation>").append(StringEscapeUtils.escapeXml(creation)).append("</creation>").append(System.getProperty("line.separator"));
        b.append("   <lastaccess>").append(StringEscapeUtils.escapeXml(lastaccess)).append("</lastaccess>").append(System.getProperty("line.separator"));
        b.append("   <lastmod>").append(StringEscapeUtils.escapeXml(lastmod)).append("</lastmod>").append(System.getProperty("line.separator"));
        b.append("   <expire>").append(StringEscapeUtils.escapeXml(expire)).append("</expire>").append(System.getProperty("line.separator"));
        b.append("  </entry>").append(System.getProperty("line.separator"));
        return b.toString();
    }

}

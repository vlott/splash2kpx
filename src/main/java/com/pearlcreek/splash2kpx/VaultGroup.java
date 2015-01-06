/*
 * Copyright (c) 2014 - 2015. Pearl Creek Research, LLC. All rights reserved.
 * This notice may not be removed.
 */
package com.pearlcreek.splash2kpx;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * User: vlott
 * Date: Dec 12, 2014
 * Time: 14:32:52
 */
public class VaultGroup {

    private String title;
    private String icon;
    private Set<VaultEntry> entries = new HashSet<>();

    public VaultGroup(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public Set<VaultEntry> getEntries() {
        return entries;
    }

    public void addEntry(VaultEntry entry) {
        if (entry != null) {
            this.entries.add(entry);
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append(" <group>").append(System.getProperty("line.separator"));
        b.append("  <title>").append(StringEscapeUtils.escapeXml(title)).append("</title>").append(System.getProperty("line.separator"));
        b.append("  <icon>").append(icon).append("</icon>").append(System.getProperty("line.separator"));
        for (VaultEntry e : entries) {
            b.append(e.toString());
        }
        b.append(" </group>").append(System.getProperty("line.separator"));
        return b.toString();
    }

}

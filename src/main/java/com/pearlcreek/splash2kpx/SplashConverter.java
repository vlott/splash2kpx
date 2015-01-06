/*
 * Copyright (c) 2014 - 2015. Pearl Creek Research, LLC. All rights reserved.
 * This notice may not be removed.
 */
package com.pearlcreek.splash2kpx;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;

/**
 * User: vlott
 * Date: Dec 12, 2014
 * Time: 12:04:00 PM
 */
public class SplashConverter {

    private final String KPX_SUFFIX = "CONVERTED_TO_KPX";

    private File splashFile;
    private Integer entryCount = 0;
    private HashMap<String, VaultGroup> vaultGroupMap = new HashMap<>();

    public SplashConverter(String path) {
        if (path == null) {
            throw new RuntimeException("Usage: java SplashConverter [fullyQualifiedPathToSplashFile]");
        }
        splashFile = null;
        try {
            splashFile = new File(path);
        } catch (Exception e) {
            throw new RuntimeException("Unable to open file: " + path, e);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new RuntimeException("Usage: java SplashConverter [fullyQualifiedPathToSplashFile]");
        }
        SplashConverter c = new SplashConverter(args[0]);
        c.convert();
    }

    public void convert() throws IOException {
        parseSplashFile();
        writeOutputFile();
    }

    public void parseSplashFile() {
        if (splashFile.isFile() && splashFile.length() > 0 && splashFile.canRead()) {
            readSplashFileContents();
        }
    }

    private void readSplashFileContents() {
        try {

            CSVParser parser = CSVParser.parse(splashFile, Charset.forName("UTF-8"), CSVFormat.EXCEL);
            for (CSVRecord r : parser) {
                VaultGroup group = null;
                entryCount++;
                
                // Group Name
                if (r.get(0) == null || r.get(0).isEmpty()) {
                    continue;
                } else {
                    if (vaultGroupMap.containsKey(r.get(0).trim())) {
                        group = vaultGroupMap.get(r.get(0).trim());
                    } else {
                        VaultGroup g = new VaultGroup(r.get(0).trim(), "1");
                        vaultGroupMap.put(r.get(0), g);
                        group = g;
                    }
                }

                // Entry Title
                VaultEntry e = new VaultEntry(r.get(1));
                e.setUsername(r.get(2));
                e.setPassword(r.get(3));
                e.setUrl(r.get(4));

                String street = r.get(5) != null && !r.get(5).isEmpty() ? r.get(5) : null;
                String city = r.get(6) != null && !r.get(6).isEmpty() ? r.get(6) : null;
                String state = r.get(7) != null && !r.get(7).isEmpty() ? r.get(7) : null;
                String zip = r.get(8) != null && !r.get(8).isEmpty() ? r.get(8) : null;

                // String address = street != null ? street : "";
                StringBuilder address = new StringBuilder();
                if (street != null && !street.isEmpty()) {
                    address.append(street);
                }
                if (city != null && !city.isEmpty()) {
                    if (!address.toString().isEmpty()) {
                        address.append(", ");
                    }
                    address.append(city);
                }
                if (state != null && !state.isEmpty()) {
                    if (!address.toString().isEmpty()) {
                        address.append(", ");
                    }
                    address.append(state);
                }
                if (zip != null && !zip.isEmpty()) {
                    if (!address.toString().isEmpty()) {
                        address.append(", ");
                    }
                    address.append(zip);
                }
                if (!address.toString().isEmpty()) {
                    address.insert(0, "Address: ");
                }

                String code = r.get(9);
                if (code != null && !code.isEmpty()) {
                    code = "Code: " + code + "\n\n";
                } else {
                    code = "";
                }

                String comment = r.get(11);
                if (comment == null) {
                    comment = "";
                }

                String expandedComment = address.toString() + code + comment;
                e.setComment(expandedComment);
                String creation = r.get(10);
                if (creation != null && !creation.isEmpty()) {
                    e.setCreation(creation);
                }
                e.setIcon("0");
                e.setLastaccess(new Date().toString());
                e.setLastmod(new Date().toString());
                group.addEntry(e);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Number of entries processed = " + entryCount);
        }
    }

    public void writeOutputFile() throws IOException {

        File outFile = new File(getOutputFilename());
        outFile.createNewFile();

        if (!outFile.exists()) {
            throw new FileNotFoundException("File does not exist: " + outFile);
        }
        if (!outFile.isFile()) {
            throw new IllegalArgumentException("Should not be a directory: " + outFile);
        }
        if (!outFile.canWrite()) {
            throw new IllegalArgumentException("File cannot be written: " + outFile);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE KEEPASSX_DATABASE>\n");
        builder.append("<database>\n");
        for (VaultGroup g : vaultGroupMap.values()) {
            builder.append(g.toString());
        }
        builder.append("</database>\n");

        //use buffering
        Writer output = new BufferedWriter(new FileWriter(outFile));
        try {
            //FileWriter always assumes default encoding is OK!
            output.write(builder.toString());
            System.out.println("Wrote Output File: " + outFile.getAbsolutePath());
        } finally {
            output.close();
        }
    }

    private String getOutputFilename() {
        String path = FilenameUtils.getPath(splashFile.getAbsolutePath());
        String fileNameSansExtension = FilenameUtils.removeExtension(splashFile.getName());
        return File.separator + path + fileNameSansExtension + KPX_SUFFIX + ".xml";
    }

}

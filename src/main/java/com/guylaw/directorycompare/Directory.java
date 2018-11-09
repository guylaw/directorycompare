package com.guylaw.directorycompare;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Directory {
  Set<File> files;
  String root;
  Set<String> allPaths = new HashSet<String>();
  List<File> uniqueFiles = new ArrayList<File>();

  public Set<File> getFiles() {
    return files;
  }

  public void setFiles(Set<File> files) {
    this.files = files;
  }

  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }

  public Set<String> getAllPaths() {
    return allPaths;
  }

  public void setAllPaths(Set<String> allPaths) {
    this.allPaths = allPaths;
  }

  public List<File> getUniqueFiles() {
    return uniqueFiles;
  }

  public void setUniqueFiles(List<File> uniqueFiles) {
    this.uniqueFiles = uniqueFiles;
  }

  public Directory(String root) {
    super();
    this.root = root;
  }

}

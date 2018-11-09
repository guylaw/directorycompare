package com.guylaw.directorycompare;

import java.util.Comparator;
import java.io.File;

public class FileComparator implements Comparator<File> {

  public int compare(File a, File b) {
    return a.getPath().compareTo(b.getPath());
  }

}

package com.guylaw.directorycompare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Compare {

  void compare(String path1, String path2) throws IOException {
    return;
  }

  void printResults(Directory dir) {
    System.out.println("++++++++++++++++ " + dir.getRoot() + " ++++++++++++++++");
    for (File f : dir.getUniqueFiles()) {
      System.out.println(f.getPath());
    }
  }

  String getCommonPath(String input, String root) {
    return input.substring(root.length());
  }

  Set<File> getFiles(File dir) {
    Set<File> set = new HashSet<File>();
    for (File f : dir.listFiles()) {
      if (f.isDirectory()) {
        set.addAll(getFiles(f));
      }
      if (!f.getName().contains(".DS_Store")) {
        set.add(f);

      }
    }

    return set;
  }

  void processPathsFromFiles(Directory input) {
    for (File file : input.getFiles()) {
      input.getAllPaths().add(getCommonPath(file.getPath(), input.getRoot()));
    }
  }

  void hydrateDiffs(Directory a, Directory b) {

    for (File file : a.getFiles()) {
      if (b.getAllPaths().add(getCommonPath(file.getPath(), a.root))) {
        a.getUniqueFiles().add(file);
      }
    }

    for (File file : b.files) {
      if (a.getAllPaths().add(getCommonPath(file.getPath(), b.root))) {
        b.getUniqueFiles().add(file);
      }
    }

    Collections.sort(a.getUniqueFiles(), new FileComparator());
    Collections.sort(b.getUniqueFiles(), new FileComparator());
  }

  void sync(Directory source, Directory dest) throws IOException {
    for (File f : source.getUniqueFiles()) {
      if (f.isDirectory()) {
        System.out.println("Making " + f.getName());
        new File(dest.getRoot() + getCommonPath(f.getPath(), source.getRoot())).mkdir();
      } else {
        System.out.println("Copying " + f.getPath());
        Files.copy(Paths.get(f.getPath()), Paths.get(dest.getRoot(), getCommonPath(f.getPath(), source.getRoot())),
            StandardCopyOption.REPLACE_EXISTING);
      }
    }
    return;
  }
}

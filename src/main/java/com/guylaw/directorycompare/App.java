package com.guylaw.directorycompare;

import java.io.File;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws Exception {
    String path1, path2;

    if (args.length != 2) {
      path1 = "/Users/guylaw/MP3";
      path2 = "/Volumes/PASSPORT/MP3";
    } else {
      path1 = args[0];
      path2 = args[1];
    }

    Directory dir1 = new Directory(path1);
    Directory dir2 = new Directory(path2);
    Compare compare = new Compare();
    dir1.setFiles(compare.getFiles(new File(dir1.getRoot())));
    dir2.setFiles(compare.getFiles(new File(dir2.getRoot())));
    compare.processPathsFromFiles(dir1);
    compare.processPathsFromFiles(dir2);
    compare.hydrateDiffs(dir1, dir2);
    compare.printResults(dir1);
    compare.printResults(dir2);
    if (!dir1.getUniqueFiles().isEmpty() || !dir2.getUniqueFiles().isEmpty()){
      System.out.print("\n\nSync directories y/n: ");
      Scanner scanner = new Scanner(System.in);
      String input = scanner.nextLine();
      scanner.close();
      if (input.equals("y")) {
        System.out.println("Syncing directories....");
        compare.sync(dir1, dir2);
        compare.sync(dir2, dir1);
      }
    }
    System.out.println("Program Exited");

    return;
  }

}

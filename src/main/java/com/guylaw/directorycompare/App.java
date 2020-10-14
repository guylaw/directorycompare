package com.guylaw.directorycompare;

import java.io.File;
import java.util.Scanner;

public class App {

  public static void main(String[] args) throws Exception {
    String path1 = null;
    String  path2 = null;
    String makePath2 = "false";
    if (args.length < 2) {
      path1 = "/Users/guylaw/MP3";
      path2 = "/Volumes/IOMEGA/MP3";

//      path1 = "/Users/guylaw/stage/YELLOW";
//      path2 = "/Volumes/YELLOW/MP3";
//
//      path1 = "/Users/guylaw/stage/BLUE";
//      path2 = "/Volumes/BLUE/MP3";
//
//      path1 = "/Users/guylaw/stage/RED";
//      path2 = "/Volumes/RED/MP3";

//      path1 = "/Users/guylaw/stage/GREEN";
//      path2 = "/Volumes/GREEN/MP3";

//      path1 = "/Users/guylaw/stage/BlackRed";
//      path2 = "/Volumes/BlackRed/MP3";
    } else if (args.length == 2) {
      path1 = Utils.trimEnd(args[0], '/');
      path2 = Utils.trimEnd(args[1], '/');
    } else if (args.length == 3) {
      makePath2 = args[2];
    }
    Boolean makeDirectory = new Boolean(makePath2);
    if (makeDirectory) {
      path2 = path2 + path1.substring(path1.lastIndexOf('/'), path1.length());
      new File(path2).mkdir();
      
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
    if (!dir1.getUniqueFiles().isEmpty() || !dir2.getUniqueFiles().isEmpty()) {
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

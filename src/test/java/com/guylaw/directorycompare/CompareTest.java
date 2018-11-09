package com.guylaw.directorycompare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CompareTest {

  @Test
  void test() throws Exception {

    Compare compare = new Compare();
    compare.compare("/Users/guylaw/MP3", "/Users/guylaw/MP3");

    // fail("Not yet implemented");
  }

  @Test
  void testCommon() {
    Compare compare = new Compare();

    assertEquals("bc", compare.getCommonPath("a", "abc"));
  }

}

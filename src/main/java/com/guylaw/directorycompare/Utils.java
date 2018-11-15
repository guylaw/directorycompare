package com.guylaw.directorycompare;

import org.apache.commons.lang3.StringUtils;

public class Utils {
  /**
   * Trim specified character from front of string
   * 
   * @param text
   *          Text
   * @param character
   *          Character to remove
   * @return Trimmed text
   */
  public static String trimFront(String text, char character) {
    String normalizedText;
    int index;

    if (StringUtils.isEmpty(text)) {
      return text;
    }

    normalizedText = text.trim();
    index = 0;

    while (normalizedText.charAt(index) == character) {
      index++;
    }
    return normalizedText.substring(index).trim();
  }

  /**
   * Trim specified character from end of string
   * 
   * @param text
   *          Text
   * @param character
   *          Character to remove
   * @return Trimmed text
   */
  public static String trimEnd(String text, char character) {
    String normalizedText;
    int index;

    if (StringUtils.isEmpty(text)) {
      return text;
    }

    normalizedText = text.trim();
    index = normalizedText.length() - 1;

    while (normalizedText.charAt(index) == character) {
      if (--index < 0) {
        return "";
      }
    }
    return normalizedText.substring(0, index + 1).trim();
  }

  /**
   * Trim specified character from both ends of a String
   * 
   * @param text
   *          Text
   * @param character
   *          Character to remove
   * @return Trimmed text
   */
  public static String trimAll(String text, char character) {
    String normalizedText = trimFront(text, character);

    return trimEnd(normalizedText, character);
  }
}

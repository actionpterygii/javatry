/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.DevilBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.DevilBoxTextNotFoundException;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author your_name_here
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestString = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    if (longestString.length() < stringContent.length()) {
                        longestString = stringContent;
                    }
                }
            }
        }
        log("Stringで入っているもので最も大きいもの: " + longestString);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestString = "";
        String shortestString = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    if (longestString.length() < stringContent.length()) {
                        longestString = stringContent;
                    }
                    if (shortestString.length() > stringContent.length()) {
                        shortestString = stringContent;
                    }
                }
            }
        }
        int stringDiff = longestString.length() - shortestString.length();
        log("Stringで入っているもので最も大きいものと小さいものの文字数差: " + stringDiff);
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestString = "";
        String sinLongestString = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content != null) {
                    String stringContent = content.toString();
                    if (sinLongestString.length() < stringContent.length()) {
                        longestString = sinLongestString;
                        sinLongestString = stringContent;
                    } else {
                        if (longestString.length() < stringContent.length()) {
                            longestString = stringContent;
                        }
                    }
                }
            }
        }
        log("入っているもので二番目に大きいもの: " + longestString);
        // 
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int totalLength = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    totalLength += stringContent.length();
                }
            }
        }
        log("Stringで入っているものの文字数の合計: " + totalLength);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> longestColorNames = new ArrayList<String>();
        String buffLongestColorName = "";
        for (ColorBox colorBox : colorBoxList) {
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            if (buffLongestColorName.length() < colorName.length()) {
                longestColorNames.clear();
                longestColorNames.add(colorName);
                buffLongestColorName = colorName;
            } else if (buffLongestColorName.length() == colorName.length()) {
                longestColorNames.add(colorName);
            }
        }
        for (String longestColorName : longestColorNames) {
            log("最も長い色の名前: " + longestColorName);
        }
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    if (((String) content).startsWith("Water")) {
                        log(colorBox.getColor());
                    }
                }
            }
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    if (((String) content).endsWith("front")) {
                        log(colorBox.getColor());
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        String targetString = "front";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    if (stringContent.endsWith(targetString)) {
                        log(stringContent.indexOf(targetString) + 1);
                    }
                }
            }
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        char target = 'ど';
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    int charCount = 0;
                    for (int i = 0; i < stringContent.length(); i++) {
                        char stringContentChar = stringContent.charAt(i);
                        if (stringContentChar == target) {
                            charCount++;
                        }
                    }
                    if (charCount >= 2) {
                        log(stringContent.lastIndexOf(target) + 1);
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        String targetString = "front";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    if (stringContent.endsWith(targetString)) {
                        log(stringContent.substring(0, 1));
                    }
                }
            }
        }
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    if (stringContent.startsWith("Water")) {
                        log(stringContent.substring(stringContent.length() - 1));
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        char target = 'o';
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof String) {
                    String stringContent = (String) content;
                    int charCount = 0;
                    for (int i = 0; i < stringContent.length(); i++) {
                        char stringContentChar = stringContent.charAt(i);
                        if (stringContentChar == target) {
                            charCount++;
                            break;
                        }
                    }
                    if (charCount >= 1) {
                        String replacedStringContent = stringContent.replace(String.valueOf(target), "");
                        log(replacedStringContent.length());
                    }
                }
            }
        }
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof java.io.File) {
                    String stringContent = ((File) content).getAbsolutePath();
                    String replacedStringContent = stringContent.replace("/", "\\");
                    log(replacedStringContent.length());
                }
            }
        }
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        int devilBoxStringLength = 0;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof DevilBox) {
                    ((DevilBox) content).wakeUp();
                    ((DevilBox) content).allowMe();
                    ((DevilBox) content).open();
                    String text = "";
                    try {
                        text = ((DevilBox) content).getText();
                    } catch (DevilBoxTextNotFoundException msg) {
                        text = "";
                    }
                    devilBoxStringLength += text.length();
                }
            }
        }
        log(devilBoxStringLength);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> spaceList = colorBox.getSpaceList();
            for (BoxSpace space : spaceList) {
                Object content = space.getContent();
                if (content instanceof java.util.Map) {
                    log(content);
                }
            }
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
    }
}

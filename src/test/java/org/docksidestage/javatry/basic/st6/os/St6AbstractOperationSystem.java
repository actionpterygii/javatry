/*
 * Copyright 2019-2019 the original author or authors.
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
package org.docksidestage.javatry.basic.st6.os;

/**
 * @author jflute
 */

// アブストラクトたる抽象クラスはそのコンクリートたる具象クラスと同ディレクトリにいると紛らわしいものではあるが
// ことばの先頭に`Abstract`をつけてしまえば、`Ab`の部分がアルファベット昇順的に最強に近いので
// 自動ソート的に同ディレクトリにいてもいいよねって感じではあるしよくそうなっているよね
// 分ける場合は`base`って名前がよく使われるしいいね

//もとは普通クラスのSt6OperationSystem
public abstract class St6AbstractOperationSystem {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    //    private static final String OS_TYPE_MAC = "Mac";
    //    private static final String OS_TYPE_WINDOWS = "Windows";
    //    private static final String OS_TYPE_OLD_WINDOWS = "OldWindows";

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========

    // 
    protected final String loginId;
    //    private String osType;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6AbstractOperationSystem(String loginId) {
        this.loginId = loginId;
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    protected abstract String getFileSeparator();

    //    protected String getFileSeparator() {
    //        if (OS_TYPE_MAC.equalsIgnoreCase(osType)) {
    //            return "/";
    //        } else if (OS_TYPE_WINDOWS.equalsIgnoreCase(osType)) {
    //            return "\\";
    //        } else if (OS_TYPE_OLD_WINDOWS.equalsIgnoreCase(osType)) {
    //            return "\\";
    //        } else {
    //            throw new IllegalStateException("Unknown osType: " + osType);
    //        }
    //    }

    // FileSeparatorでWindowsもOldWindowsも同じなのは共通化してもよいかんじはするが
    // (Windowsだからという共通点がある)

    // UserDirectoryでMacもWindowsも同じなのは共通化は微妙そう
    // (たまたま一緒になっている感が強い)

    // とはいえwindowsのパスもかわるかもと考えるとそんなに共通化する必要もあるかなーってかんじ

    protected abstract String getUserDirectory();
    //    protected String getUserDirectory() {
    //        if (OS_TYPE_MAC.equalsIgnoreCase(osType)) {
    //            return "/Users/" + loginId;
    //        } else if (OS_TYPE_WINDOWS.equalsIgnoreCase(osType)) {
    //            return "/Users/" + loginId;
    //        } else if (OS_TYPE_OLD_WINDOWS.equalsIgnoreCase(osType)) {
    //            return "/Documents and Settigs/" + loginId;
    //        } else {
    //            throw new IllegalStateException("Unknown osType: " + osType);
    //        }
    //    }
}

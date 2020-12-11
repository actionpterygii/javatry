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
package org.docksidestage.javatry.basic.st6.dbms;

import org.docksidestage.javatry.basic.st6.dbms.base.AbstractDbms;

/**
 * @author jflute
 */
//public class St6MySql {
//
//    public String buildPagingQuery(int pageSize, int pageNumber) {
//        int offset = pageSize * (pageNumber - 1);
//        return "limit " + offset + ", " + pageSize;
//    }
//}

public class St6MySql extends AbstractDbms {

    // この`@Override`はJavaでは必須ではないがコンパイルの際にスペルミスのときとかにエラーを出してくれるオプション的なものになる
    @Override
    protected String first() {
        return "limit";
    }

    @Override
    protected String seconds() {
        return ", ";
    }

}

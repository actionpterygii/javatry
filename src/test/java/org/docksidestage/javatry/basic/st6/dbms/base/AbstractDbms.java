package org.docksidestage.javatry.basic.st6.dbms.base;

public abstract class AbstractDbms {

    //    とりあえず名前はあれですけど、firstとsecondsの文字をコンクリートクラスで実装させることを
    //    強制させたかったのでこのようにしています
    //    ちなみにプロパティに`abstract`を付けるとかそういうものはないのです(実装をアレするためのものなので)
    protected abstract String first();

    protected abstract String seconds();

    public String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return first() + offset + seconds() + pageSize;
    }
}

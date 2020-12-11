package org.docksidestage.javatry.basic.st6.os;

public class St6OldWindows extends St6AbstractOperationSystem {

    public St6OldWindows(String loginId) {
        super(loginId);
    }

    @Override
    protected String getFileSeparator() {
        return "\\";
    }

    @Override
    protected String getUserDirectory() {
        return "/Documents and Settigs/" + loginId;
    }

}

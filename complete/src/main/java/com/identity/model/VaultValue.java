package com.identity.model;

import java.util.Date;

public class VaultValue {
    private String identity;
    private String fakeSsn;
    private Date endTimeStamp;

    public VaultValue(String identity, String fakeSsn, Date endTimeStamp) {
        this.identity = identity;
        this.fakeSsn = fakeSsn;
        this.endTimeStamp = endTimeStamp;
    }
}

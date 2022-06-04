package com.tempter.edms_rest_api.util;

import com.tempter.edms_rest_api.entity.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignatureUtilityTest {

    protected static final String signatureHeader = "Signed";

    protected static String signatureDater() {
        return new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss").format(new Date());
    }

    protected static String signatureHash(Document document) {
        return String.valueOf(document.hashCode());
    }

    public static String getSignature(Document document) {
        return String.format("%s_%s@%s",signatureHeader,signatureHash(document),signatureDater());
    }
    @Test
    public void run() {
        System.out.println(getSignature(new Document()));
        System.out.println(getSignature(new Document()));
        Assertions.assertNotEquals(getSignature(new Document()),getSignature(new Document()));
    }

}

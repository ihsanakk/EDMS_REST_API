package com.tempter.edms_rest_api.util;

import com.tempter.edms_rest_api.entity.Document;
import com.tempter.edms_rest_api.entity.Signature;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;

public class SignatureUtility {

    protected static final String signatureHeader = "Signed";

    protected static String signatureDater(Signature signature) {
        return new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss").format(signature.getSignedAt());
    }

    protected static String signatureHash(Document document) {
        return document.hashCode() +"_"+ StringUtils.stripFilenameExtension(document.getDocumentName());
    }

    public static String createSignatureForDocument(Document document, Signature signature) {
        return String.format("%s_%s@%s", signatureHeader, signatureHash(document), signatureDater(signature));
    }

}
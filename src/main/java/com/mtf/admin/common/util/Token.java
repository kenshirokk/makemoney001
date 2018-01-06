package com.mtf.admin.common.util;

import com.mtf.admin.common.vo.AuthVO;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Token {

    public static String generate(AuthVO auth) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        byte [] key =  "fumingkaishidasb".getBytes();
        String dataStr = StringUtils.join(new String[]{
                auth.getUcode(),
                auth.getRole().toString(),
                String.valueOf(auth.getAuthTime().getTime())},"@");
        byte [] data = dataStr.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] res = mac.doFinal(data);
        String resStr =  Base64.getEncoder().encodeToString(res);
        return URLEncoder.encode(resStr,"UTF-8");
    }
}

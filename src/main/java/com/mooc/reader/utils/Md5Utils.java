package com.mooc.reader.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {
    public final static String md5Digest(String source, Integer salt) {
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + salt);
        }
        String target = new String(chars);
        return DigestUtils.md5Hex(target);
    }
}

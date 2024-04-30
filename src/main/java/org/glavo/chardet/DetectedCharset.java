/*
 * Copyright 2024 Glavo
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 */
package org.glavo.chardet;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @author Glavo
 */
public final class DetectedCharset {

    public static final DetectedCharset US_ASCII = new DetectedCharset("US-ASCII", StandardCharsets.US_ASCII);
    public static final DetectedCharset UTF_8 = new DetectedCharset("UTF-8", StandardCharsets.UTF_8);
    public static final DetectedCharset UTF_16LE = new DetectedCharset("UTF-16LE", StandardCharsets.UTF_16LE);
    public static final DetectedCharset UTF_16BE = new DetectedCharset("UTF-16BE", StandardCharsets.UTF_16BE);
    public static final DetectedCharset UTF_32LE = new DetectedCharset("UTF-32LE");
    public static final DetectedCharset UTF_32BE = new DetectedCharset("UTF-32BE");

    public static final DetectedCharset ISO_2022_JP = new DetectedCharset("ISO-2022-JP");
    public static final DetectedCharset ISO_2022_CN = new DetectedCharset("ISO-2022-CN");
    public static final DetectedCharset ISO_2022_KR = new DetectedCharset("ISO-2022-KR");
    public static final DetectedCharset ISO_8859_5 = new DetectedCharset("ISO-8859-5");
    public static final DetectedCharset ISO_8859_7 = new DetectedCharset("ISO-8859-7");
    public static final DetectedCharset ISO_8859_8 = new DetectedCharset("ISO-8859-8");
    public static final DetectedCharset BIG5 = new DetectedCharset("BIG5");
    public static final DetectedCharset GB18030 = new DetectedCharset("GB18030");
    public static final DetectedCharset EUC_JP = new DetectedCharset("EUC-JP");
    public static final DetectedCharset EUC_KR = new DetectedCharset("EUC-KR");
    public static final DetectedCharset EUC_TW = new DetectedCharset("EUC-TW");
    public static final DetectedCharset SHIFT_JIS = new DetectedCharset("Shift_JIS");
    public static final DetectedCharset IBM855 = new DetectedCharset("IBM855");
    public static final DetectedCharset IBM866 = new DetectedCharset("IBM866");
    public static final DetectedCharset KOI8_R = new DetectedCharset("KOI8-R");
    public static final DetectedCharset MAC_CYRILLIC = new DetectedCharset("MacCyrillic");
    public static final DetectedCharset WINDOWS_1251 = new DetectedCharset("windows-1251");
    public static final DetectedCharset WINDOWS_1252 = new DetectedCharset("windows-1252");
    public static final DetectedCharset WINDOWS_1253 = new DetectedCharset("windows-1253");
    public static final DetectedCharset WINDOWS_1255 = new DetectedCharset("windows-1255");
    public static final DetectedCharset TIS620 = new DetectedCharset("TIS-620");

    // WARNING: Listed below are charsets, which Java does not support.
    public static final DetectedCharset HZ_GB_2312 = new DetectedCharset("HZ-GB-2312", null); // Simplified Chinese
    public static final DetectedCharset X_ISO_10646_UCS_4_3412 = new DetectedCharset("X-ISO-10646-UCS-4-3412", null); // Malformed UTF-32
    public static final DetectedCharset X_ISO_10646_UCS_4_2143 = new DetectedCharset("X-ISO-10646-UCS-4-2143", null); // Malformed UTF-32

    private final String name;
    private Charset charset;
    private boolean needInit;

    private DetectedCharset(String name) {
        this.name = name;
        this.needInit = true;
    }

    private DetectedCharset(String name, Charset charset) {
        this.name = name;
        this.charset = charset;
        this.needInit = false;
    }

    public String getName() {
        return name;
    }

    public Charset getCharset() throws UnsupportedCharsetException {
        Charset charset = getCharsetOrNull();
        if (charset == null) {
            throw new UnsupportedCharsetException(name);
        }
        return charset;
    }

    public Charset getCharsetOrNull() {
        if (needInit) {
            try {
                charset = Charset.forName(name);
            } catch (Throwable ignored) {
            }

            needInit = false;
        }
        return charset;
    }

    public boolean isSupported() {
        return getCharsetOrNull() != null;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetectedCharset)) {
            return false;
        }
        return name.equals(((DetectedCharset) o).name);
    }

    @Override
    public String toString() {
        return name;
    }
}

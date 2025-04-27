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
 * Represents the detected encoding.
 * <p>
 * Some encodings may not be supported by the current environment,
 * so calling the {@linkplain #getCharset()} on them will throw a {@linkplain UnsupportedCharsetException}.
 * You can check whether it is supported by the current environment through {@linkplain #isSupported()}.
 *
 * @author Glavo
 */
public enum DetectedCharset {

    //
    // The following encodings are supported by all environments.
    //

    US_ASCII("US-ASCII", StandardCharsets.US_ASCII),
    UTF_8("UTF-8", StandardCharsets.UTF_8),
    UTF_16LE("UTF-16LE", StandardCharsets.UTF_16LE),
    UTF_16BE("UTF-16BE", StandardCharsets.UTF_16BE),
    UTF_32LE("UTF-32LE"),
    UTF_32BE("UTF-32BE"),

    //
    // The following encodings are optional and some environments may not support them
    //

    ISO_2022_JP("ISO-2022-JP"),
    ISO_2022_CN("ISO-2022-CN"),
    ISO_2022_KR("ISO-2022-KR"),
    ISO_8859_5("ISO-8859-5"),
    ISO_8859_7("ISO-8859-7"),
    ISO_8859_8("ISO-8859-8"),
    BIG5("Big5"),
    GB18030("GB18030"),
    EUC_JP("EUC-JP"),
    EUC_KR("EUC-KR"),
    EUC_TW("EUC-TW"),
    SHIFT_JIS("Shift_JIS"),
    IBM855("IBM855"),
    IBM866("IBM866"),
    KOI8_R("KOI8-R"),
    MAC_CYRILLIC("MacCyrillic"),
    WINDOWS_1251("windows-1251"),
    WINDOWS_1252("windows-1252"),
    WINDOWS_1253("windows-1253"),
    WINDOWS_1255("windows-1255"),
    TIS620("TIS-620"),

    //
    // The following encodings are not supported by Java.
    //

    HZ_GB_2312("HZ-GB-2312", null), // Simplified Chinese
    X_ISO_10646_UCS_4_3412("X-ISO-10646-UCS-4-3412", null), // Malformed UTF-32
    X_ISO_10646_UCS_4_2143("X-ISO-10646-UCS-4-2143", null); // Malformed UTF-32

    private final String name;
    private Charset charset;
    private boolean needInit;

    DetectedCharset(String name) {
        this.name = name;
        this.needInit = true;
    }

    DetectedCharset(String name, Charset charset) {
        this.name = name;
        this.charset = charset;
        this.needInit = false;
    }

    public String getName() {
        return name;
    }

    private void initCharset() {
        if (needInit) {
            try {
                charset = Charset.forName(name);
            } catch (Throwable ignored) {
            }

            needInit = false;
        }
    }

    public Charset getCharset() throws UnsupportedCharsetException {
        initCharset();
        if (charset == null) {
            throw new UnsupportedCharsetException(name);
        }
        return charset;
    }

    public Charset getCharset(Charset defaultCharset) {
        initCharset();
        return charset != null ? charset : defaultCharset;
    }

    public boolean isSupported() {
        initCharset();
        return charset != null;
    }

    @Override
    public String toString() {
        return name;
    }
}

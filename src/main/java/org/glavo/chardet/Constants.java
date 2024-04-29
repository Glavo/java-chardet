/*

(C) Copyright 2016-2017 Alberto Fernández <infjaf@gmail.com>
(C) Copyright 2007 Kohei TAKETA <k-tak@void.in> (Java port)

The contents of this file are subject to the Mozilla Public License Version
1.1 (the "License"); you may not use this file except in compliance with
the License. You may obtain a copy of the License at
http://www.mozilla.org/MPL/

Software distributed under the License is distributed on an "AS IS" basis,
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
for the specific language governing rights and limitations under the
License.

Alternatively, the contents of this file may be used under the terms of
either the GNU General Public License Version 2 or later (the "GPL"), or
the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
in which case the provisions of the GPL or the LGPL are applicable instead
of those above. If you wish to allow use of your version of this file only
under the terms of either the GPL or the LGPL, and not to allow others to
use your version of this file under the terms of the MPL, indicate your
decision by deleting the provisions above and replace them with the notice
and other provisions required by the GPL or the LGPL. If you do not delete
the provisions above, a recipient may use your version of this file under
the terms of any one of the MPL, the GPL or the LGPL.

*/

package org.glavo.chardet;

public final class Constants {
    public static final String CHARSET_ISO_2022_JP  = "ISO-2022-JP".intern();
    public static final String CHARSET_ISO_2022_CN  = "ISO-2022-CN".intern();
    public static final String CHARSET_ISO_2022_KR  = "ISO-2022-KR".intern();
    public static final String CHARSET_ISO_8859_5   = "ISO-8859-5".intern();
    public static final String CHARSET_ISO_8859_7   = "ISO-8859-7".intern();
    public static final String CHARSET_ISO_8859_8   = "ISO-8859-8".intern();
    public static final String CHARSET_BIG5         = "BIG5".intern();
    public static final String CHARSET_GB18030      = "GB18030".intern();
    public static final String CHARSET_EUC_JP       = "EUC-JP".intern();
    public static final String CHARSET_EUC_KR       = "EUC-KR".intern();
    public static final String CHARSET_EUC_TW       = "EUC-TW".intern();
    public static final String CHARSET_SHIFT_JIS    = "SHIFT_JIS".intern();
    public static final String CHARSET_IBM855       = "IBM855".intern();
    public static final String CHARSET_IBM866       = "IBM866".intern();
    public static final String CHARSET_KOI8_R       = "KOI8-R".intern();
    public static final String CHARSET_MACCYRILLIC  = "MACCYRILLIC".intern();
    public static final String CHARSET_WINDOWS_1251 = "WINDOWS-1251".intern();
    public static final String CHARSET_WINDOWS_1252 = "WINDOWS-1252".intern();
    public static final String CHARSET_WINDOWS_1253 = "WINDOWS-1253".intern();
    public static final String CHARSET_WINDOWS_1255 = "WINDOWS-1255".intern();
    public static final String CHARSET_UTF_8        = "UTF-8".intern();
    public static final String CHARSET_UTF_16BE     = "UTF-16BE".intern();
    public static final String CHARSET_UTF_16LE     = "UTF-16LE".intern();
    public static final String CHARSET_UTF_32BE     = "UTF-32BE".intern();
    public static final String CHARSET_UTF_32LE     = "UTF-32LE".intern();
    public static final String CHARSET_TIS620       = "TIS620".intern();
    
    /**
     * @deprecated Mispelled, you shoud use {@link #CHARSET_US_ASCII}
     */
    @Deprecated
    public static final String CHARSET_US_ASCCI     = "US-ASCII".intern();
    public static final String CHARSET_US_ASCII     = "US-ASCII".intern();
    
    public static final String CHARSET_GBK          = "GBK".intern();
    
    
    // WARNING: Listed below are charsets which Java does not support.
    public static final String CHARSET_HZ_GB_2312   = "HZ-GB-2312".intern(); // Simplified Chinese
    public static final String CHARSET_X_ISO_10646_UCS_4_3412 = "X-ISO-10646-UCS-4-3412".intern(); // Malformed UTF-32
    public static final String CHARSET_X_ISO_10646_UCS_4_2143 = "X-ISO-10646-UCS-4-2143".intern(); // Malformed UTF-32
}

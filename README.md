# Java Chardet

[![Latest release](https://img.shields.io/maven-central/v/org.glavo/chardet)](https://github.com/Glavo/java-chardet/releases/latest)

This library is a fork of [albfernandez/juniversalchardet](https://github.com/albfernandez/juniversalchardet),
based on commit [5a50f8a](https://github.com/albfernandez/juniversalchardet/commit/5a50f8a067d51a9a77185b31aa2efa26280020ad).

The purpose of this library is to detect the encoding of unknown encoded text.

Note: This library is in beta stage, and there may be breaking changes to the API in the future.

## Differences from upstream

The main difference between this library and the upstream (and my motivation for creating this fork)
is that all APIs are based on `ByteBuffer` instead of `byte[]`, so this library can directly handle off-heap memory.

Of course, I've also provided `byte[]` based shorthands for these APIs, so working with `byte[]` isn't any more cumbersome.

In addition, I also did some cleaning up of the library.
The more important difference is that this library no longer uses `String` to represent encoding,
instead [DetectedCharset](src/main/java/org/glavo/chardet/DetectedCharset.java) is used.
You can convert DetectedCharset to Java `java.nio.charset.Charset` easily:

```java
DetectedCharset result = UniversalDetector.detectCharset(Paths.get("testfile.txt"));
Charset charset = result != null ? result.getCharset() : StandardCharsets.UTF_8;
```

The reason for not using `Charset` directly is that this library supports detection of some encodings that Java does not support (e.g. `HZ-GB-2312`).

There are some other minor cleanups and fixes to this library. I plan to submit some patches to upstream in the future.

## Adding the library to your build

Maven:
```xml
<dependency>
  <groupId>org.glavo</groupId>
  <artifactId>chardet</artifactId>
  <version>2.5.0</version>
</dependency>
```

Gradle:
```kotlin
implementation("org.glavo:chardet:2.5.0")
```

## License

The library is subject to the Mozilla Public License Version 1.1.

Alternatively, the library may be used under the terms of either the GNU General Public License Version 2 or later,
or the GNU Lesser General Public License 2.1 or later.


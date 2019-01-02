package com.frankc.hellomongo.shorturl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlGenerator {
    private final Logger logger =
                            LoggerFactory.getLogger(ShortUrlGenerator.class);

    private static final String[] BASE62_CHARSET = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d",
            "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z"
    };

    public String generateBase62() {
        return generateRandomForCharset(BASE62_CHARSET);
    }

    public String generateRandomForCharset(final String[] charset) {
        UUID uuid = UUID.randomUUID();

        return longToBaseXString(uuid.getLeastSignificantBits(), charset)
               + longToBaseXString(uuid.getLeastSignificantBits(), charset);
    }

    private String longToBaseXString(final long toConvert,
                                     final String[] charset) {

        logger.debug("Converting Long [" + toConvert + "]"
                     + " to base " + charset.length);

        long stillToConvert = Long.divideUnsigned(toConvert, charset.length);
        long modulus = Long.remainderUnsigned(toConvert, charset.length);

        logger.debug("stillToConvert: [" + stillToConvert + "], "
                     + "Adding index: [" + modulus + "], "
                     + "char: [" + charset[(int) modulus] + "]");

        if (stillToConvert > 0) {
            return longToBaseXString(stillToConvert, charset)
                   + charset[(int) modulus];
        } else {
            return charset[(int) modulus];
        }
    }
}

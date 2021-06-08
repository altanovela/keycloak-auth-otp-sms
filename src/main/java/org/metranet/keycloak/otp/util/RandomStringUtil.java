package org.metranet.keycloak.otp.util;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomStringUtil {
    
    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public static final String ALPHA_UPPER    = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String ALPHA_LOWER    = ALPHA_UPPER.toLowerCase(Locale.ROOT);

    public static final String NUMERIC        = "0123456789";

    public static final String ALPHA_NUMERIC  = ALPHA_UPPER + ALPHA_LOWER + NUMERIC;

    private final Random random;

    private final char[] symbols;

    private final char[] buf;

    public RandomStringUtil(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create a Random Symbol depend on user defined
     */
    public RandomStringUtil(int length, String symbol) {
        this(length, new SecureRandom(), symbol);
    }
    
    /**
     * Create an alphanumeric string generator.
     */
    public RandomStringUtil(int length, Random random) {
        this(length, random, ALPHA_NUMERIC);
    }
        
    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public RandomStringUtil(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public RandomStringUtil() {
        this(21);
    }

}
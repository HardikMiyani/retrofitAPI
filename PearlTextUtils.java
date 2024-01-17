package com.vmc.park.Utils;


import androidx.annotation.Nullable;



public class PearlTextUtils {

    @Nullable
    public static String nullIfBlank(@Nullable String value) {
        if (isBlank(value)) {
            return null;
        }
        return value;
    }

    /**
     * A checker for whether or not the input value is entirely whitespace. This is slightly more
     * aggressive than the android TextUtils#isEmpty method, which only returns true for
     * {@code null} or {@code ""}.
     *
     * @param value a possibly blank input string value
     * @return {@code true} if and only if the value is all whitespace, {@code null}, or empty
     */
    public static boolean isBlank(@Nullable String value) {
        return value == null || value.trim().length() == 0;
    }

}

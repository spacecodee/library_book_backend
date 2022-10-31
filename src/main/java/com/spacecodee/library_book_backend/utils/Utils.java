package com.spacecodee.library_book_backend.utils;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNotEqualsName(String nameFromDto, String name) {
        return !nameFromDto.equalsIgnoreCase(name);
    }

}

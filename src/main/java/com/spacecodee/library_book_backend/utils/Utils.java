package com.spacecodee.library_book_backend.utils;

import com.spacecodee.library_book_backend.enums.RolNameEnum;
import lombok.Getter;

public class Utils {
    @Getter
    private static final String[] ROLES_STRING = {"user", "admin", "student"};
    @Getter
    private static final RolNameEnum[] ROL_NAME_ENUMS = {RolNameEnum.ROLE_USER, RolNameEnum.ROLE_ADMIN,
            RolNameEnum.ROLE_STUDENT};

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNotEqualsName(String nameFromDto, String name) {
        return !nameFromDto.equalsIgnoreCase(name);
    }


}

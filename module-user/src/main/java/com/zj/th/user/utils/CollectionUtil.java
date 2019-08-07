package com.zj.th.user.utils;

import java.util.Collection;

/**
 *
 */

public class CollectionUtil {

    public static boolean isEmpty(Collection<?> list) {
        if (list == null) {
            return true;
        }
        return list.isEmpty();
    }
}

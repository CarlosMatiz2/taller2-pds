package co.com.poli.usersservice.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Functions {

    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection<?>) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }
}

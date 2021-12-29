package javaf.util;

public class Objects {
    public static <T> T nonNullOrElse(T value, T defaultValue) {
        if (value == null) return defaultValue;
        return value;
    }
}

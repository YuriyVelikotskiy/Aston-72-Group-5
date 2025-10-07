package appStarter;

import classBuilder.CashedClass;

import java.lang.reflect.Field;

public class ReflectionChangeObject {
    public static<T extends CashedClass> T reflectionChangeObject(T copyObject, String fieldName, String value) throws IllegalAccessException {
        Field[] fields = copyObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                if (field.getType().equals(int.class)) {
                    field.setAccessible(true);
                    field.set(copyObject, Integer.parseInt(value));
                } else {
                    field.setAccessible(true);
                    field.set(copyObject, value);
                }
            }
        }
        return copyObject;
    }
}

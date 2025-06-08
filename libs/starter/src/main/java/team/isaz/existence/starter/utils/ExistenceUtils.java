package team.isaz.existence.starter.utils;

import lombok.experimental.UtilityClass;
import team.isaz.existence.starter.config.StaticBeans;

/**
 * Static facade for the configured {@link team.isaz.existence.core.model.interfaces.ExistenceChecker}.
 */
@UtilityClass
public class ExistenceUtils {

    /**
     * Check if the object is absent
     *
     * @param o {@link Object} for check
     * @return {@code true} if object is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    public static boolean absent(Object o){
        return StaticBeans.existenceChecker().absent(o);
    }

    /**
     * Check if any of the objects is absent
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if at least one of objects is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    public static boolean anyAbsent(Object... os) {
        return StaticBeans.existenceChecker().anyAbsent(os);
    }

    /**
     * Check if all objects are absent
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if all objects is 'absent' (null, empty, e.t.c.) and {@code false} otherwise
     */
    public static boolean allAbsent(Object... os) {
        return StaticBeans.existenceChecker().allAbsent(os);
    }

    /**
     * Check if the object is existing
     *
     * @param o {@link Object} for check
     * @return {@code true} if an object is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    public static boolean exists(Object o) {
        return !absent(o);
    }

    /**
     * Check if any of objects is existing
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if at least one of objects is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    public static boolean anyExists(Object... os) {
        return !allAbsent(os);
    }

    /**
     * Check if all objects are existing
     *
     * @param os Multiple {@link Object} for check
     * @return {@code true} if all objects is 'exists' (not null, not empty, e.t.c.) and {@code false} otherwise
     */
    public static boolean allExists(Object... os) {
        return !anyAbsent(os);
    }
}

@file:JvmName("Utils")

package kondorcet.utils

/** Returns the value or 0, if null */
internal fun Int?.orZero(): Int = this ?: 0

/** Returns the maximum value */
internal fun <T : Comparable<T>> max(value: T, vararg values: T): T = (values.toList() + value).max()!!

/** Returns the minimum value */
internal fun <T : Comparable<T>> min(value: T, vararg values: T): T = (values.toList() + value).min()!!
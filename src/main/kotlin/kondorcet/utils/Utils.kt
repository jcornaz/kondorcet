package kondorcet.utils

/** Returns the value or 0, if null */
fun Int?.orZero(): Int = this ?: 0

/** Returns the maximum value */
fun <T : Comparable<T>> max(value: T, vararg values: T): T = (values.toList() + value).max()!!

/** Returns the minimum value */
fun <T : Comparable<T>> min(value: T, vararg values: T): T = (values.toList() + value).min()!!
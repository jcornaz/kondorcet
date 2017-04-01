package kondorcet

fun Int?.orZero(): Int = this ?: 0

fun <T : Comparable<T>> max(value: T, vararg values: T): T = (values.toList() + value).max()!!
fun <T : Comparable<T>> min(value: T, vararg values: T): T = (values.toList() + value).min()!!
package com.toolable.condorcet

import com.google.common.collect.Table

operator fun <R, C, V> Table<R, C, V>.set(row: R, column: C, value: V) = put(row, column, value)

package kondorcet.graph

import kable.Table
import kable.emptyTable
import kable.minusColumn
import kable.minusRow

/**
 * Simple implementation of a [Graph]
 */
internal class SimpleGraph<V : Any, out W : Any>(
        override val vertices: Set<V> = emptySet(),
        edges: Table<V, V, W> = emptyTable()
) : Graph<V, W> {

    override val edges = (edges.rows - vertices).fold(edges) { table, row -> table.minusRow(row) }.let {
        (edges.columns - vertices).fold(it) { table, column -> table.minusColumn(column) }
    }

    override fun toString() = "${javaClass.simpleName}{vertices=$vertices, edges=$edges}"
}
package kondorcet.graph

import kable.Table

interface Graph<V : Any, out W : Any> {
    val vertices: Set<V>
    val edges: Table<V, V, W>

    operator fun contains(vertex: V): Boolean = vertex in vertices
    operator fun contains(edge: Pair<V, V>): Boolean = edges.contains(edge.first, edge.second)

    operator fun get(source: V, target: V): W? = edges[source, target]

    fun getEdgesFrom(source: V): Map<V, W> = edges.getRow(source)
    fun getEdgesTo(target: V): Map<V, W> = edges.getColumn(target)

    fun getDegreeFrom(source: V): Int = getEdgesFrom(source).size
    fun getDegreeTo(target: V): Int = getEdgesTo(target).size
}
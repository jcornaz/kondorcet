package kondorcet.graph

import kable.Table
import kable.count

/**
 * Directed and weighted graph
 *
 * @param V Type of vertices
 * @param W Type of weights
 */
internal interface Graph<V : Any, out W : Any> {

    /** Vertices of the graph */
    val vertices: Set<V>

    /** Adjacency matrix */
    val edges: Table<V, V, W>

    /** Returns true if, and only if, the graph contains the given [vertex] */
    operator fun contains(vertex: V): Boolean = vertex in vertices

    /** Returns true if, and only if, the graphs contains the given [edge] */
    operator fun contains(edge: Pair<V, V>): Boolean = edges.contains(edge.first, edge.second)

    /**
     * Return the weight of the edge, or null if there is no such edge
     *
     * @param source Source vertex of the edge
     * @param target Target vertex of the edge
     */
    operator fun get(source: V, target: V): W? = edges[source, target]

    /** Returns the edges having the [source] vertex */
    fun getEdgesFrom(source: V): Map<V, W> = edges.getRow(source)

    /** Returns the edges having the [target] vertex */
    fun getEdgesTo(target: V): Map<V, W> = edges.getColumn(target)

    /** Returns the number of edges having the [source] vertex */
    fun getDegreeFrom(source: V): Int = getEdgesFrom(source).size

    /** Returns the number of edges having the [vertex] as it source or target */
    fun getDegreeOf(vertex: V): Int = edges.count { (row, column, _) -> vertex == row || vertex == column }

    /** Returns the number of edges having the [target] vertex */
    fun getDegreeTo(target: V): Int = getEdgesTo(target).size
}
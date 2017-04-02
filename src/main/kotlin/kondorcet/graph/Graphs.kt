package kondorcet.graph

import kable.entry
import kable.minus
import kable.plus

data class Edge<out V : Any, out W : Any>(val source: V, val target: V, val weight: W)

fun <V : Any, W : Any> emptyGraph(): Graph<V, W> = SimpleGraph()

operator fun <V : Any, W : Any> Graph<V, W>.plus(vertex: V): Graph<V, W> =
        SimpleGraph(vertices + vertex, edges)

operator fun <V : Any, W : Any> Graph<V, W>.plus(vertices: Collection<V>): Graph<V, W> =
        SimpleGraph(this.vertices + vertices, edges)

operator fun <V : Any, W : Any> Graph<V, W>.plus(edge: Edge<V, W>): Graph<V, W> =
        SimpleGraph(vertices + edge.source + edge.target, edges + entry(edge.source, edge.target, edge.weight))

operator fun <V : Any, W : Any> Graph<V, W>.minus(vertex: V): Graph<V, W> =
        SimpleGraph(vertices - vertex, edges)

operator fun <V : Any, W : Any> Graph<V, W>.minus(edge: Pair<V, V>): Graph<V, W> =
        SimpleGraph(vertices, edges - edge)
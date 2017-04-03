package kondorcet.graph

/** Edge of a [Graph]*/
internal data class Edge<out V : Any, out W : Any>(val source: V, val target: V, val weight: W)
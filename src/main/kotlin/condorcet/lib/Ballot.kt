package condorcet.lib


data class Ballot<T : Any>(val orderedCandidates: List<List<T>>) {

    companion object {
        fun <T : Any> blank() = Ballot<T>(emptyList())
        fun <T : Any> of(candidates: List<T>) = Ballot(candidates.map { listOf(it) })
        fun <T : Any> of(vararg candidates: T) = of(candidates.toList())
    }

    val candidates by lazy { orderedCandidates.fold(emptyList<T>()) { lst, elt -> lst + elt } }

    operator fun plus(candidates: List<T>) = Ballot(orderedCandidates.plus<List<T>>(candidates.toList()))
    operator fun plus(candidate: T) = plus(listOf(candidate))
}
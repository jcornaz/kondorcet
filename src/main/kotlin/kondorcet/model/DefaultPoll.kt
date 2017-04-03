package kondorcet.model

import kondorcet.Ballot
import kondorcet.Poll

/** Default implementation of [Poll] */
data class DefaultPoll<T : Any>(override val ballots: Map<Ballot<T>, Int> = emptyMap()) : Poll<T> {
    override val candidates by lazy { super.candidates }
}

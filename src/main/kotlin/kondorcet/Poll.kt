package kondorcet

/**
 * A poll is a collection of ballots with the quantity of voter for each one
 */
interface Poll<T : Any> {

    /** Map of vote count by ballots */
    val ballots: Map<Ballot<T>, Int>

    /** Candidates */
    val candidates: Set<T>
        get() = ballots.keys.fold(emptySet<T>()) { set, ballot -> set + ballot.candidates }
}
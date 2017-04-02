#Change log
## 1.0-SNAPSHOT
### Added
* The extension function `Ballot.with` create an copy of the ballot wich contains all given candidates.

### Changed
* Dependency to the library "kraft" (graph utilities) has been removed and replaced by a simple implementation of a graph

## 1.0-rc2
### Added
* Winner(s) properties to the `Ballot` interface
* Polls
    * `SimplePoll` simply store ballots
    * `CheckedPoll` check the validity of the ballots
* Relative majority method

### Changed
* The `Ballot` is now an interface and a `DefaultBallot` class has been added
* Ballots are now created by function call : `ballot(c1, c2, c3, c4)` or `ballot(setOf(c1, c2), setOf(c3, c4)`
* The `VoteMethod` has been created to separate it from the poll. It can be specified when calling `Poll.result`

## Fixed
* The method `Ballot.hasDuplicates` result was the inversed

## 1.0-rc1
### Added
* Condorcet method
* Schulze method

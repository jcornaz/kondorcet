#Change log
## 1.0-SNAPSHOT
### Added
* The extension function `Ballot.with` create an copy of the ballot which contains all given candidates.

### Changed
* [API-BREAK] `Poll` is now immutable
* [API-BREAK] Polls and Ballots implementations has been moved to the package `kondorcet.model`
* [API-BREAK] `Ballot` cannot be invalid anymore
* [API-BREAK] `Poll.result` has been renamed to `Poll.getResult`

### Removed
* [API-BREAK] `CheckedPoll` has been removed because no longer necessary due to changes of poll and ballots (see above)

### Fixed
* Consider missing candidates in ballots as abstentions for candidates pairwise matches.

### Dependencies changes
#### Added
* [Kable](https://github.com/slimaku/kable) 1.1.0

#### Updated
* [Kotlin](https://kotlinlang.org/) from 1.0.4 to 1.1.1

#### Removed
* [Kraft](https://github.com/slimaku/kraft) 0.1.2

## 1.0-rc2
### Added
* Winner(s) properties to the `Ballot` interface
* Polls
    * `SimplePoll` simply store ballots
    * `CheckedPoll` check the validity of the ballots
* Relative majority method

### Changed
* [API-BREAK] The `Ballot` is now an interface and a `DefaultBallot` class has been added
* [API-BREAK] Ballots are now created by function call : `ballot(c1, c2, c3, c4)` or `ballot(setOf(c1, c2), setOf(c3, c4)`
* [API-BREAK] The `VoteMethod` has been created to separate it from the poll. It can be specified when calling `Poll.getResult`

## Fixed
* The method `Ballot.hasDuplicates` getResult was the inversed

## 1.0-rc1
### Added
* Condorcet method
* Schulze method

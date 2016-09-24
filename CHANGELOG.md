#Change log
## 1.0-SNAPSHOT
### Added
* Add `winners` and `winner` properties to `Ballot`

### Changed
* The `Ballot` is now an interface and a `DefaultBallot` class has been added
* Now the way to create a ballot is `ballot(c1, c2, c3, c4)` or `ballot(setOf(c1, c2), setOf(c3, c4)`
    (Instead of `Ballot.of(c1, c2, c3)`)

## Fixed
* The method `Ballot.hasDuplicates` result was the inversed

## 1.0-rc1
### Added
* Condorcet method
* Schulze method

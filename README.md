# Kondorcet
[![Build Status](https://travis-ci.org/slimaku/kondorcet.svg?branch=master)](https://travis-ci.org/slimaku/kondorcet)
[![Code covertage](https://codecov.io/gh/slimaku/kondorcet/branch/master/graph/badge.svg)](https://codecov.io/gh/slimaku/kondorcet)
[![JitPack](https://jitpack.io/v/slimaku/kondorcet.svg)](https://jitpack.io/#slimaku/kondorcet)

Kotlin library for [voting systems](https://en.wikipedia.org/wiki/Voting_system), with a strong accent on the [Condorcet method](https://en.wikipedia.org/wiki/Condorcet_method) and it's derivatives.

## Usage exemple
```kotlin
// Create a poll that ensure the validity of the ballots
val poll = CheckedPoll<Char>()

// Add ballots to the poll
// Theses ballot contains the candidates ordered by prefernces
poll.vote(ballot('A', 'C', 'B', 'E', 'D'), 5)
poll.vote(ballot('A', 'D', 'E', 'C', 'B'), 5)
poll.vote(ballot('B', 'E', 'D', 'A', 'C'), 8)
poll.vote(ballot('C', 'A', 'B', 'E', 'D'), 3)
poll.vote(ballot('C', 'A', 'E', 'B', 'D'), 7)
poll.vote(ballot('C', 'B', 'A', 'D', 'E'), 2)
poll.vote(ballot('D', 'C', 'E', 'B', 'A'), 7)
poll.vote(ballot('E', 'B', 'A', 'D', 'C'), 8)

// Compute and return the result
// The result is an instance of Ballot wich contains the candidates ordered from the winners to the losers
val schulze = poll.result() // Use the Schulze method my default

// Eventualy get the result of other voting methods
val condorcet = poll.result(CondorcetMethod)
val relativeMajority = poll.result(RelativeMajority)
```

Depending on the given ballots and the used method, there can be status quo between some candidates.
In such case `poll.result()` would return something like `[[A, B], [C]]` which would mean : A and B are ex aequo, but both win agains C.

### Currently supported methods
* [Schulze](https://en.wikipedia.org/wiki/Schulze_method)
* [Condorcet](https://en.wikipedia.org/wiki/Condorcet_method)
* [Relative majority](https://en.wikipedia.org/wiki/Plurality_(voting)#Majority_versus_plurality)

## Add the library to your project
You have to use java 6 or newer

With [Gradle](https://gradle.org) :
```gradle
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.slimaku:kondorcet:v1.0-rc2'
}
```

You can also use [Jitpack](https://jitpack.io/#slimaku/kraft) with maven, sbt or leiningen.

## Documentation
* [Base API](https://slimaku.github.io/kondorcet/doc/1.0/kondorcet/kondorcet/index.html)
* [Vote methods](https://slimaku.github.io/kondorcet/doc/1.0/kondorcet/kondorcet.method/index.html)

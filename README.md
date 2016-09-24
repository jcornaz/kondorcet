# Kondorcet
[![Build Status](https://travis-ci.org/slimaku/kondorcet.svg?branch=master)](https://travis-ci.org/slimaku/kondorcet)
[![Code covertage](https://codecov.io/gh/slimaku/kondorcet/branch/master/graph/badge.svg)](https://codecov.io/gh/slimaku/kondorcet)
[![JitPack](https://jitpack.io/v/slimaku/kondorcet.svg)](https://jitpack.io/#slimaku/kondorcet)

This is a Kotlin library to manipulate vote polls using the [Condorcet method](https://en.wikipedia.org/wiki/Condorcet_method)

## Usage exemple
```kotlin
// Create a poll that will be solved using the Schulze method 
// See https://en.wikipedia.org/wiki/Schulze_method
val poll = SchulzeMethod<Char>()

// Add ballots to the poll
// Theses ballot contains the candidates ordered by prefernces
poll.vote(Ballot.of('A', 'C', 'B', 'E', 'D'), 5)
poll.vote(Ballot.of('A', 'D', 'E', 'C', 'B'), 5)
poll.vote(Ballot.of('B', 'E', 'D', 'A', 'C'), 8)
poll.vote(Ballot.of('C', 'A', 'B', 'E', 'D'), 3)
poll.vote(Ballot.of('C', 'A', 'E', 'B', 'D'), 7)
poll.vote(Ballot.of('C', 'B', 'A', 'D', 'E'), 2)
poll.vote(Ballot.of('D', 'C', 'E', 'B', 'A'), 7)
poll.vote(Ballot.of('E', 'B', 'A', 'D', 'C'), 8)

// Compute and return the result
// The result is an instance of Ballot wich contains the candidates ordered from the winners to the losers
val result = poll.result()

println(result.orderedCandidates) // will print "[[E], [A], [C], [B], [D]]" (E is the winner)
```

Depending on the given ballots and the used method, there can be ex aequo between the candidates.
In such case `poll.result()` would return something like "[[A, B], [C]]" which would mean : A and B are ex aequo, but both win agains C.

## Add the library to your project
You need a JDK 6 or newer

With [Gradle](https://gradle.org) :
```gradle
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.slimaku:kondorcet:v1.0-rc1'
}
```

You can also use [Jitpack](https://jitpack.io/#slimaku/kraft) with maven, sbt or leiningen.

## Documentation
[KDoc](https://slimaku.github.io/kondorcet/doc/1.0/kondorcet/kondorcet/index.html)

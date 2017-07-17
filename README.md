# Kondorcet
[![GPLv3](https://img.shields.io/badge/license-LGPLv3-blue.svg)](https://raw.githubusercontent.com/jcornaz/kondorcet/master/LICENSE)
[![JitPack](https://jitpack.io/v/jcornaz/kondorcet.svg)](https://jitpack.io/#jcornaz/kondorcet)
[![Build Status](https://travis-ci.org/jcornaz/kondorcet.svg?branch=master)](https://travis-ci.org/jcornaz/kondorcet)
[![Code covertage](https://codecov.io/gh/jcornaz/kondorcet/branch/master/graph/badge.svg)](https://codecov.io/gh/jcornaz/kondorcet)
[![Codebeat](https://codebeat.co/badges/421770ad-085c-480e-97eb-b46b30234a5e)](https://codebeat.co/projects/github-com-jcornaz-kondorcet)
[![Issues](https://img.shields.io/github/issues/jcornaz/kondorcet.svg)](https://github.com/jcornaz/kondorcet/issues)
[![Pull requests](https://img.shields.io/github/issues-pr/jcornaz/kondorcet.svg)](https://github.com/jcornaz/kondorcet/pulls)

Kotlin library for [voting systems](https://en.wikipedia.org/wiki/Voting_system), with a strong accent on the [Condorcet method](https://en.wikipedia.org/wiki/Condorcet_method) and it's derivatives.

## Usage exemple
```kotlin
// Create a poll to store ballots
var poll = emptyPoll<Char>()

// Add ballots to the poll
// Theses ballot contains the candidates ordered by preferences
poll += ballot('A', 'C', 'B') to 23
poll += ballot('B', 'C', 'A') to 19
poll += ballot('C', 'B', 'A') to 16
poll += ballot('C', 'A', 'B')
poll += ballot('C', 'A', 'B')

// Compute and return the result
// The result is an instance of Ballot which contains the candidates ordered from the winners to the losers
val schulze = poll.getResult() // [[C], [B], [A]] (Use the Schulze method by default)

// Eventualy get the result of other voting methods
val condorcet = poll.getResult(CondorcetMethod)            // [[C], [B], [A]] (identical of the Schulze method in this case)
val relativeMajority = poll.getResult(RelativeMajority)    // [[A], [B], [C]]
```

Depending on the given ballots and the used method, there can be status quo between some candidates.
In such case `poll.result()` would return something like `[[A, B], [C]]` which would mean : A and B are ex aequo, but both win agains C.

### Currently supported methods
* [Schulze](https://en.wikipedia.org/wiki/Schulze_method)
* [Condorcet](https://en.wikipedia.org/wiki/Condorcet_method)
* [Relative majority](https://en.wikipedia.org/wiki/Plurality_(voting)#Majority_versus_plurality)

## Add the library to your project
You have to use java 8 or newer

With [Gradle](https://gradle.org) :
```gradle
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.jcornaz:kondorcet:v1.0.0'
}
```

You can also use [Jitpack](https://jitpack.io/#jcornaz/kraft) with maven, sbt or leiningen.

## Documentation
* [KDoc](https://jcornaz.github.io/kondorcet/doc/1.0/kondorcet/)

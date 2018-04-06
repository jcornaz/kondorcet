# Kondorcet
[![License](https://img.shields.io/badge/license-LGPL--3.0-blue.svg)](LICENSE)
[![Project status](https://img.shields.io/badge/status-stable-brightgreen.svg)](https://gist.githubusercontent.com/jcornaz/46736c3d1f21b4c929bd97549b7406b2/raw/ProjectStatusFlow)
[![JitPack](https://jitpack.io/v/jcornaz/kondorcet.svg)](https://jitpack.io/#jcornaz/kondorcet)
[![Build Status](https://travis-ci.org/jcornaz/kondorcet.svg?branch=master)](https://travis-ci.org/jcornaz/kondorcet)
[![Code coverage](https://codecov.io/gh/jcornaz/kondorcet/branch/master/graph/badge.svg)](https://codecov.io/gh/jcornaz/kondorcet)
[![Code quality](https://codebeat.co/badges/89ad94c4-1348-42ea-8211-73e28c36fc6c)](https://codebeat.co/projects/github-com-jcornaz-kondorcet-master)

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
In such case `poll.result()` would return something like `[[A, B], [C]]` which would mean : **A** and **B** are ex aequo, but both win agains **C**.

### Currently supported methods
* [Schulze](https://en.wikipedia.org/wiki/Schulze_method)
* [Condorcet](https://en.wikipedia.org/wiki/Condorcet_method)
* [Relative majority](https://en.wikipedia.org/wiki/Plurality_(voting)#Majority_versus_plurality)

### Incoming features
* [Ranked pair](https://en.wikipedia.org/wiki/Ranked_pairs)
* [Randomized condorcet](http://www.science4all.org/wp-content/uploads/RandomizedCondorcet.pdf)
* Non-blocking API with Kotlin Couroutines
* Multiplatform support (JS, Android and Native)

## Add the library to your project
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

You can also use [Jitpack](https://jitpack.io/#jcornaz/kondorcet) with maven, sbt or leiningen.

## Documentation
* [KDoc](https://jcornaz.github.io/kondorcet/doc/1.0/kondorcet/)

# Kondorcet
[![GPLv3](https://img.shields.io/badge/license-LGPLv3-blue.svg)](https://raw.githubusercontent.com/slimaku/kondorcet/master/LICENSE)
[![JitPack](https://jitpack.io/v/slimaku/kondorcet.svg)](https://jitpack.io/#slimaku/kondorcet)
[![Build Status](https://travis-ci.org/slimaku/kondorcet.svg?branch=master)](https://travis-ci.org/slimaku/kondorcet)
[![Code covertage](https://codecov.io/gh/slimaku/kondorcet/branch/master/graph/badge.svg)](https://codecov.io/gh/slimaku/kondorcet)
[![Codebeat](https://codebeat.co/badges/421770ad-085c-480e-97eb-b46b30234a5e)](https://codebeat.co/projects/github-com-slimaku-kondorcet)
[![Issues](https://img.shields.io/github/issues/slimaku/kondorcet.svg)](https://github.com/slimaku/kondorcet/issues)
[![Pull requests](https://img.shields.io/github/issues-pr/slimaku/kondorcet.svg)](https://github.com/slimaku/kondorcet/pulls)

Kotlin library for [voting systems](https://en.wikipedia.org/wiki/Voting_system), with a strong accent on the [Condorcet method](https://en.wikipedia.org/wiki/Condorcet_method) and it's derivatives.

## Usage exemple
```kotlin
// Create a poll that ensure the validity of the ballots
val poll = CheckedPoll<Char>()

// Add ballots to the poll
// Theses ballot contains the candidates ordered by prefernces
poll.vote(ballot('A', 'C', 'B'), 23)
poll.vote(ballot('B', 'C', 'A'), 19)
poll.vote(ballot('C', 'B', 'A'), 16)
poll.vote(ballot('C', 'A', 'B'), 2)

// Compute and return the result
// The result is an instance of Ballot wich contains the candidates ordered from the winners to the losers
val schulze = poll.result() // [[C], [B], [A]] (Use the Schulze method by default)

// Eventualy get the result of other voting methods
val condorcet = poll.result(CondorcetMethod)            // [[C], [B], [A]] (identical of the shulze method in this case)
val relativeMajority = poll.result(RelativeMajority)    // [[A], [B], [C]]
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

# Kondorcet
[![Build Status](https://travis-ci.org/slimaku/kondorcet.svg?branch=master)](https://travis-ci.org/slimaku/kondorcet)
[![Code covertage](https://codecov.io/gh/slimaku/kondorcet/branch/master/graph/badge.svg)](https://codecov.io/gh/slimaku/kondorcet)
[![JitPack](https://jitpack.io/v/slimaku/kondorcet.svg)](https://jitpack.io/#slimaku/kondorcet)

This is a library to make a vote using the [Condorcet method](https://en.wikipedia.org/wiki/Condorcet_method)

## Usage exemple
### From Kotlin
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

// Compute an return the result
// The result is an instance of Ballot wich contains the candidates ordered from the winners to the losers
val result = poll.result()
```

### From Java
```java
// Create a poll that will be solved using the Schulze method 
// See https://en.wikipedia.org/wiki/Schulze_method
Poll<Character> poll = SchulzeMethod<Character>()

// Add ballots to the poll
// Theses ballot contains the candidates ordered by prefernces
poll.vote(Ballot.of('A', 'C', 'B', 'E', 'D'), 5)
poll.vote(Ballot.of('A', 'D', 'E', 'C', 'B'), 5)
poll.vote(Ballot.of('B', 'E', 'D', 'A', 'C'), 8)
poll.vote(Ballot.of('C', 'A', 'B', 'E', 'D'), 3)

// Compute an return the result
// The result is an instance of Ballot wich contains the candidates ordered from the winners to the losers
Ballot<Character> result = poll.result()
```

## Add the library to your project
With [Gradle](https://gradle.org)
```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.slimaku:kondorcet:master-SNAPSHOT'
}
```

You can also use [Jitpack](https://jitpack.io/#slimaku/kraft) with maven, sbt or leiningen.

## Documentation
[KDoc](https://slimaku.github.io/kondorcet/doc/1.0/kondorcet/kondorcet/index.html)

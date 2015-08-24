# [jsexp](http://julianmendez.github.io/jsexp)

[![Build Status](https://travis-ci.org/julianmendez/jsexp.png?branch=master)](https://travis-ci.org/julianmendez/jsexp)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.tu-dresden.inf.lat.jsexp/jsexp/badge.svg)](http://search.maven.org/#search|ga|1|g%3A%22de.tu-dresden.inf.lat.jsexp%22)

**jsexp** is a parser for S-expressions.

Author: [Julian Mendez](http://lat.inf.tu-dresden.de/~mendez)


### License

GNU Lesser General Public License version 3.0 ([LGPLv3](http://www.gnu.org/licenses/lgpl-3.0.txt))


### Source code

To checkout and compile the project, use:

```
$ git clone https://github.com/julianmendez/jsexp.git
$ cd jsexp
$ mvn clean install
```

To compile the project offline, first download the dependencies:
```
$ mvn dependency:go-offline
```
and once offline, use:
```
$ mvn --offline clean install
```

The bundles uploaded to [Sonatype](https://oss.sonatype.org/) are created with:
```
$ cd jsexp
$ mvn clean install -DperformRelease=true
```
and then:
```
$ cd target
$ jar -cf bundle.jar jsexp-*
```


### Contact

In case you need more information, please contact @julianmendez .




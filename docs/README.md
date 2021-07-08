# [jsexp](https://julianmendez.github.io/jsexp)

[![build](https://github.com/julianmendez/jsexp/workflows/Java%20CI/badge.svg)](https://github.com/julianmendez/jsexp/actions)
[![coverage](https://coveralls.io/repos/github/julianmendez/jsexp/badge.svg?branch=master)](https://coveralls.io/github/julianmendez/jsexp?branch=master)
[![maven central](https://maven-badges.herokuapp.com/maven-central/de.tu-dresden.inf.lat.jsexp/jsexp/badge.svg)](https://search.maven.org/#search|ga|1|g%3A%22de.tu-dresden.inf.lat.jsexp%22)
[![license](https://img.shields.io/badge/license-LGPL%203.0-blue.svg)](https://www.gnu.org/licenses/lgpl-3.0.txt)

**jsexp** is a parser for S-expressions.


## Download

* [library JAR file](https://sourceforge.net/projects/latitude/files/jsexp/0.2.2/jsexp-0.2.2.jar/download)
* [The Central Repository](https://repo1.maven.org/maven2/de/tu-dresden/inf/lat/jsexp/)
* [older releases](https://sourceforge.net/projects/jsexp/files/)
* as dependency:

```xml
<dependency>
  <groupId>de.tu-dresden.inf.lat.jsexp</groupId>
  <artifactId>jsexp</artifactId>
  <version>0.2.2</version>
</dependency>
```


## Author

[Julian Mendez](https://julianmendez.github.io)


## License

GNU Lesser General Public License version 3.0 ([LGPLv3](http://www.gnu.org/licenses/lgpl-3.0.txt))


## Release notes

See [release notes](https://julianmendez.github.io/jsexp/RELEASE-NOTES.html).


## Source code

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
$ mvn clean install -DperformRelease=true
```

and then:

```
$ cd jsexp/target
$ jar -cf bundle.jar jsexp-*
```

The version number is updated with:

```
$ mvn versions:set -DnewVersion=NEW_VERSION
```

where *NEW_VERSION* is the new version.


## Contact

In case you need more information, please contact @julianmendez .



Gatling Training
================

Starter project for the Gatling training session.

Tooling
-------

* JDK 17+ required
* Maven build via `gatling-maven-plugin` — source layout under `src/gatling/{java,resources}`
* Run a simulation: `mvn gatling:test`

Installing Maven
----------------

If you don't already have Maven, install it for your OS and verify with
`mvn -version`.

**macOS** — via [Homebrew](https://brew.sh):

```sh
brew install maven
```

**Windows** — via [Chocolatey](https://chocolatey.org) (admin PowerShell):

```powershell
choco install maven
```

Or via [Scoop](https://scoop.sh):

```powershell
scoop install maven
```

Manual install (either OS): download the binary archive from the
[Apache Maven site](https://maven.apache.org/download.cgi), extract it,
and add the `bin/` folder to your `PATH`.

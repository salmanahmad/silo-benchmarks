Silo Benchmark Suite
===============

Benchmarks for The Silo Programming Language. Benchmarks a variety of different things including CPU performance, memory usage, network performance (TCP + HTTP) and others.

Requirements
===============

There are many requirements to run all of the benchmarks in the suite. Generally there should be a `Makefile` which provides a consistent way to build and run all of the examples. In general the following are needed:

- Make
- gcc / g++
- Ruby MRI 2.0+
- Gem
- Bundler
- Node.js
- npm
- Python 3.0+
- pip
- Java 1.7
- Scale
- Clojure


Build Conventions
===============

Generally there will me a `Makefile` accompanying each example. First do `make compile` to compile / install all dependencies. Then run `make run` to actually run the examples.

Port Conventions
===============

All servers should be configured to listen on `127.0.0.1:{port}`. They should NOT run on `localhost`, `0.0.0.0` or others. This consistency makes it much easier to benchmark with tools like `ab`.

Each language has a range of ports that they can use. They should always use the lowest port number but sometimes they can use others if there are competing implementations. For example, Ruby may want to use `thin` as well a `puma` and run those on different ports within the "8100-8199" range

- Silo: 8000 - 8099
- Ruby: 8100 - 8199
- Python: 8200 - 8299
- Node: 8300 - 8399
- Java: 8400 - 8499
- Scala: 8500 - 8599
- Clojure: 8600 - 8699


Fixes to ulimit and Port Range
===============

Generally, you should run the benchmarks on Linux. With OS X there are several things that you need to tweak.

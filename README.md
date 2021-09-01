# Simple WebAssembly examples

This repository is a collection of WASM examples, going from simplest to slightly more complex ones.

The goal is to demonstrate how WASM binaries can be consumed from various environments.
The binaries are currently produced by Rust.

## Examples

* [e0-trivial](e0-trivial/README.md) - little or no tools needed
  * [HTML client](e0-trivial/example.html)
  * [Java client](e0-trivial-java-client/)
* _more to come soon_

## Structure

Each directory comes with a `Makefile`. It is used only as a container for commands that are useful to try with the demos.

## Languages

Following languages and environments are the primary target for consuming WebAssembly artefacts:

- Javascript (from a webbrowser)
- Rust
- Java
- Python
- C++

## Tools

Some tools help with defining bindings and they are going to be used and demonstrated in some of the demos.

Some of them are:

- wasm-bindgen
- [wasm-pack](https://rustwasm.github.io/wasm-pack/)
- [WASI - WebAssembly System Interface](https://github.com/webassembly/wasi)
- WebAssembly Interface Types

## Contributions

Contributions are totally welcome!
Please send PR.

## Status

The collection is just starting. Please be patient - or consider adding your examples.

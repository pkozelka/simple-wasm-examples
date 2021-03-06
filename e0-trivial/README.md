# E0: Trivial example

This example exposes single function `compute_sum()` which just sums parameters of different types.

Note that without any *bindgen support, WASM is a desperate place where no much types can be used - only integers (`I32`, `I64`) and floating-point numbers (`F32`, `F64`).
Everything else (including strings) requires passing via shared memory, which will be demonstrated in other examples.

Demonstrates:

- pure WebAssembly, minimum tools needed; we build with cargo
- target platform `wasm-unknown-unknown` is configured as default in `.cargo/config` so we don't have to specify the `--target` option in `cargo`
- size is heavily reduced by configuring `lto = true` in the root `Cargo.toml` file (~1.5M -> 282 bytes)

## Tools needed

- [Cargo](https://www.rust-lang.org/tools/install)
- target `wasm32-unknown-unknown`
  - install with `rustup target add wasm32-unknown-unknown --toolchain nightly`
- optional: [wasmer](https://wasmer.io/) (for `make run-wasmer`)
- optional: [wabt](https://nicedoc.io/WebAssembly/wabt) (for `make wat`)
- optional: [microserver](https://github.com/robertohuertasm/microserver) (for `make run-webserver`) - but you can use any other tool to expose dir on HTTP

## Observations

- Rust types `u32` and `i32` are translated into WebAssembly type `I32`; similarly for 64bit variant
- any smaller types like `u8` or `i16` etc become `I32` as well
- still, the code internally treats the signed and unsigned types differently - see `make wat`

## HTML client observations

(use page `http://localhost:9090/e0-trivial/example.html` served from the project root directory)

- 64bit types must be passed as `BigInt`, ie. using either `n` suffix or the `BigInt()` constructor

## WAT

```
(module
  (type (;0;) (func (param i32 i32 i64 i64 f32 f64) (result f64)))
  (func $compute_sum (type 0) (param i32 i32 i64 i64 f32 f64) (result f64)
    local.get 0
    f64.convert_i32_u
    local.get 1
    f64.convert_i32_s
    f64.add
    local.get 2
    f64.convert_i64_u
    f64.add
    local.get 3
    f64.convert_i64_s
    f64.add
    local.get 4
    f64.promote_f32
    f64.add
    local.get 5
    f64.add)
  (memory (;0;) 16)
  (global $__stack_pointer (mut i32) (i32.const 1048576))
  (global (;1;) i32 (i32.const 1048576))
  (global (;2;) i32 (i32.const 1048576))
  (export "memory" (memory 0))
  (export "compute_sum" (func $compute_sum))
  (export "__data_end" (global 1))
  (export "__heap_base" (global 2)))
```

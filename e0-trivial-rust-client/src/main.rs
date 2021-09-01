extern crate wasmtime;

use std::error::Error;

use wasmtime::*;

fn main() -> Result<(), Box<dyn Error>> {
    // An engine stores and configures global compilation settings like
    // optimization level, enabled wasm features, etc.
    let engine = Engine::default();

    let module = Module::from_file(&engine, "target/wasm32-unknown-unknown/release/e0_trivial.wasm")?;
    // let module = Module::new(&engine, r#"(module (func (export "answer") (result i32) i32.const 42))"#)?;

    // A `Store` is what will own instances, functions, globals, etc. All wasm
    // items are stored within a `Store`, and it's what we'll always be using to
    // interact with the wasm world. Custom data can be stored in stores but for
    // now we just use `()`.
    let mut store = Store::new(&engine, ());

    // With a compiled `Module` we can then instantiate it, creating
    // an `Instance` which we can actually poke at functions on.
    let instance = Instance::new(&mut store, &module, &[])?;

    // The `Instance` gives us access to various exported functions and items,
    // which we access here to pull out our `answer` exported function and
    // run it.
    let compute_sum = instance.get_func(&mut store, "compute_sum")
        .expect("`compute_sum` was not an exported function");

    // There's a few ways we can call the `answer` `Func` value. The easiest
    // is to statically assert its signature with `typed` and then call it.
    let compute_sum = compute_sum.typed::<(u32, i32, u64, i64, f32, f64), f64, _>(&store)?;

    // And finally we can call our function! Note that the error propagation
    // with `?` is done to handle the case where the wasm function traps.
    let result = compute_sum.call(&mut store, (1, -2, 3, -4, 5.7, -6.8))?;
    println!("Result: {:?}", result);
    Ok(())
}

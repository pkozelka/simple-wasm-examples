from wasmtime import Store, Module, Instance

# wire up
store = Store()
module = Module.from_file(store.engine, '../target/wasm32-unknown-unknown/release/e0_trivial.wasm')
instance = Instance(store, module, [])
exports = instance.exports(store)
compute_sum = exports["compute_sum"]
# call
result = compute_sum(store, 1, -2, 3, -4, 5.7, -6.8)
print("Result:", result)

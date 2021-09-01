WASM_BIN=../target/wasm32-unknown-unknown/release/e0_trivial.wasm

all: build

build:
	cargo build --release
	ls -lah $(WASM_BIN)

# Just to inspect the binary
# install from https://github.com/WebAssembly/wabt or use your distro packaging tool
wat:
	wasm2wat $(WASM_BIN)

# see https://wasmer.io/ for installation
run-wasmer:
	wasmer run --invoke compute_sum $(WASM_BIN) -- 1 -2 3 -4 5.7 -6.8
	# prints -3.100000190734863

# see https://docs.wasmtime.dev/cli-install.html for installation
run-wasmtime:
	# NOTE: this does not work now (version 0.29)
	wasmtime run $(WASM_BIN) --invoke compute_sum 1 -2 3 -4 5.7 6.8

# install with `cargo install microserver`
run-webserver:
	# open http://localhost:9090/e0-trivial/example.html in your browser
	microserver ..

WASM_BIN=../target/wasm32-unknown-unknown/release/e0_trivial.wasm

all: build

build:
	cargo build --release
	ls -lah $(WASM_BIN)

wat:
	wasm2wat $(WASM_BIN)

run-wasmer:
	wasmer run --invoke compute_sum $(WASM_BIN) -- 1 -2 3 -4 5.7 -6.8
	# prints -3.100000190734863

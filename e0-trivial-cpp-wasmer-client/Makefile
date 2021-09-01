WASMER_DIR=$(HOME)/.wasmer
OUTPUT_BIN=target/e0-trivial-wasmer-client

all: build

build: $(OUTPUT_BIN)
$(OUTPUT_BIN): Makefile src/*.c
	mkdir -p "target"
	gcc src/main.c -I$(WASMER_DIR)/include -L$(WASMER_DIR)/lib \
	  -lwasmer \
	  --output "$(OUTPUT_BIN)"

run: $(OUTPUT_BIN)
	# TODO use static linking
	LD_LIBRARY_PATH="$(WASMER_DIR)/lib" $(OUTPUT_BIN)

pkg-config:
	wasmer config --pkg-config

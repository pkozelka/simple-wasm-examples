WASM_BIN=../target/wasm32-unknown-unknown/release/e0_trivial.wasm
JAVA_BIN=target/e0-trivial-java-client.jar

all: build

build: $(JAVA_BIN)
$(JAVA_BIN): pom.xml $(shell find src/main -type f)
	mvn clean verify

run: $(WASM_BIN) $(JAVA_BIN)
	java -Dwasmfile=$(WASM_BIN) -jar $(JAVA_BIN)

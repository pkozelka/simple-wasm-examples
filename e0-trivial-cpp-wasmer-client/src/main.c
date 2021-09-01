#include <stdio.h>
#include <wasmer.h>

int main() {
    const char* version = wasmer_version();
    printf("Wasmer version: %s!\n", version);
}

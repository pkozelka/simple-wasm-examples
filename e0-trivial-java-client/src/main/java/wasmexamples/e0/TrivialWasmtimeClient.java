package wasmexamples.e0;

import io.github.kawamuray.wasmtime.Engine;
import io.github.kawamuray.wasmtime.Extern;
import io.github.kawamuray.wasmtime.Func;
import io.github.kawamuray.wasmtime.Instance;
import io.github.kawamuray.wasmtime.Module;
import io.github.kawamuray.wasmtime.Store;
import io.github.kawamuray.wasmtime.Val;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Demonstrates how to use WASMTIME for invoking a WASM method from Java.
 */
public class TrivialWasmtimeClient {
    public static final String WASMFILE = System.getProperty("wasmfile", "target/wasm32-unknown-unknown/release/e0_trivial.wasm");

    public static void main(String[] args) throws IOException {
        final File wasmFile = new File(WASMFILE).getCanonicalFile();
        if (!wasmFile.exists()) {
            throw new FileNotFoundException(wasmFile.getAbsolutePath());
        }
        System.out.println("WASM file: " + wasmFile);
        try (final Store<Void> store = Store.withoutData();
             final Engine engine = store.engine();
             // Module.fromBinary() is also cool, but here we prefer to get it from the FS
             final Module module = Module.fromFile(engine, wasmFile.getAbsolutePath()))
        {
            final Collection<Extern> imports = Collections.emptyList();
            try (final Instance instance = new Instance(store, module, imports)) {
                final Optional<Func> of = instance.getFunc(store, "compute_sum");
                if (of.isPresent()) try (final Func func = of.get()) {
                    final Val[] result = func.call(store,
                        Val.fromI32(1),
                        Val.fromI32(-2),
                        Val.fromI64(3L),
                        Val.fromI64(-4L),
                        Val.fromF32(5.7f),
                        Val.fromF64(-6.8d));
                    System.out.println("Result: " + Arrays.asList(result));
                }
            }
        }
    }
}

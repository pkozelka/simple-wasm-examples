# only if it was present locally
# import e0_trivial
# instead, use sys
import sys
sys.path.insert(0, '../target/wasm32-unknown-unknown/release/')

import e0_trivial

result = e0_trivial.compute_sum(1,-2,3,-4,5.7,-6.8)

print("Result:", result)

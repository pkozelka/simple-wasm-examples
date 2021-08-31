/// Sums numbers with different types
#[no_mangle]
pub fn compute_sum(a: u32, b:i32, c: u64, d: i64, e: f32, f: f64) -> f64 {
   a as f64
       + b as f64
       + c as f64
       + d as f64
       + e as f64
       + f
}

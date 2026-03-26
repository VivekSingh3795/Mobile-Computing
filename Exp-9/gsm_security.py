import random

print("--- GSM A3 Security Algorithm Simulation ---")

# Generate 128-bit secret key (Ki) and 128-bit random number (RAND)
k = random.getrandbits(128)
m = random.getrandbits(128)

# Convert to binary strings and ensure they are exactly 128 bits
kb = bin(k)[2:].zfill(128)
mb = bin(m)[2:].zfill(128)

# Split into left and right 64-bit halves
kbl = kb[0:64]
kbr = kb[64:]
mbl = mb[0:64]
mbr = mb[64:]

# XOR operations mimicking the A3 algorithm mixing process
a1 = int(kbl, 2) ^ int(mbr, 2)
a2 = int(kbr, 2) ^ int(mbl, 2)
a3 = a1 ^ a2

# Convert mixed result back to binary (64 bits)
a4 = bin(a3)[2:].zfill(64)

# Split into 32-bit halves and XOR for the final 32-bit SRES
a5 = a4[0:32]
a6 = a4[32:]
a7 = int(a5, 2) ^ int(a6, 2)

# Display the results
print("128 Bit Key (Ki)       = ", kb)
print("128 Random Bits (RAND) = ", mb)
print("--------------------------------------------")
print("Generated RES/SRES     = ", bin(a7)[2:].zfill(len(a5)))
print("--------------------------------------------")
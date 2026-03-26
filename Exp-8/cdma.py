import numpy as np

# Orthogonal Codes (Walsh Codes)
c1 = [1, 1, 1, 1]
c2 = [1, -1, 1, -1]
c3 = [1, 1, -1, -1]
c4 = [1, -1, -1, 1]
rc = []

print("--- CDMA Simulation ---")
print("Enter the data bits (use 1 or -1):")
d1 = int(input("Enter D1: "))
d2 = int(input("Enter D2: "))
d3 = int(input("Enter D3: "))
d4 = int(input("Enter D4: "))

# Encoding the data
r1 = np.multiply(c1, d1)
r2 = np.multiply(c2, d2)
r3 = np.multiply(c3, d3)
r4 = np.multiply(c4, d4)

# Combining all signals into one channel
resultant_channel = r1 + r2 + r3 + r4
print("\nResultant Channel (The combined signal in the air):", resultant_channel)

# Decoding the data
Channel = int(input("\nEnter the station to listen to (1, 2, 3, or 4): "))

if Channel == 1:
    rc = c1
elif Channel == 2:
    rc = c2
elif Channel == 3:
    rc = c3
elif Channel == 4:
    rc = c4
else:
    print("Invalid channel selected.")
    exit()

# Extracting the specific signal
inner_product = np.multiply(resultant_channel, rc)
print("Inner Product:", inner_product)

res1 = sum(inner_product)
data = res1 / len(inner_product)

print("-----------------------------------")
print(f"Data bit that was sent on Channel {Channel}: {int(data)}")
print("-----------------------------------")
import os
import pandas as pd
import matplotlib.pyplot as plt


os.makedirs("plots", exist_ok=True)


data = pd.read_csv("results.csv")

# --- Time vs n ---
plt.figure()
for algo in data['algo'].unique():
    subset = data[data['algo'] == algo]
    plt.plot(subset['n'], subset['time'], marker='o', label=algo)
plt.xlabel("n")
plt.ylabel("Time (ms)")
plt.title("Time vs n")
plt.legend()
plt.savefig("plots/time_vs_n.png")

# --- Depth vs n ---
plt.figure()
for algo in data['algo'].unique():
    subset = data[data['algo'] == algo]
    plt.plot(subset['n'], subset['depth'], marker='o', label=algo)
plt.xlabel("n")
plt.ylabel("Recursion Depth")
plt.title("Depth vs n")
plt.legend()
plt.savefig("plots/depth_vs_n.png")

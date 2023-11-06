import random

def generate_dataset(size, order, max_val):
    if order == "sorted":
        dataset = [random.randint(1, max_val) for _ in range(size)]
        dataset.sort()
    elif order == "random":
        dataset = [random.randint(1, max_val) for _ in range(size)]
    elif order == "reversed":
        dataset = [random.randint(1, max_val) for _ in range(size)]
        dataset.sort(reverse=True)
    else:
        raise ValueError("Invalid order type. Use 'sorted', 'random', or 'reversed'.")

    return dataset

# Generate datasets
max_val = 1000
sizes = [10, 20, 30]
orders = ["sorted", "random", "reversed"]

for size in sizes:
    for order in orders:
        dataset = generate_dataset(size, order, max_val)
        filename = f"dataset_{size}_{order}.txt"
        
        with open(filename, "w") as file:
            file.write("\n".join(map(str, dataset)))

print("Datasets generated successfully.")


def sum_numbers(a: float, b: float) -> float:
    return a + b

if __name__ == "__main__":
    a = float(input("Enter first number: "))
    b = float(input("Enter second number: "))
    print(f"The sum is: {sum_numbers(a, b)}")
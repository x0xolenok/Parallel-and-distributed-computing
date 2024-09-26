import requests
import json


def main():
    url = 'http://java-server:8080/calculate'

    data = {
        "num1": 10,
        "num2": 20,
        "num3": 30,
        "num4": 40,
        "num5": 50
    }

    headers = {'Content-Type': 'application/json'}

    response = requests.post(url, data=json.dumps(data), headers=headers)

    result = response.json()
    print(f"Sum: {result['sum']}")
    print(f"Product: {result['product']}")


if __name__ == "__main__":
    main()
import ast
import requests

# Post request to the API
r = requests.post("http://192.168.99.100:8443/parcel/size", json={"length": 1, "width": 2, "height": 3, "size": ""})


# Parse response data
size = r.json()['size']

if(str(size) =="XS"):
  exit(0)
else:
  exit(1)

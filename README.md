# e-store
# Team Members
~~~
    1. Bunyodbek Kosimov
    2. Shokhrukh Toshniyozov
    3. Abubakr Abdullaev
~~~

## Steps to run application : 
### 1. Import swa-project2.postman_collection.json file to Postman application
### 2. Run kubectl apply commands
```
kubectl apply -f product-service/k8s/deployment-db.yaml
kubectl apply -f gateway-service/k8s/deployment.yaml
kubectl apply -f product-service/k8s/deployment.yaml
kubectl apply -f account-service/k8s/deployment.yaml
kubectl apply -f order-service/k8s/deployment.yaml
kubectl apply -f payment-service/k8s/deployment.yaml
kubectl apply -f credit-service/k8s/deployment.yaml
kubectl apply -f paypal-service/k8s/deployment.yaml
kubectl apply -f bank-account-service/k8s/deployment.yaml
kubectl apply -f ingress.yaml
```
### 4 Run command to get IP of kubernetes cluster
```
kubectl get ingress estore-ingress  
```
## then put assigned IP to your /etc/hosts file with domain "a.uz"

### 5 Add a.uz to your /etc/hosts file
### 6 Add a.uz to your /etc/hosts file
### 7 Now you can test postman requests.
### Default Admin credentials
~~~
    - email: diana@gmail.com
    - password: pass1234
~~~
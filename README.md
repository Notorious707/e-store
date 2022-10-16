# e-store
## Docker build image commands
```
docker build -t webper2606/swa-project2:account-service ./account-service
docker build -t webper2606/swa-project2:bank-account-service ./bank-account-service
docker build -t webper2606/swa-project2:credit-service  ./credit-service
docker build -t webper2606/swa-project2:order-service ./order-service
docker build -t webper2606/swa-project2:payment-service ./payment-service
docker build -t webper2606/swa-project2:paypal-service ./paypal-service
docker build -t webper2606/swa-project2:product-service ./product-service
docker build -t webper2606/swa-project2:shipping-service ./shipping-service
```
## Docker push image commands
```
docker push webper2606/swa-project2:account-service
docker push webper2606/swa-project2:bank-account-service
docker push webper2606/swa-project2:credit-service 
docker push webper2606/swa-project2:order-service
docker push webper2606/swa-project2:payment-service
docker push webper2606/swa-project2:paypal-service
docker push webper2606/swa-project2:product-service
docker push webper2606/swa-project2:shipping-service
```
## To deploy application first run database deployments
```
kubectl apply -f account-service/k8s/deployment-db.yaml
kubectl apply -f order-service/k8s/deployment-db.yaml
kubectl apply -f payment-service/k8s/deployment-db.yaml
kubectl apply -f product-service/k8s/deployment-db.yaml
kubectl apply -f shipping-service/k8s/deployment-db.yaml
```
Then
```
kubectl apply -f account-service/k8s/deployment.yaml
kubectl apply -f order-service/k8s/deployment.yaml
kubectl apply -f payment-service/k8s/deployment.yaml
kubectl apply -f product-service/k8s/deployment.yaml
kubectl apply -f shipping-service/k8s/deployment.yaml
kubectl apply -f credit-service/k8s/deployment.yaml
kubectl apply -f paypal-service/k8s/deployment.yaml
kubectl apply -f bank-account-service/k8s/deployment.yaml
```
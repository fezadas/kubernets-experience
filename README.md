# Kubernetes Experience

My journey studying Kubernetes with a real-world Kotlin application.

## Pokémon API

A Kotlin Ktor application that serves random Pokémon from [PokéAPI](https://pokeapi.co/).

### Endpoints

| Path                  | Description                        |
|-----------------------|------------------------------------|
| `/`                   | Home page                          |
| `/api/pokemon/random` | Get a random Pokémon               |
| `/api/pokemon/health` | Health check                       |
| `/metrics`            | Prometheus metrics                 |

### Run Locally

#### With Gradle

```bash
./gradlew run
```

#### With Docker

```bash
docker build -t pokemon-api .
docker run -p 8080:8080 pokemon-api
```

### Deploy on Kubernetes (Local/Minikube)

```bash
kubectl apply -f k8s/pokemon-api.yaml
kubectl port-forward -n pokemon-api service/pokemon-api 8080:80
```

Visit http://localhost:8080/api/pokemon/random

### Deploy on AWS EKS

Prerequisites:
- AWS CLI configured
- `eksctl` installed
- `kubectl` configured for your EKS cluster

#### 1. Create ECR Repository

```bash
aws ecr create-repository --repository-name pokemon-api
aws ecr get-login-password --region <REGION> | docker login --username AWS --password-stdin <ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com
```

#### 2. Build and Push Image

```bash
docker build -t pokemon-api .
docker tag pokemon-api:latest <ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com/pokemon-api:latest
docker push <ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com/pokemon-api:latest
```

#### 3. Update and Deploy Manifests

Edit `k8s/aws/deployment.yaml` — replace `<ACCOUNT_ID>` and `<REGION>` with your values.

```bash
kubectl apply -f k8s/aws/rbac.yaml
kubectl apply -f k8s/aws/deployment.yaml
```

#### 4. Access

```bash
kubectl get svc -n pokemon-api pokemon-api -o jsonpath='{.status.loadBalancer.ingress[0].hostname}'
```

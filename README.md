# Kubernetes Experience

My journey studying Kubernetes.

## Hello World

This is a simple hello-world deployment to get started with Kubernetes.

### Prerequisites

- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [Minikube](https://minikube.sigs.k8s.io/docs/start/) (for local testing) or access to a cluster

### Deploy

```bash
kubectl apply -f k8s/hello-world.yaml
```

### Access

If using Minikube:
```bash
minikube service hello-world
```

Or port-forward:
```bash
kubectl port-forward service/hello-world 8080:80
```

Then visit http://localhost:8080

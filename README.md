
# PMT _ Project Management Tool 

Conforme aux exigences: Angular + Spring Boot + PostgreSQL, schema SQL + seed, tests (≥60% visé), CI/CD (GitHub Actions), Dockerfiles (front/back), push Docker Hub, procédure de déploiement.

## Lancement rapide en local
```bash
# 1) DB
docker compose up -d db

# 2) Backend
cd backend
./mvnw -q -B clean verify
./mvnw spring-boot:run

# 3) Frontend (Angular)
cd ../frontend
npm ci
npm run test -- --watch=false --code-coverage
npm run build
npm run start
```
- API docs: http://localhost:8080/swagger-ui/index.html
- Frontend: http://localhost:4200

## Déploiement Docker (simple)
```bash
# Back
cd backend
docker build -t <dh-user>/pmt-backend:latest .
docker push <dh-user>/pmt-backend:latest

# Front
cd ../frontend
docker build -t <dh-user>/pmt-frontend:latest .
docker push <dh-user>/pmt-frontend:latest
```

## CI/CD (GitHub Actions)
Configurer les *Secrets* du dépôt :
- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN`

La pipeline construit, teste (coverage), et pousse les images sur Docker Hub.

## Structure
- **backend/** Spring Boot 3 (Java 21) – API: users, projects, members (roles), tasks, invitations, notifications (stub), task history.
- **frontend/** Angular 17 – Modules projets/tâches, vue tableau (statuts), tests Jest/Karma (config par défaut Angular), coverage.
- **sql/** Schéma PostgreSQL + seed.
- **.github/workflows/ci.yml** – deux jobs (front/back).
- **docker-compose.yml** – service DB local.

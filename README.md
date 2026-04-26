# 🌍 Bolt Planet

> See how much CO₂ you save by choosing a scooter or e-bike over a private car.

**Live Demo:** [bolt-fe-six.vercel.app](https://bolt-fe-six.vercel.app)

---

## What is this?

Bolt Planet is a small web app that lets you pick a persona and instantly see how many grams of CO₂ they saved by riding a Bolt scooter or e-bike instead of driving a private car. Emissions are compared against the average car baseline of **160g CO₂/km**.

Each persona has a real Tallinn scenario — a coastal ride, a cross-city commute, a quick market run — and gets a personalised hero message celebrating their green choice.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | React 19, TypeScript, Vite 8, SCSS Modules |
| Backend | Java 21, Spring Boot 4, Lombok |
| AI Messages | Anthropic Java SDK |
| Frontend Hosting | Vercel |
| Backend Hosting | Google Cloud Run |
| Container Registry | Google Artifact Registry |

---

## Architecture

```
[Vercel]                    [Cloud Run]
React Frontend  →  REST  →  Spring Boot Backend
(bolt-fe-six.vercel.app)    (europe-north1)
                                  │
                          EmissionCalculatorService
                          HeroMessageService
                          PersonaRepository
```

The frontend fetches personas and posts a calculation request. The backend computes CO₂ savings and returns a personalised hero message alongside the numbers.

---

## Emission Model

| Vehicle | CO₂ per km |
|---|---|
| Private car (baseline) | 160g |
| Bolt Scooter | 17g |
| Bolt E-Bike | 25g |

**Savings = (160 - vehicle emission) × distance**

---

## Personas

| Name | Vehicle | Distance | Scenario |
|---|---|---|---|
| Mete | Scooter | 1.2 km | Quick office commute |
| Liis | E-Bike | 4.5 km | Coastal ride |
| Jaan | Scooter | 0.8 km | Quick market run |
| Anu | E-Bike | 12.0 km | From outside Tallinn to center |
| Erik | Scooter | 3.5 km | Meeting friends |
| Kadri | E-Bike | 7.2 km | To university campus |
| Marko | Scooter | 2.5 km | Telliskivi tour |
| Elena | E-Bike | 15.4 km | Crossing the city end to end |

---

## Running Locally

### Prerequisites
- Java 21
- Node.js 20+
- Maven (or use the included `mvnw`)

### Backend

```bash
cd backend
./mvnw clean package -DskipTests
./mvnw spring-boot:run
```

Runs on `http://localhost:8080`

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Runs on `http://localhost:5173`

---

## API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/personas` | Returns all 8 personas |
| POST | `/api/calculate/{id}` | Returns CO₂ savings + hero message for persona |

### Example Response

```json
{
  "personaName": "Liis",
  "vehicleType": "EBIKE",
  "distanceKm": 4.5,
  "baselineGrams": 720.0,
  "actualGrams": 112.5,
  "savingsGrams": 607.5,
  "savingsKg": 0.61,
  "heroMessage": "Incredible choice, Liis! ..."
}
```

---

## Deployment

### Backend — Google Cloud Run

```bash
./mvnw clean package -DskipTests
docker build -t europe-north1-docker.pkg.dev/$PROJECT/bolt-planet/backend:latest .
docker push europe-north1-docker.pkg.dev/$PROJECT/bolt-planet/backend:latest
gcloud run deploy bolt-planet-backend --region=europe-north1 ...
```

### Frontend — Vercel

```bash
cd frontend
vercel --prod
```

---

## License

MIT
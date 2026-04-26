# 🌍 Bolt Planet

> See how much CO₂ you save by choosing a scooter or e-bike over a private car.

*Imagine you are a Bolt user and you see this exact same pop up right after your ride — just like when you select a persona in my app.*

> **Awareness.**
> **Impact.**
> **Stewardship.**
 

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


---

# Bolt Planet — Technical Specification Prompt

You are a Senior Full-Stack Engineer. Build an AI-native Carbon Footprint Coach Bolt plugin called **Bolt Planet**. Users pick a persona, backend calculates CO2 savings, AI returns a unique nature hero message.

---

## Project Structure (Monorepo — Already Created, Do Not Change)
```
C:\BoltPlanet\
├── backend\    (Spring Boot — already initialized, do not touch folder structure)
└── frontend\   (Vite + React — already initialized, do not touch folder structure)
```

Backend package: `com.bolt.planet`
Backend main class: `C:\BoltPlanet\backend\src\main\java\com\bolt\planet\PlanetApplication.java`

---

## Tech Stack
- **Backend:** Java, Spring Boot, Maven
- **Frontend:** React, TypeScript, SCSS, Vite
- **AI:** Claude API — model: `claude-sonnet-4-20250514`
- No database, no auth, fully stateless

---

## Emission Constants (Never Change)
| Vehicle | g CO2/km |
|---------|----------|
| Private car (baseline) | 160g |
| SCOOTER | 17g |
| EBIKE | 25g |

**Formula:**
```
savings_grams = (160 - vehicle_emission) × distance_km
```

---

## Enum Values (Consistent Everywhere — No Exceptions)
```
SCOOTER
EBIKE
```

---

## 8 User Personas (Hardcoded Mock Data)
| ID | Name | Vehicle | Distance (km) | Scenario |
|----|------|---------|---------------|---------|
| 1 | Mete | SCOOTER | 1.2 | Quick office commute |
| 2 | Liis | EBIKE | 4.5 | Coastal ride |
| 3 | Jaan | SCOOTER | 0.8 | Quick market run |
| 4 | Anu | EBIKE | 12.0 | From outside Tallinn to center |
| 5 | Erik | SCOOTER | 3.5 | Meeting friends |
| 6 | Kadri | EBIKE | 7.2 | To university campus |
| 7 | Marko | SCOOTER | 2.5 | Telliskivi tour |
| 8 | Elena | EBIKE | 15.4 | Crossing the city end to end |

---

## Backend Structure

### Add These Files Inside Existing Structure
```
backend\src\main\java\com\bolt\planet\
├── controller\
│   └── BoltPlanetController.java
├── service\
│   ├── EmissionCalculatorService.java
│   └── HeroMessageService.java
├── repository\
│   └── PersonaRepository.java
├── model\
│   ├── Persona.java
│   └── CalculationResult.java
└── PlanetApplication.java  (already exists, do not modify)
```

### Endpoints
```
GET  /api/personas        → returns all 8 personas
POST /api/calculate/{id}  → calculates savings, calls AI, returns result
```

### GET /api/personas Response
```json
[
  {
    "id": 1,
    "name": "Mete",
    "vehicleType": "SCOOTER",
    "distanceKm": 1.2,
    "scenario": "Quick office commute"
  }
]
```

### POST /api/calculate/{id} Response
```json
{
  "personaName": "Mete",
  "vehicleType": "SCOOTER",
  "distanceKm": 1.2,
  "baselineGrams": 192.0,
  "actualGrams": 20.4,
  "savingsGrams": 171.6,
  "savingsKg": 0.1716,
  "heroMessage": "..."
}
```

---

## AI Prompt Template
```
You are a cheerful, nature-loving Carbon Coach built into the Bolt Planet plugin.

A user just completed a trip:
- Name: {name}
- Vehicle: {vehicleType}
- Distance: {distanceKm} km
- CO2 saved vs private car: {savingsGrams}g

Write a unique, joyful 2-3 sentence hero message celebrating their savings.

Rules:
- Reference specific Estonian/Baltic nature: Lahemaa forests, Kadriorg park, Soomaa bogs, Baltic Sea, local birds and squirrels
- Frame the user as a nature guardian
- Never mention food, flights, or consumption rewards
- Focus only on nature benefits: cleaner air, trees breathing easier, animals thriving
- Never repeat the same metaphor across different personas
- Each message must feel personal to this specific distance and vehicle type
- Respond in English only
```

---

## application.properties
```
server.port=8080
spring.web.cors.allowed-origins=http://localhost:5173
```

---

## Frontend Structure
```
frontend\src\
├── components\
│   ├── PersonaGrid.tsx
│   ├── PersonaCard.tsx
│   ├── ResultPanel.tsx
│   └── SavingsDisplay.tsx
├── styles\
│   ├── _variables.scss
│   ├── _components.scss
│   └── main.scss
├── api\
│   └── boltPlanetApi.ts
├── types\
│   └── index.ts
├── App.tsx
└── main.tsx
```

### types/index.ts
```typescript
export interface Persona {
  id: number;
  name: string;
  vehicleType: 'SCOOTER' | 'EBIKE';
  distanceKm: number;
  scenario: string;
}

export interface CalculationResult {
  personaName: string;
  vehicleType: 'SCOOTER' | 'EBIKE';
  distanceKm: number;
  baselineGrams: number;
  actualGrams: number;
  savingsGrams: number;
  savingsKg: number;
  heroMessage: string;
}
```

---

## SCSS Structure
- `_variables.scss` — all color, font, spacing variables
- `_components.scss` — persona card, result panel, savings display styles
- `main.scss` — global reset and imports
- Each component has its own `.module.scss` file

### _variables.scss
```scss
$color-primary: #34D186;
$color-background: #F5F5F0;
$color-surface: #FFFFFF;
$color-surface-hover: #F0F0EB;
$color-text: #1A1A2E;
$color-text-secondary: #6B6B80;
$font-family: 'Inter', system-ui, sans-serif;
$border-radius: 12px;
$transition: all 0.3s ease;
```

---

## UI Rules
- Colors and spacing strictly from `_variables.scss`
- No inline styles
- No Tailwind
- SCSS modules per component
- 8 persona cards in a grid: emoji + name + vehicle + distance
- On card click: `ResultPanel` slides in
- Hero message appears in a speech bubble
- While loading: `"Bolt Planet is calculating... 🌿"`
- Vehicle icons: SCOOTER → 🛴 / EBIKE → 🚲

---

## Precision Rules
- Backend stores full precision: `savingsKg: 0.1716`
- Frontend displays 2 decimals: `0.17 kg`

---

## Reviewer Demo Flow
1. App opens → 8 persona cards visible in grid
2. Reviewer clicks a card (e.g. Mete — 1.2km 🛴)
3. FE sends `POST /api/calculate/1`
4. BE calculates + calls Claude API
5. Screen shows: large **"171.6g CO2 saved"** + hero message in speech bubble
6. Reviewer clicks another card → different savings + different message

---

## Deliberately Out of Scope
- Real Bolt API integration
- User login / registration
- Database
- Real-time GPS tracking
- Manual trip input
- Bolt Ride / Bolt Green vehicle types

---

## Run Commands
```
BE: ./mvnw spring-boot:run
FE: npm run dev
```
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

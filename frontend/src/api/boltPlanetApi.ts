import type { CalculationResult, Persona } from '../types';

const BASE_URL = 'https://bolt-planet-backend-250080246614.europe-north1.run.app/api';

export async function fetchPersonas(): Promise<Persona[]> {
  const res = await fetch(`${BASE_URL}/personas`);
  if (!res.ok) throw new Error('Failed to fetch personas');
  return res.json();
}

export async function calculateSavings(id: number): Promise<CalculationResult> {
  const res = await fetch(`${BASE_URL}/calculate/${id}`, { method: 'POST' });
  if (!res.ok) throw new Error('Failed to calculate savings');
  return res.json();
}

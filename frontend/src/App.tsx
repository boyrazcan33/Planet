import { useState, useEffect, useRef } from 'react';
import PersonaGrid from './components/PersonaGrid';
import ResultPanel from './components/ResultPanel';
import { fetchPersonas, calculateSavings } from './api/boltPlanetApi';
import type { Persona, CalculationResult } from './types';
import './styles/main.scss';

export default function App() {
  const [personas, setPersonas] = useState<Persona[]>([]);
  const [selectedPersona, setSelectedPersona] = useState<Persona | null>(null);
  const [result, setResult] = useState<CalculationResult | null>(null);
  const [loading, setLoading] = useState(false);
  const resultRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    fetchPersonas().then(setPersonas).catch(console.error);
  }, []);

  useEffect(() => {
    if ((loading || result) && resultRef.current) {
      resultRef.current.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }, [loading, result]);

  const handleSelect = async (persona: Persona) => {
    if (loading) return;
    setSelectedPersona(persona);
    setLoading(true);
    setResult(null);
    try {
      const data = await calculateSavings(persona.id);
      setResult(data);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
      <div className="app">
        <header className="app-header">
          <span className="app-logo">🌍</span>
          <h1>Bolt Planet</h1>
          <p>Pick a persona and see how much CO₂ they saved by choosing a scooter or e-bike instead of a private car. We compare against the average car emission of 160g/km.</p>
        </header>

        <main className="app-main">
          <PersonaGrid
              personas={personas}
              selectedId={selectedPersona?.id ?? null}
              onSelect={handleSelect}
          />

          {(loading || result) && (
              <div ref={resultRef}>
                <ResultPanel
                    result={result}
                    loading={loading}
                    persona={selectedPersona}
                />
              </div>
          )}
        </main>
      </div>
  );
}
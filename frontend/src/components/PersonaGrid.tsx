import type { Persona } from '../types';
import PersonaCard from './PersonaCard';
import styles from './PersonaGrid.module.scss';

interface Props {
  personas: Persona[];
  selectedId: number | null;
  onSelect: (persona: Persona) => void;
}

export default function PersonaGrid({ personas, selectedId, onSelect }: Props) {
  return (
    <div className={styles.grid}>
      {personas.map(persona => (
        <PersonaCard
          key={persona.id}
          persona={persona}
          isSelected={selectedId === persona.id}
          onClick={onSelect}
        />
      ))}
    </div>
  );
}

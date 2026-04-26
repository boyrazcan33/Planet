import type { Persona } from '../types';
import styles from './PersonaCard.module.scss';

const VEHICLE_ICON: Record<string, string> = {
  SCOOTER: '🛴',
  EBIKE: '🚲',
};

interface Props {
  persona: Persona;
  isSelected: boolean;
  onClick: (persona: Persona) => void;
}

export default function PersonaCard({ persona, isSelected, onClick }: Props) {
  return (
    <button
      className={`${styles.card} ${isSelected ? styles.selected : ''}`}
      onClick={() => onClick(persona)}
      aria-pressed={isSelected}
    >
      <span className={styles.icon}>{VEHICLE_ICON[persona.vehicleType]}</span>
      <span className={styles.name}>{persona.name}</span>
      <span className={styles.vehicle}>{persona.vehicleType}</span>
      <span className={styles.distance}>{persona.distanceKm} km</span>
      <span className={styles.scenario}>{persona.scenario}</span>
    </button>
  );
}

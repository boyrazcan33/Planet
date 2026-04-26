import type { CalculationResult } from '../types';
import styles from './SavingsDisplay.module.scss';

const VEHICLE_ICON: Record<string, string> = {
  SCOOTER: '🛴',
  EBIKE: '🚲',
};

interface Props {
  result: CalculationResult;
}

export default function SavingsDisplay({ result }: Props) {
  return (
    <div className={styles.container}>
      <div className={styles.savingsAmount}>
        {result.savingsGrams.toFixed(1)}g
      </div>
      <div className={styles.savingsLabel}>CO₂ saved</div>
      <div className={styles.details}>
        <span className={styles.kg}>{result.savingsKg.toFixed(2)} kg</span>
        <span className={styles.divider}>·</span>
        <span>{result.distanceKm} km</span>
        <span className={styles.divider}>·</span>
        <span>{VEHICLE_ICON[result.vehicleType]}</span>
      </div>
    </div>
  );
}

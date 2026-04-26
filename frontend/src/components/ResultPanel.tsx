import type { CalculationResult, Persona } from '../types';
import SavingsDisplay from './SavingsDisplay';
import styles from './ResultPanel.module.scss';

interface Props {
  result: CalculationResult | null;
  loading: boolean;
  persona: Persona | null;
}

export default function ResultPanel({ result, loading }: Props) {
  return (
    <div className={styles.panel}>
      {loading && (
        <div className={styles.loading}>
          <span className={styles.loadingText}>Bolt Planet is calculating... 🌿</span>
        </div>
      )}

      {!loading && result && (
        <>
          <SavingsDisplay result={result} />
          <div className={styles.speechBubble}>
            <p>{result.heroMessage}</p>
          </div>
        </>
      )}
    </div>
  );
}

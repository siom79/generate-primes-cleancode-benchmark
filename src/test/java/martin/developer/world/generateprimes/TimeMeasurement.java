package martin.developer.world.generateprimes;

public class TimeMeasurement {
	private long startMillis;
	private long endMillis;

	public interface TimeMeasurementExecutor {
		void execute();
	}

	public TimeMeasurement takeMeasurement(TimeMeasurementExecutor timeMeasurementExecutor) {
		startMillis = System.nanoTime();
		timeMeasurementExecutor.execute();
		endMillis = System.nanoTime();
		return this;
	}

	public long getTime() {
		return endMillis - startMillis;
	}
}

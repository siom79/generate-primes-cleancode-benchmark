package martin.developer.world.generateprimes;

import martin.developer.world.generateprimes.TimeMeasurement.TimeMeasurementExecutor;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class GeneratePrimesBenchmarkTest {
	private static final Logger logger = Logger.getLogger(GeneratePrimesBenchmarkTest.class);

	@BeforeClass
	public static void beforeClass() {
		BasicConfigurator.configure();
	}

	@Test
	public void benchmark() {
		TimeMeasurement timeMeasurement = new TimeMeasurement();
		for (int i = 10; i < 1000000000; i = i * 10) {
			runMeasurement(timeMeasurement, i);
		}
	}

	private void runMeasurement(final TimeMeasurement timeMeasurement, final int i) {
		long timeOneMethod = timeMeasurement.takeMeasurement(new TimeMeasurementExecutor() {
			public void execute() {
				GeneratePrimes.generatePrimes(i);
			}
		}).getTime();
		long timePlentyMethods = timeMeasurement.takeMeasurement(new TimeMeasurementExecutor() {
			public void execute() {
				GeneratePrimesCleanCode.generatePrimes(i);
			}
		}).getTime();
		logStats(i, timeOneMethod, timePlentyMethods);
	}

	private void logStats(final int i, long timeOneMethod, long timePlentyMethods) {
		logger.info(String.format("OneMethod    : %.2f ms for %s", (double) timeOneMethod / 1000, i));
		logger.info(String.format("PlentyMethods: %.2f ms for %s", (double) timePlentyMethods / 1000, i));
		logger.info(String.format("OneMethod/PlentyMethods: %.2f (< 1 means OneMethod is faster)",
				(double) timeOneMethod / timePlentyMethods));
	}
}

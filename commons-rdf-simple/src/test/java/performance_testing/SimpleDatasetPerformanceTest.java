package performance_testing;

import org.apache.commons.rdf.simple.*;
import org.junit.Rule;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

@BenchmarkOptions(concurrency = 1, warmupRounds = 3, benchmarkRounds = 20000)
public class SimpleDatasetPerformanceTest extends SimpleDatasetTest {
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
}

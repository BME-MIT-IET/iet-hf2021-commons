package performance_testing;

import org.apache.commons.rdf.simple.*;
import org.apache.commons.rdf.simple.experimental.AbstractRDFParserTest;
import org.junit.Rule;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;

@BenchmarkOptions(concurrency = 1, warmupRounds = 0, benchmarkRounds = 1)
public class AbstractRDFParserPerformanceTest extends AbstractRDFParserTest {
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
}

package org.pitest.pitclipse.ui.behaviours.steps;

import static org.junit.Assert.assertEquals;
import static org.pitest.pitclipse.ui.behaviours.pageobjects.PageObjects.INSTANCE;
import static org.pitest.pitclipse.ui.util.AssertUtil.assertDoubleEquals;

import org.pitest.pitclipse.ui.behaviours.Then;
import org.pitest.pitclipse.ui.behaviours.When;

public class PitclipseSteps {

	@When("test $testClassName in package $packageName is run for project $projectName")
	public void runTest(String projectName, String packageName,
			String testClassName) {
		INSTANCE.getPackageExplorer().selectClass(testClassName, packageName,
				projectName);
		INSTANCE.getRunMenu().runPit();

	}

	@Then("a coverage report is generated with $classes classes tested with overall coverage of $totalCoverage and mutation coverage of $mutationCoverage")
	public void coverageReportGenerated(int classes, double totalCoverage,
			double mutationCoverage) {
		INSTANCE.getPitView().waitForUpdate();
		assertEquals(classes, INSTANCE.getPitView().getClassesTested());
		assertDoubleEquals(totalCoverage, INSTANCE.getPitView()
				.getOverallCoverage());
		assertDoubleEquals(mutationCoverage, INSTANCE.getPitView()
				.getMutationCoverage());
	}
}
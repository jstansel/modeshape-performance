/*
 * JBoss, Home of Professional Open Source
 * Copyright [2011], Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.modeshape;

import org.junit.Test;
import org.modeshape.jcr.JcrRepositoryFactory;
import org.modeshape.jcr.perftests.SuiteRunner;
import org.modeshape.jcr.perftests.RunnerCfg;
import org.modeshape.jcr.perftests.report.TextFileReport;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Runs the performance tests against a Modeshape 3.x repo.
 *
 * @author Horia Chiorean
 */
public class ModeShape30PerformanceTest {

    @Test
    public void testModeShapeInMemory() throws Exception {
        // see https://issues.jboss.org/browse/MODE-1346
        SuiteRunner performanceTestSuiteRunner =
                new SuiteRunner("ModeShape 3.x InMemory", new RunnerCfg().addTestsToExclude("BigFileReadTestSuite", "BigFileWriteTestSuite"));
        Map<String, URL> parameters = new HashMap<String, URL>();
        parameters.put(JcrRepositoryFactory.URL, getClass().getClassLoader().getResource("configRepository.json"));
        performanceTestSuiteRunner.runPerformanceTests(parameters, null);

        new TextFileReport(TimeUnit.SECONDS).generateReport(performanceTestSuiteRunner.getTestData());
    }
}

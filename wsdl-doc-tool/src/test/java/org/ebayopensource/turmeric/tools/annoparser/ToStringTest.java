package org.ebayopensource.turmeric.tools.annoparser;

import java.util.ArrayList;
import java.util.List;

import org.ebayopensource.turmeric.tools.annoparser.driver.Driver;
import org.junit.Test;

import junit.framework.TestCase;

public class ToStringTest  extends TestCase{
	/**
	 * Test driver.
	 */
	@Test
	public void testToStringWithSample() {
			List<String> docs=new ArrayList<String>();
			docs.add("sampleWsdl/FindingService(1.8.0).wsdl");
			docs.add("ClientAlertsService_707_doc_private.wsdl");
			String tempdir = System.getProperty("java.io.tmpdir");
			Driver driver=new Driver(tempdir, "ToStringConfiguration.xml",docs,null);
			driver.process();
		
	}
}

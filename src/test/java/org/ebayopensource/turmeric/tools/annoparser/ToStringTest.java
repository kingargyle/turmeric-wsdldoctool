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
			//docs.add("ebaySvc_705_doc_public.wsdl");
			//docs.add("SampleWsdl.wsdl");
			docs.add("ClientAlertsService_707_doc_private.wsdl");
			Driver driver=new Driver("c:\\ToStringOutput\\","ToStringConfiguration.xml",docs,null);
			driver.process();
		
	}
}

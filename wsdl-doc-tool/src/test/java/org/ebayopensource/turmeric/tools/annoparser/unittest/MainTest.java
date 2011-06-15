/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.unittest;

import java.net.URL;

import org.ebayopensource.turmeric.tools.annoparser.Main;
import org.ebayopensource.turmeric.tools.annoparser.commons.Constants;
import org.ebayopensource.turmeric.tools.annoparser.exception.WsdlDocException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The Class MainTest.
 * 
 * @author sdaripelli
 */
public class MainTest {

	String tempDir = null;
	@Before
	public void setUp() {
		tempDir = System.getProperty("java.io.tmpdir");
	}
	
	/**
	 * Test main.
	 */
	@Test
	public void testMain() {
		String[] args = new String[] {
				"documents="
						+ "sampleWsdl/AddressVerificationService(1.0.0).wsdl",
				"output_dir=" + tempDir + "/Documents" };
		try {
			Main.main(args);
		} catch (WsdlDocException e) {
			fail(e.getMessage());
		}
	}

	// @Test
	// public void testMainWithConfig() {
	// String [] args=new
	// String[]{"config="+getFilepathFromTestConfigFolder("ConfigurationWithAllProps")};
	// try {
	// Main.main(args);
	// } catch (WsdlDocException e) {
	// fail(e.getMessage());
	// }
	// }

	// @Test
	// public void testAll() {
	// URL url=this.getClass().getClassLoader().getResource("sampleWsdl/");
	// File file=new File(url.getPath());
	// File[] files=file.listFiles();
	// String documents="";
	// for(File fileName:files){
	// documents=documents+fileName.getAbsolutePath()+";";
	// }
	// String [] args=new
	// String[]{"documents="+documents,"output_dir=C:\\annpParser\\Documents"};
	// try {
	// Main.main(args);
	// } catch (WsdlDocException e) {
	// fail(e.getMessage());
	// }
	// }

	/**
	 * Test main with config and sys env.
	 */
	@Test
	public void testMainWithConfigAndSysEnv() {
		String annoParserConfig = System.getenv().get("ANNOPARSER_CONFIG");
		if (annoParserConfig == null) {
			return;
		}
		String[] args = new String[] {};
		try {
			Main.main(args);
		} catch (WsdlDocException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test main for xsd.
	 */
	@Test
	public void testMainForXsd() {
		String[] args = new String[] { "documents=" + "sampleWsdl/sample.xsd",
				"output_dir=" + tempDir };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test main for sax exception.
	 */
	@Test
	public void testMainForSaxException() {
		String[] args = new String[] {
				"documents=" + "sampleWsdl/sample_invalid.xsd",
				"output_dir=" + tempDir };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test main for invalid extension.
	 */
	@Test
	public void testMainForInvalidExtension() {
		String[] args = new String[] {
				"documents=" + "sampleWsdl/sample_invalid_extension.xsd1",
				"output_dir=" + tempDir };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test main for invalid op gen.
	 */
	@Test
	public void testMainForInvalidOpGen() {
		String[] args = new String[] { "config="
				+ getFilepathFromTestConfigFolder("ConfigurationWithInvalidOpGenerator.xml") };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test main for no config file.
	 */
	@Test
	public void testMainForNoConfigFile() {
		String[] args = new String[] { "config="
				+ getFilepathFromTestConfigFolder("test.xml") };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets the filepath from test config folder.
	 * 
	 * @param fileName
	 *            the file name
	 * @return the filepath from test config folder
	 */
	private String getFilepathFromTestConfigFolder(String fileName) {
		String path = "";
		URL url = this.getClass().getClassLoader().getResource(fileName);
		if (null != url) {
			path = url.getPath();
		}
		return path;
	}

	/**
	 * Test main with help arg.
	 */
	@Test
	public void testMainWithHelpArg() {
		String[] args = new String[] {
				"documents="
						+ "sampleWsdl/AddressVerificationService(1.0.0).wsdl",
				"output_dir=" + tempDir + "/Documents",
				Constants.COMMAND_LINE_HELP_ARG };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test main with no doc value.
	 */
	@Test
	public void testMainWithNoDocValue() {
		String[] args = new String[] { "documents",
				"output_dir=" + tempDir + "/Documents",
				Constants.COMMAND_LINE_HELP_ARG };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test main for invalid op gen class.
	 */
	@Test
	public void testMainForInvalidOpGenClass() {
		String[] args = new String[] { "config="
				+ getFilepathFromTestConfigFolder("ConfigurationWithAbstractClassAsOpGen.xml") };
		try {
			Main.main(args);
		} catch (Exception e) {
			if (e.getCause() instanceof InstantiationException) {
				assertTrue(true);
			}
		}
	}

	/**
	 * Test main for invalid anno parser class.
	 */
	@Test
	public void testMainForInvalidAnnoParserClass() {
		String[] args = new String[] { "config="
				+ getFilepathFromTestConfigFolder("ConfigFileWithInvalidAnnoParser.xml") };
		try {
			Main.main(args);
		} catch (Exception e) {
			if (e.getCause() instanceof ClassNotFoundException) {
				assertTrue(true);
			}
		}
	}

	/**
	 * Test main for abstract anno parser class.
	 */
	@Test
	public void testMainForAbstractAnnoParserClass() {
		String[] args = new String[] { "config="
				+ getFilepathFromTestConfigFolder("ConfigFileWithAbstractAnnoParser.xml") };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test main for invalid config file.
	 */
	@Test
	public void testMainForInvalidConfigFile() {
		String[] args = new String[] { "config="
				+ getFilepathFromTestConfigFolder("Configuration_invalid.xml") };
		try {
			Main.main(args);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

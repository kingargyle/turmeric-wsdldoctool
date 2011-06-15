package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.ebayopensource.turmeric.tools.annoparser.WSDLDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.XSDDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.context.OutputGenaratorParam;
import org.ebayopensource.turmeric.tools.annoparser.exception.WsdlDocException;
import org.ebayopensource.turmeric.tools.annoparser.exception.XsdDocException;
import org.ebayopensource.turmeric.tools.annoparser.outputgenerator.OutputGenerator;

/**
 * The Class ToStringOutputGenerator.
 */
public class ToStringOutputGenerator implements OutputGenerator {
	private OutputGenaratorParam outParam;
	private Map<Object,String> docs=new HashMap<Object, String>();
	
	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.tools.annoparser.outputgenerator.OutputGenerator#completeProcessing()
	 */
	@Override
	public void completeProcessing() throws XsdDocException {
		for(Map.Entry<Object,String> entry:docs.entrySet()){
			printToFileIfApplicable(entry.getKey(), outParam,entry.getValue());
		}
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.tools.annoparser.outputgenerator.OutputGenerator#generateWsdlOutput(org.ebayopensource.turmeric.tools.annoparser.WSDLDocInterface, org.ebayopensource.turmeric.tools.annoparser.context.OutputGenaratorParam)
	 */
	@Override
	public void generateWsdlOutput(WSDLDocInterface wsdlDoc,
			OutputGenaratorParam outputGenaratorParam) throws WsdlDocException {
		System.out.println(wsdlDoc.toString());
		docs.put(wsdlDoc, wsdlDoc.getServiceName()+" To String.txt");
		outParam=outputGenaratorParam;
		
	}

	private void printToFileIfApplicable(Object doc,
			OutputGenaratorParam outputGenaratorParam,String fileName) throws WsdlDocException {
		if (outputGenaratorParam.getParameters() != null) {
			if (outputGenaratorParam.getParameters().get("writeToFile") != null
					&& "true".equals(outputGenaratorParam.getParameters().get(
							"writeToFile"))) {
				String outFile = outputGenaratorParam.getOutputDir();
				if (outFile != null) {
					try {
						writeFile(doc.toString(),outFile,fileName);
					} catch (IOException e) {
						throw new WsdlDocException(e);
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.ebayopensource.turmeric.tools.annoparser.outputgenerator.OutputGenerator#generateXsdOutput(org.ebayopensource.turmeric.tools.annoparser.XSDDocInterface, org.ebayopensource.turmeric.tools.annoparser.context.OutputGenaratorParam)
	 */
	@Override
	public void generateXsdOutput(XSDDocInterface xsdDoc,
			OutputGenaratorParam outputGenaratorParam) throws XsdDocException {
		System.out.println(xsdDoc.toString());
		docs.put(xsdDoc, "XSD DocOutput");
		outParam=outputGenaratorParam;
		
	}

	/**
	 * Write file.
	 *
	 * @param fileContent the file content
	 * @param dir the dir
	 * @param fileName the file name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeFile(String fileContent, String dir,String fileName) throws IOException {
		File file = new File(dir);
		file.mkdirs();
		FileWriter fw = new FileWriter(dir+ File.separator+fileName);
		fw.write(fileContent);
		fw.close();

	}
}
